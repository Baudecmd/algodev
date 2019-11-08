package poker;

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

    public int compareTo(Carte autre){
        return getHauteur().compareTo(autre.getHauteur());    //est-ce que ça prend en compte la valeur déjà définie?
    }

}
