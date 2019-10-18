package loto;

import commun.Grille;
import commun.Joueur;

import java.util.List;

public class JoueurLoto extends Joueur {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2464103437009606435L;
	private Grille grille;
    private List<Integer> cochee;

    public JoueurLoto(String nom) {
        super(nom);

    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public List<Integer> getCochee() {
        return cochee;
    }

    public void setCochee(List<Integer> cochee) {
        this.cochee = cochee;
    }

    public void initGrille() {
        this.grille = new Grille(3,9);
        this.grille.initTab();
    }
}
