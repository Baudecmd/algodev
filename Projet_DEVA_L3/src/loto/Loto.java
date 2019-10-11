package loto;

import commun.Grille;
import commun.Joueur;
import commun.Partie;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loto implements Partie {
    private Map<Joueur, Grille> joueurs;

    public Loto(List<Joueur> L) {
        HashMap hm = new HashMap();
        for (int i = 0; i < L.size(); i++){
            hm.put(L.get(i), new Grille(3,15));
        }
        initialiser();
    }

    @Override
    public void initialiser() {
        File f = new File("resources/scoreboardLoto.ser");
        try {
            if (f.length() == 0) {
                FileOutputStream fO = new FileOutputStream(f);
                ObjectOutputStream oO = new ObjectOutputStream(fO);
                Joueur j = new Joueur("_____");
                for (int i = 0; i < 10; i++) {
                    oO.writeObject(j);
                }
                oO.close();
                fO.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean partieGagnee() {
        for (Joueur j : joueurs.keySet()) {
            if (joueurs.get(j).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void tourSuivant() {
        for (Joueur j : joueurs.keySet()) {

        }
    }

    /*public static void main(String[] args) {
        Loto L1 = new Loto();
        L1.initialiser();
        Joueur j1 = new Joueur("Joffrey");
        Joueur j2 = new Joueur("Gaby");
        Joueur j3 = new Joueur("Romane");
        Joueur j4 = new Joueur("Lilian");
        Joueur j5 = new Joueur("Conrad");
        j1.setScore(37);
        j2.setScore(45);
        j3.setScore(2);
        j4.setScore(6);
        j5.setScore(700);
        Partie.ajouterScore("resources/scoreboardLoto.ser",j1);
        Partie.ajouterScore("resources/scoreboardLoto.ser",j2);
        Partie.ajouterScore("resources/scoreboardLoto.ser",j3);
        Partie.ajouterScore("resources/scoreboardLoto.ser",j4);
        Partie.ajouterScore("resources/scoreboardLoto.ser",j5);
        ArrayList<Joueur> L = Partie.recupererScore("resources/scoreboardLoto.ser");
        System.out.println(L.toString());
    }*/
}
