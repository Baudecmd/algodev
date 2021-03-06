package poker;

public enum Combinaisons {
    quinte_flush_royale(10),
    quinte_flush(9),
    carre(8),
    full(7),
    couleur(6),
    quinte(5),
    brelan(4),
    deux_paires(3),
    une_paire(2),
    plus_haute(1);

    private final int value;

    Combinaisons(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }

    /**
     * Renvoie vrai si la combinaison considérée est meilleure que celle à laquelle elle est comparée
     * @param other La combinaison à comparer
     * @return La valeur booléenne correspondant à la comparaison
     */
    public boolean isGreater(Combinaisons other){
        return value > other.getValue();
    }

}