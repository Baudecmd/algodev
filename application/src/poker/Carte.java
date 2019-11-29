package poker;

import java.util.Objects;

public class Carte {

    private Couleurs couleur;
    private Hauteurs hauteur;

    public Couleurs getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleurs couleur) {
        this.couleur = couleur;
    }

    public Hauteurs getHauteur() {
        return hauteur;
    }

    public void setHauteur(Hauteurs hauteur) {
        this.hauteur = hauteur;
    }

    public Carte(Couleurs couleur, Hauteurs hauteur) {
        this.couleur = couleur;
        this.hauteur = hauteur;
    }

    public int compareTo(Carte other){     //tri d'abord par la hauteur, puis par la couleur si c'est nécessaire. Permet d'éviter que deux cartes de même hauteur soient mal comparées
        if(this.hauteur==other.getHauteur())
            return getCouleur().compareTo(other.getCouleur());
        else
            return getHauteur().compareTo(other.getHauteur());
    }

    @Override
    public String toString() {
        return hauteur.name()+couleur.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return couleur == carte.couleur &&
                hauteur == carte.hauteur;
    }

}
