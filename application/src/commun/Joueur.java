package commun;

import java.io.*;
import java.util.Objects;

public class Joueur implements Serializable, Comparable { //Un Joueur est caracterise par son nom/pseudo et son score
    private String nom;
    private float score;

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void increaseScore(float score) { //Augmente le score du joueur en fonction de l'entrée
        this.score += score;
    }

    @Override
    public boolean equals(Object o) { //Ici deux Joueur sont les mêmes s'ils ont le même pseudo
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(nom, joueur.nom);
    }

    @Override
    public int compareTo(Object o) {
        return Float.compare( ((Joueur)o).score,score);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", score=" + score +
                '}';
    }
}
