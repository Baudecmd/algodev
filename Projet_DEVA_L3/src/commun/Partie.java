package commun;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public interface Partie {
    void initialiser();
    void partieGagnee();
    void partiePerdu();

    static ArrayList<Joueur> recupererScore(String f) {
        ArrayList<Joueur> scores = new ArrayList<>(10);
        try {
            FileInputStream fI = new FileInputStream(f);
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

    static void ajouterScore(String f,Joueur j) {
        ArrayList<Joueur> scores = recupererScore(f);
        scores.add(j);
        Collections.sort(scores);

        try {
            FileOutputStream fO = new FileOutputStream(f);
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
