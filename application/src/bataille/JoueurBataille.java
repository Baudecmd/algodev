package bataille;

import commun.Joueur;

import java.util.ArrayList;
import java.util.Scanner;

public class JoueurBataille extends Joueur {
    //version sans tabCases
    private ArrayList<Bateau>listeBateaux = new ArrayList<>();
    private ArrayList<Case>alreadyChecked = new ArrayList<>();

    public JoueurBataille(String nom) {
        super(nom);
    }

    public JoueurBataille(Joueur joueur) {
        super(joueur.getNom());
    }

    public JoueurBataille(String nom, ArrayList<Bateau> listeBateaux) {
        super(nom);
        this.listeBateaux = listeBateaux;
    }

    public ArrayList<Bateau> getListeBateaux() {
        return listeBateaux;
    }

    public void setListeBateaux(ArrayList<Bateau> listeBateaux) {
        this.listeBateaux = listeBateaux;
    }

    public ArrayList<Case> getAlreadyChecked() {
        return alreadyChecked;
    }

    public void setAlreadyChecked(ArrayList<Case> alreadyChecked) {
        this.alreadyChecked = alreadyChecked;
    }

    private boolean canBePlacedByBoat(ArrayList<Case> listeCases, ArrayList<Bateau>listeBateauxCrees) {
        for (Bateau b : listeBateauxCrees) {
            for (Case c : b.getTabCases()) {
                if (listeCases.contains(c))
                    return false;
            }
        }
        return true;
    }

    private boolean coherentValues(ArrayList<Case> listeCases){
        for(Case c:listeCases){
            if(!(0<=c.getI()) || !(c.getI()<10) || !(0<=c.getJ()) || !(c.getJ()<10))
                return false;
        }
        return true;
    }

    private ArrayList<Case> getCases(int nbCases, ArrayList<Bateau>listeBateauxCrees){
        ArrayList<Case>tabCases=new ArrayList<>();
        Case origin=new Case(-1,-1);
        Case temp;
        int i;
        boolean horizontal=true;    //false: bateau placé verticalement
        boolean direction=true;     //true: droite ou haut, false: gauche ou bas
        //récupération de l'origine via l'interface : temp=...
        tabCases.add(origin);
        //récupération de l'orientation du bateau
        for(i=1;i<nbCases;i++){
            if(horizontal){
                if(direction){
                    temp=new Case(origin.getI()+i, origin.getJ());
                }
                else{
                    temp=new Case(origin.getI()-i, origin.getJ());
                }
            }
            else{
                if(direction){
                    temp=new Case (origin.getI(), origin.getJ()+i);
                }
                else{
                    temp=new Case(origin.getI(), origin.getJ()-i);
                }
            }
            tabCases.add(temp);
        }
        return tabCases;
    }

    public void placement(){        //placement des bateaux sur la grille
        Bateau temp;
        ArrayList<Case> listeCases;      //liste des cases où doit être placé le bateau
        ArrayList<Bateau>listeBateauxCrees=new ArrayList<>();
        int count=5;
        while(count > 0){
            if(count == 2 || count ==3)
                listeCases=getCases(3,listeBateauxCrees);   //destroyer
            else {
                if (count == 1)
                    listeCases = getCases(2, listeBateauxCrees);    //torpilleur
                else
                    listeCases=getCases(count,listeBateauxCrees);   //porte-avions ou croiseur
            }
            if(canBePlacedByBoat(listeCases,listeBateauxCrees) && coherentValues(listeCases)){
                temp=new Bateau(listeCases, this);
                listeBateauxCrees.add(temp);
                listeBateaux.add(temp);
                count--;
            }
            //Sinon, indiquer au joueur que son placement est impossible
        }
    }

    public boolean tir(int i,int j){    //booléen pour indiquer si un bateau a été touché ou non
        boolean touche=false;           // ATTENTION! Cette fonction n'effectue pas un tir CONTRE le joueur adverse (sinon, celui-ci serait passé en paramètre). C'est au joueur 2 de faire appel à cette fonction du joueur 1 pour effectuer son tir contre celui-ci.
        Bateau removed=null;
        Case temp=new Case(i,j);
        if(alreadyChecked.contains(temp)){
            System.out.println("Case déjà vérifiée");
            return touche;
        }
        if(0<=i && i<10 && 0<=j && j<10){
            for(Bateau b:listeBateaux){   //la case est existante
                for(Case c:b.getTabCases()){
                    if(c.getI()==i && c.getJ()==j){
                        touche=true;
                        System.out.println("Bateau touché!");
                        b.getTabCases().remove(c);
                        if(b.getTabCases().isEmpty())
                            removed=b;
                        break;  //pas la peine de vérifier les cases restantes
                    }
                }
                this.alreadyChecked.add(temp);
            }
            if(removed!=null){
                this.getListeBateaux().remove(removed);
                System.out.println("Bateau coulé!");
            }
        }
        else
            System.out.println("Case invalide");
        return touche;
    }

    // *** Tests *** //

    private ArrayList<Case> getCasesTest(int i, int j){
        ArrayList<Case>tabCases=new ArrayList<>();
        int nbCases=3;
        int k;
        Case origin=new Case(i,j);
        Case temp;
        boolean horizontal=true;    //false: bateau placé verticalement
        boolean direction=true;     //true: droite ou haut, false: gauche ou bas
        tabCases.add(origin);
        for(k=1;k<nbCases;k++){
            if(horizontal){
                if(direction){
                    temp=new Case(origin.getI()+k, origin.getJ());
                }
                else{
                    temp=new Case(origin.getI()-k, origin.getJ());
                }
            }
            else{
                if(direction){
                    temp=new Case (origin.getI(), origin.getJ()+k);
                }
                else{
                    temp=new Case(origin.getI(), origin.getJ()-k);
                }
            }
            tabCases.add(temp);
        }
        return tabCases;
    }

    public void placementTest(int i,int j){        //placement des bateaux sur la grille
        Bateau temp;
        Scanner sc=new Scanner(System.in);
        ArrayList<Case>listeCases;      //liste des cases où doit être placé le bateau
        ArrayList<Bateau>listeBateauxCrees=new ArrayList<>();
        int count=1;
        while(count > 0){
            listeCases=getCasesTest(i,j);
            if(canBePlacedByBoat(listeCases,listeBateauxCrees) && coherentValuesTest(listeCases)){
                temp=new Bateau(listeCases, this);
                listeBateauxCrees.add(temp);
                listeBateaux.add(temp);
                count--;
            }
            else{
                i=sc.nextInt();
                j=sc.nextInt();
            }
        }
    }

    public void afficherBateau(){
        for(Bateau b:listeBateaux){
            for(Case c:b.getTabCases()){
                System.out.println(c.getI() + " " + c.getJ());
            }
        }
    }

    private boolean coherentValuesTest(ArrayList<Case> listeCases){
        for(Case c:listeCases){
            if(!(0<=c.getI()) || !(c.getI()<10) || !(0<=c.getJ()) || !(c.getJ()<10)){
                System.out.println("Valeurs incoherentes");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        JoueurBataille joueur = new JoueurBataille("germain");
        joueur.placementTest(9,3);
        joueur.afficherBateau();
        joueur.tir(6,9);
        System.out.println();
        joueur.afficherBateau();
        joueur.tir(5, 9);
        System.out.println();
        joueur.afficherBateau();
        joueur.tir(7, 9);
    }
}
