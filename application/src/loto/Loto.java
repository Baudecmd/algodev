package loto;

import commun.Grille;
import commun.Joueur;
import commun.Partie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loto implements Partie { // Le Loto est composé d'une map associant un joueur et une grille, un Tirage et la liste des numéros déjà tiré
    private List<JoueurLoto> joueurs;
    private Tirage tirage;
    private List<Integer> tiree;

    public Loto(List<Joueur> L) { //Constructeur du loto, avec pour entrée la liste des joueurs de la partie envoyée par le menu
        this.joueurs = new ArrayList<>();
        for (Joueur j: L){
            //JoueurLoto jL = (JoueurLoto) j;
        	JoueurLoto jL = new JoueurLoto(j.getNom());
            jL.initGrille();
            joueurs.add(jL);
        }
        this.tiree = new ArrayList<>();
        this.tirage = new Tirage(L.size(), 0);
        Partie.initialiser("resources/scoreboardLoto.ser");
    }

    public List<JoueurLoto> getJoueurs() {
        return joueurs;
    }

    public Tirage getTirage() {
        return tirage;
    }

    public List<Integer> getTiree() {
        return tiree;
    }

    public JoueurLoto retournerGagnant() { //Renvoie le joueur qui a gagné la partie
        for (JoueurLoto j : joueurs)
            if (j.getGrille().isContained(tiree)){
                Partie.ajouterScore("resources/scoreboardLoto.ser",j);
                return j;
            }
        return null;
    }

    public Boolean partieFinie() { //Verifie si la partie est finie
        for (JoueurLoto j : joueurs)
            if (j.getGrille().isContained(tiree)) return true;
        return false;
    }

    public void tourSuivant() { //Passe au tour suivant en tirant un nouveau numéro
        tiree.add(tirage.getTokenToGame());
    }

    public static void main(String[] args) { //Test de la gestion des scores
        Joueur j1 = new Joueur("Joffrey");
        Joueur j2 = new Joueur("Gaby");
        Joueur j3 = new Joueur("Romane");
        Joueur j4 = new Joueur("Lilian");
        Joueur j5 = new Joueur("Conrad");

        j1.setScore(1);
        j2.setScore(0);
        j3.setScore(2);
        j4.setScore(0);
        j5.setScore(0);

        Partie.ajouterVictoire("resources/scoreboardLoto.ser",j1);
        Partie.ajouterVictoire("resources/scoreboardLoto.ser",j2);
        Partie.ajouterVictoire("resources/scoreboardLoto.ser",j3);
        Partie.ajouterVictoire("resources/scoreboardLoto.ser",j4);
        Partie.ajouterVictoire("resources/scoreboardLoto.ser",j5);
        ArrayList<Joueur> L = Partie.recupererScore("resources/scoreboardLoto.ser");
        System.out.println(L.toString());
    }
}
