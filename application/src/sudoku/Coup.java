package sudoku;

public class Coup {
	private int x;
	private int y;
	private int valeurRentree;
	private static int num = 0 ;
	
	public Coup() {
		super();
		this.x = -1;
		this.y = -1;
		this.valeurRentree = 0;
		coupSuivant();
	}
	
	public Coup(int _x, int _y, int _val) {
		super();
		this.x = _x;
		this.y = _y;
		this.valeurRentree = _val;
		coupSuivant();
	}


	public void coupSuivant() {
		Coup.num++;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getValeurRentree() {
		return valeurRentree;
	}
	public void setValeurRentree(int val) {
		this.valeurRentree = val;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	

}
