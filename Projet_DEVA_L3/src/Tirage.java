import java.util.ArrayList;
import java.util.Random;

public class Tirage {

    private int nbJoueurs;
    private int nextToken;  //prochain numero tiré (entre 1 et 90)
    private ArrayList<Integer> unusedTokens = new ArrayList<Integer>();   //liste des jetons qui n'ont pas encore été utilisés

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public int getNextToken() {
        return nextToken;
    }

    public void setNextToken(int nextToken) {
        this.nextToken = nextToken;
    }

    public Tirage(int nbJoueurs, int nextToken) {
        this.nbJoueurs = nbJoueurs;
        this.nextToken = nextToken;
        for(int i=1;i<91;i++){
            unusedTokens.add(i-1,i);
        }
    }

    public Tirage(int nbJoueurs, int nextToken, ArrayList<Integer> unusedTokens) {
        this.nbJoueurs = nbJoueurs;
        this.nextToken = nextToken;
        this.unusedTokens = unusedTokens;
    }

    public void initGrille(){   //3 lignes de 5 numéros, 9 colonnes de 15 numéros

    }

    public void RandomToken(){
        Random randomVar=new Random();
        int index=randomVar.nextInt(unusedTokens.size());   //nombre aléatoire >=0 et <taille de la liste des tokens restants
        this.nextToken=unusedTokens.indexOf(index);     //on récupère le nombre de la liste
        unusedTokens.remove(index);     //on supprime cet élément de la liste
    }

    public int getTokenToGame(){
        if(!unusedTokens.isEmpty()) {
            RandomToken();
            return nextToken;
        }
        else
            return 0;       //si la fonction renvoie 0, c'est que la partie est finie, car il n'y a plus de jetons
    }
}