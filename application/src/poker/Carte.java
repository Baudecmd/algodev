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

//    public int compareTo(Carte autre){
//        return getHauteur().compareTo(autre.getHauteur());
//    }

    public int compareTo(Carte other){
        return getHauteur().compareTo(other.getHauteur())+getCouleur().compareTo(other.getCouleur());
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
