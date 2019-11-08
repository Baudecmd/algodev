package loto;

import commun.Joueur;
import commun.Partie;

import java.util.ArrayList;
import java.util.List;

public class    Loto implements Partie { // Le Loto est composé d'une map associant un joueur et une grille, un Tirage et la liste des numéros déjà tiré
    private List<JoueurLoto> joueurs;
    private Tirage tirage;
    private List<Integer> tiree;

    public Loto(List<Joueur> L) { //Constructeur du loto, avec pour entrée la liste des joueurs de la partie envoyée par le menu
        this.joueurs = new ArrayList<>();
        for (Joueur j: L){
        	JoueurLoto jL = new JoueurLoto(j.getNom());
            jL.initGrille();
            joueurs.add(jL);
        }
        this.tiree = new ArrayList<>();
        this.tirage = new Tirage(L.size(), 0);
        Partie.initialiser("resources/scoreboardLoto.ser");
    }

    List<JoueurLoto> getJoueurs() {
        return joueurs;
    }

    Tirage getTirage() {
        return tirage;
    }

    List<Integer> getTiree() {
        return tiree;
    }

    public JoueurLoto retournerGagnant() { //Renvoie le joueur qui a gagné la partie
        for (JoueurLoto j : joueurs)
            if (j.getGrille().isContained(j.getCochee()) && j.getGrille().isContained(tiree)){
                Partie.ajouterVictoire("resources/scoreboardLoto.ser",new Joueur(j.getNom()));
                return j;
            }
        return null;
    }

    public Boolean partieFinie() { //Verifie si la partie est finie
        for (JoueurLoto j : joueurs)
            if ( (j.getGrille().isContained(j.getCochee()) && j.getGrille().isContained(tiree)) || tirage.getUnusedTokens().isEmpty()) return true;
        return false;
    }

    Boolean gagnant() { //Verifie si il y a un gagnant
        for (JoueurLoto j : joueurs)
            if (j.getGrille().isContained(j.getCochee()) && j.getGrille().isContained(tiree)) return true;
        if(tirage.getUnusedTokens().isEmpty()) return false;
        return false;
    }

    public void tourSuivant() { //Passe au tour suivant en tirant un nouveau numéro
        tiree.add(tirage.getTokenToGame());
    }

    public static void main(String[] args) { //Test de la gestion des scores
        ArrayList<Joueur> L = Partie.recupererScore("resources/scoreboardLoto.ser");
        System.out.println(L.toString());
    }
}
