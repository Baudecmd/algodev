package bataille;

import commun.Joueur;
import commun.Partie;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
import java.util.Scanner;

public class Bataille implements Partie {
=======

public class Bataille implements Partie {
    private final String fileName = "scoreboardBataille.ser";
    private JoueurBataille j1, j2;
>>>>>>> bataillenavale

    JoueurBataille2 j1,j2;
    boolean firstTurn=true;

<<<<<<< HEAD
    public JoueurBataille2 getJ1() {
        return j1;
    }

    public void setJ1(JoueurBataille2 j1) {
        this.j1 = j1;
    }

    public JoueurBataille2 getJ2() {
        return j2;
    }

    public void setJ2(JoueurBataille2 j2) {
        this.j2 = j2;
    }

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    public Bataille(JoueurBataille2 j1, JoueurBataille2 j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

    public JoueurBataille2 retournerGagnant(){
        if(j1.getListeBateaux().isEmpty())  //si la liste des bateaux du joueur 1 est vide...
            return j2;    //...c'est l'autre joueur qui gagne
        return j1;
    }

    public Boolean partieFinie(){
        if(!j1.getListeBateaux().isEmpty() && !j2.getListeBateaux().isEmpty())      //un bateau détruit est retiré de la liste des bateaux du joueur
            return false;
        return true;
    }

    public void tourSuivant(){
        int i=-1,j=-1;
        if(firstTurn){
            j1.placement();
            j2.placement();
            firstTurn=false;
        }
        else{
            //récupération des coordonnées du tir contre le j2 via l'interface
            j2.tir(i, j);
            //récupération des coordonnées du tir contre le j1 via l'interface
            j1.tir(i, j);
        }
    }

    // *** Tests *** //

    public void tourSuivantTest(JoueurBataille2 j3,JoueurBataille2 j4){
        int i,j;
        Scanner sc=new Scanner(System.in);
        if(firstTurn){
            System.out.println(j3.getNom() +", veuillez entrer les coordonnées i et j de l'origine de votre bateau");
            i=sc.nextInt();
            j=sc.nextInt();
            j3.placementTest(i,j);
            System.out.println(j4.getNom() +", veuillez entrer les coordonnées i et j de l'origine de votre bateau");
            i=sc.nextInt();
            j=sc.nextInt();
            j4.placementTest(i,j);
            firstTurn=false;
        }
        else{
            //récupération des coordonnées du tir contre le j2 via l'interface
            System.out.println(j3.getNom() + ", où souhaitez-vous tirer?");
            i=sc.nextInt();
            j=sc.nextInt();
            j4.tir(i, j);
            if(this.partieFinie())  //la partie peut s'arrêter à la fin du tour du j1. Dans ce cas, pas besoin de demander au j2 de jouer
                return;
            //récupération des coordonnées du tir contre le j1 via l'interface
            System.out.println(j4.getNom() + ", où souhaitez-vous tirer?");
            i=sc.nextInt();
            j=sc.nextInt();
            j3.tir(i, j);
        }
    }

    public void afficheGagnant(JoueurBataille2 joueur){
        System.out.println("Le gagnant est:" + joueur.getNom());
    }

    public static void main(String[] args) {
        //Pour le test, on ne placera qu'un seul bateau
        JoueurBataille2 j3=new JoueurBataille2("Joffrey");
        JoueurBataille2 j4=new JoueurBataille2("Conrad");
        JoueurBataille2 j5;
        Scanner sc=new Scanner(System.in);
        Bataille bataille=new Bataille(j3,j4);
        do{     //il faut rentrer obligatoirement au moins une fois dans la boucle, car au début, la liste des bateaux de chaque joueur est vide également
            bataille.tourSuivantTest(j3,j4 );
        }while(!bataille.partieFinie());
        j5=bataille.retournerGagnant();
        bataille.afficheGagnant(j5);
=======
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
        Partie.reset("scoreboardBataille.ser");
        Partie.initialiser("scoreboardBataille.ser");
        ArrayList<Joueur> L = Partie.recupererScore("scoreboardBataille.ser");
        System.out.println(L.toString());
>>>>>>> bataillenavale
    }
}

