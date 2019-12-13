package bataille;

import commun.Joueur;

import java.util.ArrayList;

public class JoueurBataille extends Joueur {
    private ArrayList<Bateau> listeBateaux = new ArrayList<>();
    private ArrayList<Case> alreadyChecked = new ArrayList<>();

    public JoueurBataille(String nom) {
        super(nom);
    }

    JoueurBataille(Joueur joueur) {
        super(joueur.getNom());
    }

    ArrayList<Bateau> getListeBateaux() {
        return listeBateaux;
    }

    void setListeBateaux(ArrayList<Bateau> listeBateaux) {
        this.listeBateaux = listeBateaux;
    }

    /**
     * gère le tir sur le joueur en mettant à jour sa liste de bateaux
     *
     * @param i ligne du tir
     * @param j colonne du tir
     * @return true si le tir touche et false sinon
     */
    int tir(int i, int j) {    //booléen pour indiquer si un bateau a été touché ou non
        int touche = 0;           // ATTENTION! Cette fonction n'effectue pas un tir CONTRE le joueur adverse (sinon, celui-ci serait passé en paramètre). C'est au joueur 2 de faire appel à cette fonction du joueur 1 pour effectuer son tir contre celui-ci.
        Bateau removed = null;
        Case temp = new Case(i, j);
        if (alreadyChecked.contains(temp)) return touche;
        if (0 <= i && i < 10 && 0 <= j && j < 10) {
            for (Bateau b : listeBateaux) {   //la case est existante
                for (Case c : b.getTabCases()) {
                    if (c.getI() == i && c.getJ() == j) {
                        touche = 1;
                        b.getTabCases().remove(c);
                        if (b.getTabCases().isEmpty())
                            removed = b;
                        break;  //pas la peine de vérifier les cases restantes
                    }
                }
                this.alreadyChecked.add(temp);
            }
            if (removed != null) {
                this.getListeBateaux().remove(removed);
                touche = 2;
            }
        }
        return touche;
    }
}