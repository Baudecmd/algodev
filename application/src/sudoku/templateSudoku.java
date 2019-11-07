package sudoku;

import commun.Grille;

public class templateSudoku {
	public final int nbSudoku = 3;

	public static coupleSudoku facile() {
		coupleSudoku temp = new coupleSudoku();
		switch((int)(Math.random()*5)) {
		case 0:
			temp.template = new Grille(9,9,new int[][]{ { 8, 0, 0, 4, 0, 3, 0, 0, 1 }, 
														{ 0, 0, 0, 8, 0, 1, 0, 2, 0 },
														{ 0, 0, 0, 0, 9, 0, 0, 0, 0 }, 
														{ 0, 1, 0, 6, 0, 0, 9, 0, 0 }, 
														{ 0, 0, 0, 0, 0, 8, 0, 0, 0 },
														{ 0, 0, 0, 0, 5, 0, 0, 0, 3 }, 
														{ 0, 2, 6, 0, 0, 0, 0, 0, 5 }, 
														{ 1, 3, 0, 0, 7, 0, 0, 0, 2 },
														{ 0, 7, 0, 9, 0, 0, 1, 0, 4 } });
			
			temp.solution = new Grille(9,9,new int[][]{ { 8, 6, 9, 4, 2, 3, 5, 7, 1 }, 
														{ 7, 5, 3, 8, 6, 1, 4, 2, 9 },
														{ 2, 4, 1, 7, 9, 5, 3, 8, 6 }, 
														{ 3, 1, 2, 6, 4, 7, 9, 5, 8 }, 
														{ 6, 9, 5, 3, 1, 8, 2, 4, 7 },
														{ 4, 8, 7, 2, 5, 9, 6, 1, 3 }, 
														{ 9, 2, 6, 1, 8, 4, 7, 3, 5 }, 
														{ 1, 3, 4, 5, 7, 6, 8, 9, 2 },
														{ 5, 7, 8, 9, 3, 2, 1, 6, 4 } });
			break;
		case 1:
			temp.template = new Grille(9,9,new int[][]{ { 0, 0, 0, 0, 0, 0, 0, 0, 4 }, 
														{ 7, 0, 0, 0, 0, 9, 0, 0, 0 },
														{ 0, 3, 0, 7, 0, 8, 0, 2, 0 }, 
														{ 0, 9, 5, 0, 0, 7, 0, 0, 6 }, 
														{ 0, 0, 0, 0, 0, 2, 0, 0, 0 },
														{ 1, 0, 0, 0, 4, 0, 0, 0, 0 }, 
														{ 6, 0, 0, 2, 0, 0, 5, 0, 0 }, 
														{ 0, 0, 2, 0, 0, 0, 0, 9, 8 },
														{ 3, 0, 4, 6, 0, 0, 2, 0, 0 } });

			temp.solution = new Grille(9,9,new int[][]{ { 9, 5, 8, 1, 2, 6, 3, 7, 4 }, 
														{ 7, 2, 1, 4, 3, 9, 8, 6, 5 },
														{ 4, 3, 6, 7, 5, 8, 1, 2, 9 }, 
														{ 2, 9, 5, 8, 1, 7, 4, 3, 6 }, 
														{ 8, 4, 3, 9, 6, 2, 7, 5, 1 },
														{ 1, 6, 7, 5, 4, 3, 9, 8, 2 }, 
														{ 6, 7, 9, 2, 8, 1, 5, 4, 3 }, 
														{ 5, 1, 2, 3, 7, 4, 6, 9, 8 },
														{ 3, 8, 4, 6, 9, 5, 2, 1, 7 } });
			break;
		case 2:
			temp.template = new Grille(9,9,new int[][]{ { 8, 0, 0, 4, 0, 3, 0, 0, 1 }, 
														{ 0, 0, 0, 8, 0, 1, 0, 2, 0 },
														{ 0, 0, 0, 0, 9, 0, 0, 0, 0 }, 
														{ 0, 1, 0, 6, 0, 0, 9, 0, 0 }, 
														{ 0, 0, 0, 0, 0, 8, 0, 0, 0 },
														{ 0, 0, 0, 0, 5, 0, 0, 0, 3 }, 
														{ 0, 2, 6, 0, 0, 0, 0, 0, 5 }, 
														{ 1, 3, 0, 0, 7, 0, 0, 0, 2 },
														{ 0, 7, 0, 9, 0, 0, 1, 0, 4 } });

			temp.solution = new Grille(9,9,new int[][]{ { 8, 6, 9, 4, 2, 3, 5, 7, 1 }, 
														{ 7, 5, 3, 8, 6, 1, 4, 2, 9 },
														{ 2, 4, 1, 7, 9, 5, 3, 8, 6 }, 
														{ 3, 1, 2, 6, 4, 7, 9, 5, 8 }, 
														{ 6, 9, 5, 3, 1, 8, 2, 4, 7 },
														{ 4, 8, 7, 2, 5, 9, 6, 1, 3 }, 
														{ 9, 2, 6, 1, 8, 4, 7, 3, 5 }, 
														{ 1, 3, 4, 5, 7, 6, 8, 9, 2 },
														{ 5, 7, 8, 9, 3, 2, 1, 6, 4 } });
			break;
		case 3:
			temp.template = new Grille(9,9,new int[][]{ { 8, 0, 0, 4, 0, 3, 0, 0, 1 }, 
														{ 0, 0, 0, 8, 0, 1, 0, 2, 0 },
														{ 0, 0, 0, 0, 9, 0, 0, 0, 0 }, 
														{ 0, 1, 0, 6, 0, 0, 9, 0, 0 }, 
														{ 0, 0, 0, 0, 0, 8, 0, 0, 0 },
														{ 0, 0, 0, 0, 5, 0, 0, 0, 3 }, 
														{ 0, 2, 6, 0, 0, 0, 0, 0, 5 }, 
														{ 1, 3, 0, 0, 7, 0, 0, 0, 2 },
														{ 0, 7, 0, 9, 0, 0, 1, 0, 4 } });

			temp.solution = new Grille(9,9,new int[][]{ { 8, 6, 9, 4, 2, 3, 5, 7, 1 }, 
														{ 7, 5, 3, 8, 6, 1, 4, 2, 9 },
														{ 2, 4, 1, 7, 9, 5, 3, 8, 6 }, 
														{ 3, 1, 2, 6, 4, 7, 9, 5, 8 }, 
														{ 6, 9, 5, 3, 1, 8, 2, 4, 7 },
														{ 4, 8, 7, 2, 5, 9, 6, 1, 3 }, 
														{ 9, 2, 6, 1, 8, 4, 7, 3, 5 }, 
														{ 1, 3, 4, 5, 7, 6, 8, 9, 2 },
														{ 5, 7, 8, 9, 3, 2, 1, 6, 4 } });
			break;
		default:
			temp.template = new Grille(9,9,new int[][]{ { 8, 0, 0, 4, 0, 3, 0, 0, 1 }, 
														{ 0, 0, 0, 8, 0, 1, 0, 2, 0 },
														{ 0, 0, 0, 0, 9, 0, 0, 0, 0 }, 
														{ 0, 1, 0, 6, 0, 0, 9, 0, 0 }, 
														{ 0, 0, 0, 0, 0, 8, 0, 0, 0 },
														{ 0, 0, 0, 0, 5, 0, 0, 0, 3 }, 
														{ 0, 2, 6, 0, 0, 0, 0, 0, 5 }, 
														{ 1, 3, 0, 0, 7, 0, 0, 0, 2 },
														{ 0, 7, 0, 9, 0, 0, 1, 0, 4 } });

			temp.solution = new Grille(9,9,new int[][]{ { 8, 6, 9, 4, 2, 3, 5, 7, 1 }, 
														{ 7, 5, 3, 8, 6, 1, 4, 2, 9 },
														{ 2, 4, 1, 7, 9, 5, 3, 8, 6 }, 
														{ 3, 1, 2, 6, 4, 7, 9, 5, 8 }, 
														{ 6, 9, 5, 3, 1, 8, 2, 4, 7 },
														{ 4, 8, 7, 2, 5, 9, 6, 1, 3 }, 
														{ 9, 2, 6, 1, 8, 4, 7, 3, 5 }, 
														{ 1, 3, 4, 5, 7, 6, 8, 9, 2 },
														{ 5, 7, 8, 9, 3, 2, 1, 6, 4 } });
			break;
		}
		return temp;
	}
	
	/*
	
	
	
	
	
	
	public static coupleSudoku facile1 = new coupleSudoku(new int[][]{ { 8, 0, 0, 4, 0, 3, 0, 0, 1 }, { 0, 0, 0, 8, 0, 1, 0, 2, 0 },
		{ 0, 0, 0, 0, 9, 0, 0, 0, 0 }, { 0, 1, 0, 6, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 8, 0, 0, 0 },
		{ 0, 0, 0, 0, 5, 0, 0, 0, 3 }, { 0, 2, 6, 0, 0, 0, 0, 0, 5 }, { 1, 3, 0, 0, 7, 0, 0, 0, 2 },
		{ 0, 7, 0, 9, 0, 0, 1, 0, 4 } }, new int[][] 
			{ { 8, 6, 9, 4, 2, 3, 5, 7, 1 }, { 7, 5, 3, 8, 6, 1, 4, 2, 9 },
			{ 2, 4, 1, 7, 9, 5, 3, 8, 6 }, { 3, 1, 2, 6, 4, 7, 9, 5, 8 }, { 6, 9, 5, 3, 1, 8, 2, 4, 7 },
			{ 4, 8, 7, 2, 5, 9, 6, 1, 3 }, { 9, 2, 6, 1, 8, 4, 7, 3, 5 }, { 1, 3, 4, 5, 7, 6, 8, 9, 2 },
			{ 5, 7, 8, 9, 3, 2, 1, 6, 4 } });
	public static coupleSudoku moyen1;
	public static coupleSudoku difficile1;

	public static void setCouple(int difficulte) {
		switch (difficulte) {
		case 1:
			int[][] templateMatF = { { 8, 0, 0, 4, 0, 3, 0, 0, 1 }, { 0, 0, 0, 8, 0, 1, 0, 2, 0 },
					{ 0, 0, 0, 0, 9, 0, 0, 0, 0 }, { 0, 1, 0, 6, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 8, 0, 0, 0 },
					{ 0, 0, 0, 0, 5, 0, 0, 0, 3 }, { 0, 2, 6, 0, 0, 0, 0, 0, 5 }, { 1, 3, 0, 0, 7, 0, 0, 0, 2 },
					{ 0, 7, 0, 9, 0, 0, 1, 0, 4 } };

			Grille templateF = new Grille(9, 9, templateMatF);
			Grille solutionF = new Grille(9, 9, new int[][]{ { 8, 6, 9, 4, 2, 3, 5, 7, 1 }, { 7, 5, 3, 8, 6, 1, 4, 2, 9 },
				{ 2, 4, 1, 7, 9, 5, 3, 8, 6 }, { 3, 1, 2, 6, 4, 7, 9, 5, 8 }, { 6, 9, 5, 3, 1, 8, 2, 4, 7 },
				{ 4, 8, 7, 2, 5, 9, 6, 1, 3 }, { 9, 2, 6, 1, 8, 4, 7, 3, 5 }, { 1, 3, 4, 5, 7, 6, 8, 9, 2 },
				{ 5, 7, 8, 9, 3, 2, 1, 6, 4 } });

			templateSudoku.facile1.setSolution(solutionF);
			templateSudoku.facile1.setTemplate(templateF);

			break;
		case 2:
			int[][] templateMatM = { { 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 7, 0, 0, 0, 0, 9, 0, 0, 0 },
					{ 0, 3, 0, 7, 0, 8, 0, 2, 0 }, { 0, 9, 5, 0, 0, 7, 0, 0, 6 }, { 0, 0, 0, 0, 0, 2, 0, 0, 0 },
					{ 1, 0, 0, 0, 4, 0, 0, 0, 0 }, { 6, 0, 0, 2, 0, 0, 5, 0, 0 }, { 0, 0, 2, 0, 0, 0, 0, 9, 8 },
					{ 3, 0, 4, 6, 0, 0, 2, 0, 0 } };

			Grille templateM = new Grille(9, 9, templateMatM);
			int[][] soluceMatM = { { 9, 5, 8, 1, 2, 6, 3, 7, 4 }, { 7, 2, 1, 4, 3, 9, 8, 6, 5 },
					{ 4, 3, 6, 7, 5, 8, 1, 2, 9 }, { 2, 9, 5, 8, 1, 7, 4, 3, 6 }, { 8, 4, 3, 9, 6, 2, 7, 5, 1 },
					{ 1, 6, 7, 5, 4, 3, 9, 8, 2 }, { 6, 7, 9, 2, 8, 1, 5, 4, 3 }, { 5, 1, 2, 3, 7, 4, 6, 9, 8 },
					{ 3, 8, 4, 6, 9, 5, 2, 1, 7 } };
			Grille solutionM = new Grille(9, 9, soluceMatM);

			templateSudoku.moyen1.setSolution(solutionM);
			templateSudoku.moyen1.setTemplate(templateM);
			break;
		case 3:
			int[][] templateMatD = { { 0, 0, 0, 8, 0, 0, 0, 7, 0 }, { 0, 0, 0, 0, 0, 0, 8, 0, 0 },
					{ 0, 3, 0, 7, 5, 0, 0, 0, 4 }, { 0, 0, 9, 0, 2, 0, 0, 3, 0 }, { 0, 8, 0, 4, 1, 9, 0, 0, 0 },
					{ 0, 5, 0, 0, 0, 0, 4, 0, 0 }, { 3, 0, 0, 0, 0, 0, 5, 0, 6 }, { 0, 0, 0, 0, 9, 0, 7, 0, 8 },
					{ 0, 1, 6, 0, 8, 0, 0, 0, 0 } };

			Grille templateD = new Grille(9, 9, templateMatD);
			int[][] soluce = { { 9, 6, 5, 8, 4, 1, 3, 7, 2 }, { 1, 4, 7, 9, 3, 2, 8, 6, 5 },
					{ 8, 3, 2, 7, 5, 6, 1, 9, 4 }, { 4, 7, 9, 5, 2, 8, 6, 3, 1 }, { 6, 8, 3, 4, 1, 9, 2, 5, 7 },
					{ 2, 5, 1, 3, 6, 7, 4, 8, 9 }, { 3, 9, 8, 1, 7, 4, 5, 2, 6 }, { 5, 2, 4, 6, 9, 3, 7, 1, 8 },
					{ 7, 1, 6, 2, 8, 5, 9, 4, 3 } };
			Grille solutionD = new Grille(9, 9, soluce);

			templateSudoku.difficile1.setSolution(solutionD);
			templateSudoku.difficile1.setTemplate(templateD);
			break;
		}

	}*/
	
	public static int[][] test() {
		return new int[][]{ { 9, 6, 5, 8, 0, 1, 3, 7, 2 }, { 1, 4, 7, 9, 3, 2, 8, 6, 5 },
			{ 8, 3, 2, 7, 5, 6, 1, 9, 4 }, { 4, 7, 9, 5, 2, 8, 6, 3, 1 }, { 6, 8, 3, 4, 1, 9, 2, 5, 7 },
			{ 2, 5, 1, 3, 6, 7, 4, 8, 9 }, { 3, 9, 8, 1, 7, 4, 5, 2, 6 }, { 5, 2, 4, 6, 9, 3, 7, 1, 8 },
			{ 7, 1, 6, 2, 8, 5, 9, 4, 3 } };
	}
	
	public static int[][] test2() {
		return new int[][]{ { 9, 6, 5, 8, 4, 1, 3, 7, 2 }, { 1, 4, 7, 9, 3, 2, 8, 6, 5 },
			{ 8, 3, 2, 7, 5, 6, 1, 9, 4 }, { 4, 7, 9, 5, 2, 8, 6, 3, 1 }, { 6, 8, 3, 4, 1, 9, 2, 5, 7 },
			{ 2, 5, 1, 3, 6, 7, 4, 8, 9 }, { 3, 9, 8, 1, 7, 4, 5, 2, 6 }, { 5, 2, 4, 6, 9, 3, 7, 1, 8 },
			{ 7, 1, 6, 2, 8, 5, 9, 4, 3 } };
	}
}