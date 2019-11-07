package sudoku;

import commun.Grille;

public class coupleSudoku {
	public Grille template;
	private Grille solution;

	public coupleSudoku() {
		super();
		this.template = new Grille(9, 9);
		this.solution = new Grille(9, 9);
	}

	public coupleSudoku(int[][] template, int[][] solution) {
		this.template = new Grille(9, 9);
		this.solution = new Grille(9, 9);
		this.solution.setMatrice(solution);
		this.template.setMatrice(template);
	}

	public Grille getTemplate() {
		return template;
	}

	public void setTemplate(Grille template) {
		this.template = template;
	}

	public Grille getSolution() {
		return solution;
	}

	public void setSolution(Grille solution) {
		this.solution = solution;
	}

}
