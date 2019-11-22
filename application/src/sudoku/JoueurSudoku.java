package sudoku;

import commun.Joueur;

public class JoueurSudoku extends Joueur {
	private Coup coutCourant;
	
	public JoueurSudoku(Joueur j) {
		super(j.getNom());
		this.coutCourant = new Coup();
	}

	public Coup getCoutCourant() {
		return coutCourant;
	}

	public void setCoutCourant(Coup coutCourant) {
		this.coutCourant = coutCourant;
	}
	
	

}
