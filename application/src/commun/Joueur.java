package commun;

import java.io.*;
import java.util.Objects;

public class Joueur implements Serializable, Comparable {
    private String nom;
    private float score;

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void increaseScore(float score) {
        this.score += score;
    }

    @Override
    public boolean equals(Object o) {
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
