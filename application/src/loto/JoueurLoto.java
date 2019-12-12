package loto;

import commun.Grille;
import commun.Joueur;

import java.util.ArrayList;
import java.util.List;

class JoueurLoto extends Joueur {
	private static final long serialVersionUID = -2464103437009606435L;
	private Grille grille;
    private List<Integer> cochee;

    JoueurLoto(String nom) {
        super(nom);
        cochee = new ArrayList<>();
    }

    Grille getGrille() {
        return grille;
    }

    List<Integer> getCochee() {
        return cochee;
    }

    void initGrille() {
        this.grille = new Grille(3,9);
        this.grille.initTab();
    }

    Boolean cocher(int i, int j) {
        System.out.println(grille.getMatrice()[i][j]);
        if(grille.getMatrice()[i][j] == 0) return false;
        cochee.add(grille.getMatrice()[i][j]);
        return true;
    }
}
