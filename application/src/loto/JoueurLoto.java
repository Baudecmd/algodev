package loto;

import commun.Grille;
import commun.Joueur;

import java.util.ArrayList;
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
        cochee = new ArrayList<Integer>();
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

    public Boolean cocher(int i, int j) {
        System.out.println(grille.getMatrice()[i][j]);
        if(grille.getMatrice()[i][j] == 0) return false;
        cochee.add(grille.getMatrice()[i][j]);
        return true;
    }
}
