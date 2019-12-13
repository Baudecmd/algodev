package commun;

import java.io.*;
import java.util.Objects;


public class Joueur implements Serializable, Comparable<Joueur> {
	private static final long serialVersionUID = -2511917305652687589L;
	private String nom;
    private float score;

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
    }

    public String getNom() {
        return nom;
    }

    /**
     * Augmente le score du joueur
     * @param scoreGagne quantité de score à ajouter
     */
    void increaseScore(float scoreGagne) { //Augmente le score du joueur en fonction de l'entrée
        this.score += scoreGagne;
    }

    @Override
    public boolean equals(Object o) { //Ici deux Joueurs sont les mêmes s'ils ont le même pseudo
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(nom, joueur.nom);
    }

    @Override
    public int compareTo(Joueur j) {
        return Float.compare( j.score,score);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", score=" + score +
                '}';
    }

    public float getScore() {
        return score;
    }
}
