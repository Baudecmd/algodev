package sudoku;

import commun.Joueur;

/**
 * Un joueur Sudoku est une extension de Joueur, auquel on ajoute un coup courant
 *
 */
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
