package bataille;

import commun.Joueur;

import java.util.ArrayList;

public class Bateau {

    private String name;
    private ArrayList<Case> tabCases = new ArrayList<Case>();     //tableau des cases sur lesquelles est placé le bateau
    private boolean status;     //false si détruit
    private Joueur joueur;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Case> getTabCases() {
        return tabCases;
    }

    public void setTabCases(ArrayList<Case> tabCases) {
        this.tabCases = tabCases;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Bateau(String name, ArrayList<Case> tabCases, Joueur joueur) {
        this.name = name;
        this.tabCases = tabCases;
        this.joueur=joueur;
        this.status=true;
    }

    public Bateau(ArrayList<Case> tabCases,Joueur joueur) {
        this.tabCases = tabCases;
        this.joueur=joueur;
        if(tabCases.size()==2){
            this.name="Torpilleur";
        }
        else{
            if(tabCases.size()==3){
                this.name="Destroyer";
            }
            else{
                if(tabCases.size()==4){
                    this.name="Croiseur";
                }
                else
                    this.name="Porte-Avions";
            }
        }
        this.status=true;
    }

}
