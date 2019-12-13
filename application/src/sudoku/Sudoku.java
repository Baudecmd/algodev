package sudoku;

import java.util.ArrayList;
import java.util.Arrays;

import commun.Grille;
import commun.Joueur;

public class Sudoku {
	private static Grille grille = new Grille(9, 9);
	private static Grille grilleSolution = new Grille(9, 9);
	private JoueurSudoku joueur;

	/**
	 * Constructeur d'un Sudoku, permet de générer aléatoirement une grille en fonction de son niveau de difficulté
	 * @param lj
	 * @param difficulty
	 */
	public Sudoku(ArrayList<Joueur> lj, int difficulty) {
		if(lj !=null) this.joueur = new JoueurSudoku(lj.get(0)); else this.joueur = null;
		//templateSudoku.setCouple(difficulty);
		int random = (int)(Math.random()*5);
		switch (difficulty) {
		case 1:
			Sudoku.grille.setMatrice(templateSudoku.facile(random).getTemplate().getMatrice());
			Sudoku.grilleSolution.setMatrice(templateSudoku.facile(random).getSolution().getMatrice());
			break;
		case 2:
			Sudoku.grille.setMatrice(templateSudoku.moyen(random).getTemplate().getMatrice());
			Sudoku.grilleSolution.setMatrice(templateSudoku.moyen(random).getSolution().getMatrice());
			break;
		case 3:
			Sudoku.grille.setMatrice(templateSudoku.difficile(random).getTemplate().getMatrice());
			Sudoku.grilleSolution.setMatrice(templateSudoku.difficile(random).getSolution().getMatrice());
			break;
		}

	}
	
	

	public JoueurSudoku getJoueur() {
		return joueur;
	}



	public void setJoueur(JoueurSudoku joueur) {
		this.joueur = joueur;
	}



	public static Grille getGrille() {
		return grille;
	}


	public static void setGrille(Grille grille) {
		Sudoku.grille = grille;
	}



	public static Grille getGrilleSolution() {
		return grilleSolution;
	}



	public static void setGrilleSolution(Grille grilleSolution) {
		Sudoku.grilleSolution = grilleSolution;
	}


	/**
	 * Retourne l'indice de la ligne du coup courant du joueur
	 * @return indice de la ligne du coup courant du joueur
	 */
	public int getXCC() {
		return this.joueur.getCoutCourant().getX();
	}
	
	/**
	 * Retourne l'indice de la colonne du coup courant du joueur
	 * @return indice de la colonne du coup courant du joueur
	 */
	public int getYCC() {
		return this.joueur.getCoutCourant().getY();
	}

	/**
	 * Vérifie si la valeur d'un coup est possible
	 * @return boolean
	 */
	public boolean possibilite() {
		ArrayList<Integer> poss = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

		int x = this.getXCC();
		int y = this.getYCC();

		for (int i = 0; i < 9; i++) {
			if (poss.contains(Sudoku.grille.getMatrice()[i][y]))
				poss.remove((Integer) Sudoku.grille.getMatrice()[i][y]);
			
			if (poss.contains(Sudoku.grille.getMatrice()[x][i]))
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][i]);
			
		}
		switch (x % 3) {
		case 0:
			switch (y % 3) {
			case 0:
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y + 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y + 2]);
				break;
			case 1:

				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y + 1]);
				break;
			case 2:

				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y - 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 2][y - 2]);
				break;

			}
			break;
		case 1:
			switch (y % 3) {
			case 0:

				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y + 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y + 2]);
				break;
			case 1:
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y + 1]);
				break;
			case 2:
			

				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x + 1][y - 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y - 2]);
				break;

			}
			break;
		case 2:
			switch (y % 3) {
			case 0:

				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y + 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y + 2]);
				break;
			case 1:

				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y + 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y + 1]);
				break;
			case 2:
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y]);;
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y - 1]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x][y - 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 1][y - 2]);
				poss.remove((Integer) Sudoku.grille.getMatrice()[x - 2][y - 2]);
				break;

			}
			break;

		}
		if (!poss.contains(this.joueur.getCoutCourant().getValeurRentree())) {
			return false;
		}
		return true;
	}
	

	public static void main(String[] args) {
		Joueur j = new Joueur("joueur1");
		ArrayList<Joueur> lj = new ArrayList<Joueur>();
		lj.add(j);
		Sudoku s = new Sudoku(lj, 1);
		s.joueur.getCoutCourant().setX(1);
		s.joueur.getCoutCourant().setY(2);
		s.joueur.getCoutCourant().setValeurRentree(3);
		System.out.println(s.possibilite());

	}

}
