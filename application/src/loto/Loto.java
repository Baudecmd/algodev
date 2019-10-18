package loto;

import commun.Grille;
import commun.Joueur;
import commun.Partie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loto implements Partie { // Le Loto est composé d'une map associant un joueur et une grille, un Tirage et la liste des numéros déjà tiré
    private Map<Joueur, Grille> joueurs;
    private Tirage tirage;
    private List<Integer> tiree;

    public Loto(List<Joueur> L) { //Constructeur du loto, avec pour entrée la liste des joueurs de la partie envoyée par le menu
        this.joueurs = new HashMap<>();
        for (Joueur j: L){
            joueurs.put(j, new Grille(3,9));
            joueurs.get(j).initGrid2();
        }
        this.tiree = new ArrayList<>();
        this.tirage = new Tirage(L.size(), 0);
        Partie.initialiser("resources/scoreboardLoto.ser");
    }

    public Joueur retournerGagnant() { //Renvoie le joueur qui a gagné la partie 
        for (Joueur j : joueurs.keySet())
            if (joueurs.get(j).isContained(tiree)){
                Partie.ajouterScore("resources/scoreboardLoto.ser",j);
                return j;
            }
        return null;
    }

    public Boolean partieFinie() { //Verifie si la partie est finie
        for (Joueur j : joueurs.keySet())
            if (joueurs.get(j).isContained(tiree)) return true;
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
