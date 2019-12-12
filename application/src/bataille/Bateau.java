package bataille;

import commun.Joueur;

import java.util.ArrayList;

public class Bateau {
    private String name;
    private ArrayList<Case> tabCases; //tableau des cases sur lesquelles est placé le bateau
    private Joueur joueur;

    public Bateau(ArrayList<Case> tabCases, Joueur joueur) {
        this.tabCases = tabCases;
        this.joueur = joueur;
        if (tabCases.size() == 2) {
            this.name = "Torpilleur";
        } else {
            if (tabCases.size() == 3) {
                this.name = "Destroyeur";
            } else {
                if (tabCases.size() == 4) {
                    this.name = "Cuirasse";
                } else
                    this.name = "Porte-avion";
            }
        }
    }

    String getName() {
        return name;
    }

    ArrayList<Case> getTabCases() {
        return tabCases;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        String tabCasesS = "";
        for (Case c : this.tabCases) {
            tabCasesS += " " + c.toString();
        }
        return "Bateau{" +
                "name='" + name + '\'' +
                ", tabCases=" + tabCasesS +
                ", joueur=" + joueur.getNom() +
                '}';
    }

}
