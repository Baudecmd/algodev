package bataille;

import commun.Joueur;

import java.util.ArrayList;

public class Bateau {
    private String name;
    private ArrayList<Case> tabCases;     //tableau des cases sur lesquelles est placé le bateau
    private boolean status;     //false si détruit
    private Joueur joueur;

    public String getName() {
        return name;
    }

    public ArrayList<Case> getTabCases() {
        return tabCases;
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

    @Override
    public String toString() {
        String tabCasesS="";
        for (int i=0;i<this.tabCases.size();i++){
            tabCasesS+=" "+ tabCases.get(i).toString();
        }
        return "Bateau{" +
                "name='" + name + '\'' +
                ", tabCases=" + tabCasesS+
                ", joueur=" + joueur.getNom()+
                '}';
    }

    public Bateau(ArrayList<Case> tabCases, Joueur joueur) {
        this.tabCases = tabCases;
        this.joueur=joueur;
        if(tabCases.size()==2){
            this.name="Torpilleur";
        }
        else{
            if(tabCases.size()==3){
                this.name="Destroyeur";
            }
            else{
                if(tabCases.size()==4){
                    this.name="Cuirasse";
                }
                else
                    this.name="Porte-avion";
            }
        }
        this.status=true;
    }

}
