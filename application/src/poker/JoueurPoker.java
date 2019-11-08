package poker;

import commun.Joueur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JoueurPoker extends Joueur {

    private ArrayList<Carte>mainJoueur=new ArrayList<>();
    private Combinaisons combinaison;

    public ArrayList<Carte> getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(ArrayList<Carte> mainJoueur) {
        this.mainJoueur = mainJoueur;
    }

    public Combinaisons getCombinaison() {
        return combinaison;
    }

    public JoueurPoker(String nom, ArrayList<Carte> mainJoueur) {
        super(nom);
        this.mainJoueur = mainJoueur;
    }

    public boolean memeCouleur(){
        Carte temp=mainJoueur.get(0);
        for(Carte a:mainJoueur){
            if(a.getCouleur()!=temp.getCouleur())
                return false;
        }
        return true;
    }

    public int nbCartesMemeHauteur(){
        int total=-1;   //on a une itération de trop
        Carte temp=mainJoueur.get(0);
        for(Carte a:mainJoueur){
            if(a.getHauteur()==temp.getHauteur())
                total+=1;
        }
        return total;
    }

    public void sortByValue(){
        Collections.sort(mainJoueur, Carte::compareTo);
        Collections.reverse(mainJoueur);
    }

    public boolean suite(){
        int i=0;
        sortByValue();
        while(i!=(mainJoueur.size()-2)){
            if(!(mainJoueur.get(i).getHauteur().getValue()==(mainJoueur.get(i+1).getHauteur().getValue()+1)))
                return false;
        }
        return true;
    }

    public boolean checkFull(){
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
        if(temp.get(0).getHauteur()==temp.get(1).getHauteur())
            return true;
        return false;
    }

    public boolean checkDouble(){
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
        if(temp.get(0).getHauteur()==temp.get(1).getHauteur() || temp.get(1).getHauteur()==temp.get(2).getHauteur())
            return true;
        return false;
    }

    public void combinaisonMain(){
        int memeHauteur=nbCartesMemeHauteur();
        if(memeCouleur()){  //cas des quinte flush ou d'une couleur
            if(suite()){    //quinte flush
                for(Carte a:mainJoueur){
                    if(a.getHauteur()==Hauteurs.as)   //il y a un as: quinte flush royale
                        combinaison=Combinaisons.quinte_flush_royale;
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
}
