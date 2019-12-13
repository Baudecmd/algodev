package bataille;

import commun.Joueur;
import commun.Partie;

import java.util.ArrayList;

public class Bataille implements Partie {
    public static final String fileName = "scoreboardBataille.ser";
    private JoueurBataille j1, j2;

    public Bataille(JoueurBataille j1, JoueurBataille j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

    JoueurBataille getJ1() {
        return j1;
    }

    JoueurBataille getJ2() {
        return j2;
    }

    String getFileName() {
        return fileName;
    }

    /**
     * cherche le joueur qui n'a plus de bateau
     *
     * @return ce joueur
     */
    JoueurBataille retournerGagnant() {
        if (j1.getListeBateaux().isEmpty()) return j2;
        return j1;
    }

    /**
     * cherche si la partie est finie, donc un si un des joueurs n'a plus de bateau
     *
     * @return vrai si la partie est finie
     */
    public Boolean partieFinie() {
        return !(!j1.getListeBateaux().isEmpty() && !j2.getListeBateaux().isEmpty());   //un bateau détruit est retiré de la liste des bateaux du joueur
    }

    public static void main(String[] args) {
        Joueur j = new Joueur("Jacques");
        Partie.reset(fileName);
        Partie.initialiser(fileName);
        ArrayList<Joueur> L = Partie.recupererScore(fileName);
        System.out.println(L.toString());
    }
}