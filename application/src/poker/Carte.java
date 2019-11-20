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

    public int compareTo(Carte other){      //pour faire le tri en fonction de la hauteur ET de la couleur; nécessaire à la fonction d'élimination des doublons
        return getHauteur().compareTo(other.getHauteur())+getCouleur().compareTo(other.getCouleur());
    }

    public int compareTo2(Carte other){     //pour faire le tri en fonction de la hauteur uniquement, pour permettre un affichage lisible
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
