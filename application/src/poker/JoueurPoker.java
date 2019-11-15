package poker;

import commun.Joueur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class JoueurPoker extends Joueur {

    private ArrayList<Carte>mainJoueur=new ArrayList<>();
    private int somme;
    private int mise;
    private Combinaisons combinaison;
    private boolean smallBlind;
    private boolean tapis;

    public ArrayList<Carte> getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(ArrayList<Carte> mainJoueur) {
        this.mainJoueur = mainJoueur;
    }

    public Combinaisons getCombinaison() {
        return combinaison;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    public int getMise() {
        return mise;
    }

    public void setMise(int mise) {
        this.mise = mise;
    }

    public boolean isTapis() {
        return tapis;
    }

    public void setTapis(boolean tapis) {
        this.tapis = tapis;
    }

    public boolean isSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(boolean smallBlind) {
        this.smallBlind = smallBlind;
    }

    public JoueurPoker(String nom, ArrayList<Carte> mainJoueur, int somme) {
        super(nom);
        this.mainJoueur = mainJoueur;
        this.somme=somme;
        this.smallBlind=false;
        this.mise=0;
        this.tapis=false;
    }

    public JoueurPoker(JoueurPoker player){
        super(player.getNom());
        this.mainJoueur=player.mainJoueur;
        this.somme=player.somme;
        this.smallBlind=player.smallBlind;
        this.mise=player.mise;
        this.combinaison=player.combinaison;
        this.tapis=player.tapis;
    }

    public boolean canPlay(int miseEnCours){
        return miseEnCours <= somme;
    }

    public int actionJoueur(boolean hasChecked){   //true: le joueur précédent a check
        int choix=0;
        boolean correctValue=true;
        Scanner sc=new Scanner(System.in);
        while(correctValue){
            System.out.println(this.getNom() + ", quelle action souhaitez-vous faire?");  //on met le nom du joueur pour améliorer la lisibilité
            System.out.print("1.Miser 2.Suivre 3.Se Coucher");
            if(hasChecked){
                System.out.print(" 4.Check");
                System.out.println(" 5.Tapis");
            }
            else{
                System.out.print(" 4.Check (Impossible)");
                System.out.println(" 5.Tapis");
            }
            choix=sc.nextInt();
            if((!hasChecked && choix==4) || choix<1 || choix >6){
                System.out.println("Choix invalide");
            }
            else
                correctValue=false;
        }
        return choix;
    }

    private boolean memeCouleur(){
        Carte temp=mainJoueur.get(0);
        for(Carte a:mainJoueur){
            if(a.getCouleur()!=temp.getCouleur())
                return false;
        }
        return true;
    }

    private int nbCartesMemeHauteur(){
        int total=-1;   //on a une itération de trop
        Carte temp=mainJoueur.get(0);
        for(Carte a:mainJoueur){
            if(a.getHauteur()==temp.getHauteur())
                total+=1;
        }
        return total;
    }

    private void sortByValue(){
        Collections.sort(mainJoueur, Carte::compareTo);
        Collections.reverse(mainJoueur);
    }

    private boolean suite(){
        int i=0;
        sortByValue();
        while(i!=(mainJoueur.size()-2)){
            if(!(mainJoueur.get(i).getHauteur().getValue()==(mainJoueur.get(i+1).getHauteur().getValue()+1)))
                return false;
        }
        return true;
    }

    private boolean checkFull(){
        ArrayList<Carte>temp=mainJoueur;
        ArrayList<Carte>triple=new ArrayList<>();
        sortByValue();
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).getHauteur()==temp.get(i+1).getHauteur() && temp.get(i).getHauteur()==temp.get(i+2).getHauteur()){
                triple.add(temp.get(i));
                triple.add(temp.get(i+1));
                triple.add(temp.get(i+2));
                temp.remove(i);
                temp.remove(i+1);
                temp.remove(i+2);
                break;
            }
        }
        return temp.get(0).getHauteur() == temp.get(1).getHauteur();
    }

    private boolean checkDouble(){
        ArrayList<Carte>temp=mainJoueur;
        ArrayList<Carte>paire=new ArrayList<>();
        ArrayList<Carte>autres=new ArrayList<>();
        sortByValue();
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).getHauteur()==temp.get(i+1).getHauteur()){
                paire.add(temp.get(i));
                paire.add(temp.get(i+1));
                temp.remove(i);
                temp.remove(i+1);
                break;
            }
        }
        //note: les cartes ont été triées. Si il y a une seconde paire, les cartes sont forcément côte à côte
        return temp.get(0).getHauteur() == temp.get(1).getHauteur() || temp.get(1).getHauteur() == temp.get(2).getHauteur();
    }

    public void combinaisonMain(){
        int memeHauteur=nbCartesMemeHauteur();
        if(memeCouleur()){  //cas des quinte flush ou d'une couleur
            if(suite()){    //quinte flush
                for(Carte a:mainJoueur){
                    if(a.getHauteur()==Hauteurs.as){     //il y a un as: quinte flush royale
                        combinaison=Combinaisons.quinte_flush_royale;
                        break;
                    }
                }
                if(combinaison!=Combinaisons.quinte_flush_royale)
                    combinaison=Combinaisons.quinte_flush;
            }
            else
                combinaison=Combinaisons.couleur;
        }
        else{
            if(memeHauteur==3){     //cas d'un carré
                combinaison=Combinaisons.carre;
            }
            else{
                if(memeHauteur==2){
                    if(checkFull()){
                        combinaison=Combinaisons.full;
                    }
                    else
                        combinaison=Combinaisons.brelan;
                }
                else{
                    if(suite())
                        combinaison=Combinaisons.quinte;
                    else{
                        if(memeHauteur==1){ //au moins une paire
                            if(checkDouble())
                                combinaison=Combinaisons.deux_paires;
                            else
                                combinaison=Combinaisons.une_paire;
                        }
                        else
                            combinaison=Combinaisons.plus_haute;
                    }
                }
            }
        }
    }

    public ArrayList<ArrayList<Carte>> getAllCombinaisons(ArrayList<Carte>communityCards){
        ArrayList<ArrayList<Carte>>listeCombinaisons=new ArrayList<>();
        
        return listeCombinaisons;
    }

}
