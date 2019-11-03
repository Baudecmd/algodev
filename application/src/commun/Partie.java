package commun;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public interface Partie { //L'interface partie apporte les fonctions necessaires au deroulement de tous les jeux du projet
    void tourSuivant();
    Boolean partieFinie();
    Joueur retournerGagnant();

    //gestion des scores
    static void initialiser(String nomFichier) { //initialise le tableau des scores a une liste de joueur vide si le fichier est vide
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

    static ArrayList<Joueur> recupererScore(String path) { //renvoie le tableau des scores sous forme de liste de joueur
        File f = new File(path);
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

    static void ajouterVictoire(String nomFichier,Joueur j) { //Ajout du score dans le cas d'un classement par nombre de parties gagnées
        ArrayList<Joueur> scores = recupererScore(nomFichier);

        if(scores.contains(j)) {
            scores.get(scores.indexOf(j)).increaseScore(1);
        }
        else {
            j.increaseScore(1);
            scores.add(j);
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
