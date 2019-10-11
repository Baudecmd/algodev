package commun;

public class Grille {
    private int nbLignes;
    private int nbColonnes;

    public int getNbLignes() {
        return nbLignes;
    }

    public void setNbLignes(int nbLignes) {
        this.nbLignes = nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public void setNbColonnes(int nbColonnes) {
        this.nbColonnes = nbColonnes;
    }

    public Grille(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
    }

    public boolean isEmpty(){
        return false;
    }
}
