package loto;

import commun.Grille;
import commun.Joueur;
import commun.Partie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loto implements Partie {
    private Map<Joueur, Grille> joueurs;
    private Tirage tirage;
    private List<Integer> tiree;

    public Loto(List<Joueur> L) {
        this.joueurs = new HashMap<>();
        for (Joueur j: L){
            joueurs.put(j, new Grille(3,9));
            joueurs.get(j).initGrid();
        }
        this.tiree = new ArrayList<>();
        this.tirage = new Tirage(L.size(), 0);
        Partie.initialiser("resources/scoreboardLoto.ser");
    }

    public Joueur retournerGagnant() {
        for (Joueur j : joueurs.keySet())
            if (joueurs.get(j).isContained(tiree)){
                return j;
                Partie.ajouterScore("resources/scoreboardLoto.ser",j);
            }
        return null;
    }

    public Boolean partieFinie() {
        for (Joueur j : joueurs.keySet())
            if (joueurs.get(j).isContained(tiree)) return true;
        return false;
    }

    public void tourSuivant() {
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
