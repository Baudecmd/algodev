package loto;

import commun.Joueur;
import commun.Partie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Loto implements Partie { // Le Loto est composé d'une map associant un joueur et une grille, un Tirage et la liste des numéros déjà tiré
    private List<JoueurLoto> joueurs;
    private Tirage tirage;
    private List<Integer> tiree;
    private final String fileName = "scoreboardLoto.ser";

    public String getFileName() {
        return fileName;
    }

    public Loto(List<Joueur> L) { //Constructeur du loto, avec pour entrée la liste des joueurs de la partie envoyée par le menu
        this.joueurs = new ArrayList<>();
        for (Joueur j: L){
        	JoueurLoto jL = new JoueurLoto(j.getNom());
            jL.initGrille();
            joueurs.add(jL);
        }
        this.tiree = new ArrayList<>();
        this.tirage = new Tirage(L.size(), 0);
        Partie.initialiser(fileName);
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
                Partie.ajouterVictoire(fileName ,new Joueur(j.getNom()));
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
        Loto l = new Loto(new ArrayList<>());
        Partie.ajouterVictoire(l.fileName, new Joueur("grr"));
        ArrayList<Joueur> L = Partie.recupererScore(l.fileName);
        System.out.println(L.toString());
    }
}