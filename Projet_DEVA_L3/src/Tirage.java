import java.util.ArrayList;
import java.util.Random;

public class Tirage {

    private int nbJoueurs;
    private int nextToken;  //prochain numero tiré (entre 1 et 90)
    private ArrayList<Integer> AlreadyTaken = new ArrayList<Integer>();

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
    }

    public Tirage(int nbJoueurs, int nextToken, ArrayList<Integer> alreadyTaken) {
        this.nbJoueurs = nbJoueurs;
        this.nextToken = nextToken;
        AlreadyTaken = alreadyTaken;
    }

    public void RandomToken(){
        Random randomVar=new Random();
        this.nextToken=randomVar.nextInt(90)+1;   //nombre aléatoire >0 et <91
        while(AlreadyTaken.contains((Integer)nextToken)){

        }
    }



}
