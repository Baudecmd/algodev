package commun;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public interface Partie {
    void tourSuivant();
    Boolean partieFinie();
    Joueur retournerGagnant();

    static void initialiser(String nomFichier) {
        File f = new File(nomFichier);
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
    static ArrayList<Joueur> recupererScore(String nomFichier) {
        ArrayList<Joueur> scores = new ArrayList<>(10);
        try {
            FileInputStream fI = new FileInputStream(nomFichier);
            ObjectInputStream oI = new ObjectInputStream(fI);
            for(int i=0;i<10;i++) {
                Joueur j = (Joueur) oI.readObject();
                scores.add(j);
            }
            oI.close();
            fI.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Collections.sort(scores);
        return scores;
    }

    static void ajouterScore(String nomFichier,Joueur j) {
        ArrayList<Joueur> scores = recupererScore(nomFichier);
        scores.add(j);
        Collections.sort(scores);

        try {
            FileOutputStream fO = new FileOutputStream(nomFichier);
            ObjectOutputStream oO = new ObjectOutputStream(fO);
            for(int i=0;i<10;i++) {
                oO.writeObject(scores.get(i));
            }
            oO.close();
            fO.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void ajouterVictoire(String nomFichier,Joueur j) {
        ArrayList<Joueur> scores = recupererScore(nomFichier);

        if(scores.contains(j)) {
            scores.get(scores.indexOf(j)).increaseScore(1);
            System.out.println("fff");
        }
        else {
            scores.add(j);
            System.out.println("eee");
        }
        Collections.sort(scores);

        try {
            FileOutputStream fO = new FileOutputStream(nomFichier);
            ObjectOutputStream oO = new ObjectOutputStream(fO);
            for(int i=0;i<10;i++) {
                oO.writeObject(scores.get(i));
            }
            oO.close();
            fO.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
