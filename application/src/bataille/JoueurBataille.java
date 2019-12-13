package bataille;

import commun.Joueur;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;

public class JoueurBataille extends Joueur{
=======

public class JoueurBataille extends Joueur {
    private ArrayList<Bateau> listeBateaux = new ArrayList<>();
    private ArrayList<Case> alreadyChecked = new ArrayList<>();
>>>>>>> bataillenavale

    private Case[][]tabCases=new Case[10][10];
    private ArrayList<Bateau>tabBateaux=new ArrayList<>();
    private ArrayList<Case>alreadyChecked=new ArrayList<>();    //liste des cases sur lesquelles le joueur a déjà tiré

    public Case[][] getTabCases() {
        return tabCases;
    }

<<<<<<< HEAD
    public void setTabCases(Case[][] tabCases) {
        this.tabCases = tabCases;
    }

    public ArrayList<Bateau> getTabBateaux() {
        return tabBateaux;
    }

    public void setTabBateaux(ArrayList<Bateau> tabBateaux) {
        this.tabBateaux = tabBateaux;
    }

    public ArrayList<Case> getAlreadyChecked() {
        return alreadyChecked;
    }

    public void setAlreadyChecked(ArrayList<Case> alreadyChecked) {
        this.alreadyChecked = alreadyChecked;
    }

    public JoueurBataille(String nom, Case[][] tabCases, ArrayList<Bateau> tabBateaux) {
        super(nom);
        this.tabCases = tabCases;
        this.tabBateaux=tabBateaux;
    }

    public JoueurBataille(String nom) {
        super(nom);
        initCases();
    }

    private void initCases(){
        int i,j;
        Case newCase=new Case(-1, -1);
        for(i=0;i<10;i++){
            for(j=0;j<10;j++){
                newCase.setI(i);
                newCase.setJ(j);
                tabCases[i][j]=newCase;
            }
        }
    }

    private void updateGrid(List<Bateau> listeBateauxCrees){
        for(Bateau b:listeBateauxCrees){
            for(Case c:b.getTabCases()){
                for(int i=0;i<10;i++){
                    for(int j=0;j<10;j++){
                        if(tabCases[i][j].equals(c))
                            tabCases[i][j].setContains(true);
                    }
                }
            }
        }
    }

    private boolean canBePlacedByBoat(List<Case> listeCases, List<Bateau>listeBateauxCrees){
        for(Bateau b:listeBateauxCrees){
            for(Case c:b.getTabCases()){
                if(listeCases.contains(c))
                    return false;
            }
        }
        return true;
    }

    private boolean canBePlaced(Case origin, boolean horizontal, boolean direction, int nbCases){
        int i;
        if(tabCases[origin.getI()][origin.getJ()].isContains()){
            return false;
        }
        for(i=1;i<nbCases;i++){
            if(horizontal){
                if(direction){
                    if(tabCases[origin.getI()+i][origin.getJ()].isContains())
                        return false;
                }
                else{
                    if(tabCases[origin.getI()-i][origin.getJ()].isContains())
                        return false;
                }
            }
            else{
                if(direction){
                    if(tabCases[origin.getI()][origin.getJ()+i].isContains())
                        return false;
                }
                else{
                    if(tabCases[origin.getI()-i][origin.getJ()].isContains())
                        return false;
                }
            }
        }
        return true;
    }

    private ArrayList<Case> getCases(int nbCases){       //on place d'abord la première case, puis on choisit l'orientation, et le programme se charge de mettre les cases restantes
        ArrayList<Case>listeCases=new ArrayList<>();
        Case origin=new Case(-1,-1);
        Case temp=new Case(-1,-1);
        int i=nbCases;
        boolean horizontal=true;     //false : le bateau est placé à la verticale
        boolean direction=true;      //true : droite ou haut, sinon gauche ou bas
        //récupération des Cases via l'interface : temp=...
        listeCases.add(origin);
        //récupération de l'orientation du bateau
        //if(canBePlaced(origin, horizontal, direction, nbCases)){
            for(i=1;i<nbCases;i++){     //1 car la première case est déjà connue
                if(horizontal){
                    if(direction){
                        temp.setI(origin.getI()+i);
                    }
                    else{
                        temp.setI(origin.getI()-i);
                    }
                }
                else{
                    if(direction){
                        temp.setJ(origin.getJ()+i);
                    }
                    else{
                        temp.setJ(origin.getJ()-i);
                    }
                }
                listeCases.add(temp);
            }
            return listeCases;
       // }
        //else
            //return null;      //on ne renvoie rien si le bateau n'a pas pu être placé
    }

    public void placement(){        //placement sur une grille
        Bateau temp;
        ArrayList<Case>listeCases;    //liste des cases où doit être placé le bateau
        List<Bateau>listeBateauxCrees=new ArrayList<>();
        int count = 5;
        while(count>0){
            if(count == 5)
                listeCases=getCases(5);     //porte-avions
            else{
                if(count == 4)
                    listeCases=getCases(4);     //croiseur
                else{
                    if(count == 3 || count == 2)
                        listeCases=getCases(3);     //destroyer
                    else
                        listeCases=getCases(2);     //torpilleur
                }
            }
            if(canBePlacedByBoat(listeCases, listeBateauxCrees)){
                temp=new Bateau(listeCases, this);
                listeBateauxCrees.add(temp);
                tabBateaux.add(new Bateau(listeCases,this));
                count--;
            }
        }
        updateGrid(listeBateauxCrees);
    }

    public boolean tir(int i, int j){       //vérifie si le tir du joueur a touché ou non, et effectue les actions qui en découlent
        boolean touche=false;
        Case temp=new Case(i, j);
        if(alreadyChecked.contains(temp)){
            System.out.println("Case déjà vérifiée");
            return touche;
        }
        for(Bateau b:tabBateaux){
            if(0<=i && i<10 && 0<=j && j<10){   //la case est existante
                for(Case c:b.getTabCases()){
                    if(c.getI()==i && c.getJ()==j){
                        touche=true;
                        b.getTabCases().remove(c);
                        if(b.getTabCases().isEmpty())
                            this.getTabBateaux().remove(b);
=======
    JoueurBataille(Joueur joueur) {
        super(joueur.getNom());
    }

    ArrayList<Bateau> getListeBateaux() {
        return listeBateaux;
    }

    void setListeBateaux(ArrayList<Bateau> listeBateaux) {
        this.listeBateaux = listeBateaux;
    }

    /**
     * gère le tir sur le joueur en mettant à jour sa liste de bateaux
     *
     * @param i ligne du tir
     * @param j colonne du tir
     * @return true si le tir touche et false sinon
     */
    int tir(int i, int j) {    //booléen pour indiquer si un bateau a été touché ou non
        int touche = 0;           // ATTENTION! Cette fonction n'effectue pas un tir CONTRE le joueur adverse (sinon, celui-ci serait passé en paramètre). C'est au joueur 2 de faire appel à cette fonction du joueur 1 pour effectuer son tir contre celui-ci.
        Bateau removed = null;
        Case temp = new Case(i, j);
        if (alreadyChecked.contains(temp)) return touche;
        if (0 <= i && i < 10 && 0 <= j && j < 10) {
            for (Bateau b : listeBateaux) {   //la case est existante
                for (Case c : b.getTabCases()) {
                    if (c.getI() == i && c.getJ() == j) {
                        touche = 1;
                        b.getTabCases().remove(c);
                        if (b.getTabCases().isEmpty())
                            removed = b;
>>>>>>> bataillenavale
                        break;  //pas la peine de vérifier les cases restantes
                    }
                }
                this.alreadyChecked.add(temp);
            }
<<<<<<< HEAD
            else
                System.out.println("Case invalide");
        }
        return touche;
    }

    //optimisation possible: supprimer tabCases. Pour le placement, vérifier si les cases choisies par le joueur sont contenues dans un des navires, sinon, on peut les placer. Voir si ça ne gêne pas du côté de l'interface pour faciliter le code

}
=======
            if (removed != null) {
                this.getListeBateaux().remove(removed);
                touche = 2;
            }
        }
        return touche;
    }
}
>>>>>>> bataillenavale
