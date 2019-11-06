package sudoku;

import java.util.ArrayList;
import java.util.Arrays;

import commun.Grille;
import commun.Joueur;

public class Sudoku {
	private static Grille grille = new Grille(9, 9);
	private JoueurSudoku joueur;

	public Sudoku(ArrayList<Joueur> lj, int difficulty) {
		this.joueur = new JoueurSudoku(lj.get(0));
		templateSudoku.setCouple(difficulty);
		switch (difficulty) {
		case 1:
			Sudoku.grille.setMatrice(templateSudoku.facile1.getTemplate().getMatrice());
			break;
		case 2:
			Sudoku.grille.setMatrice(templateSudoku.moyen1.getTemplate().getMatrice());
			break;
		case 3:
			Sudoku.grille.setMatrice(templateSudoku.difficile1.getTemplate().getMatrice());
			break;
		}

	}

	public int getXCC() {
		return this.joueur.getCoutCourant().getX();
	}

	public int getYCC() {
		return this.joueur.getCoutCourant().getY();
	}

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
		System.out.println(poss);
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
	
	public boolean verification(coupleSudoku sudo) {
		for(int i = 0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(sudo.getSolution().getMatrice()[i][j]!= sudo.getTemplate().getMatrice()[i][j])
					return false;
			}
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
