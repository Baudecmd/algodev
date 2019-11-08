package poker;

public enum Hauteurs {
    un(1),
    deux(2),
    trois(3),
    quatre(4),
    cinq(5),
    six(6),
    sept(7),
    huit(8),
    neuf(9),
    dix(10),
    valet(11),
    dame(12),
    roi(13),
    as(14);

    private final int value;

    Hauteurs(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
