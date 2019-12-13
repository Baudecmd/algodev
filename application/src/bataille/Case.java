package bataille;

public class Case {
    private int i,j;

    Case(int i, int j){
        this.i=i;
        this.j=j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    boolean equals(Case a){
        if(this == a)
            return true;
        return this.i == a.getI() && this.j == a.getJ();
    }
<<<<<<< HEAD

    public Case(int i, int j, boolean contains) {
        this.i = i;
        this.j = j;
        this.contains=contains;
    }

    public Case(int i, int j){
        this.i=i;
        this.j=j;
        this.contains=false;     //par défaut, aucune pièce n'est présente sur la case
=======
    
    public String toString() {
    	return "i = "+i+" j = "+j;
>>>>>>> bataillenavale
    }
}
