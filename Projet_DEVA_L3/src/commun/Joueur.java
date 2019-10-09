package commun;

import java.io.*;

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
