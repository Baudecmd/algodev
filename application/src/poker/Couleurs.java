package poker;

public enum Couleurs {
    pique(1),
    trefle(2),
    carreau(3),
    coeur(4);

    private final int value;

    Couleurs(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
