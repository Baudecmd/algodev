import java.util.ArrayList;
import java.util.Random;

public class Tirage {

    private int nbPlayers;
    private int nextToken;  //prochain numero tiré (entre 1 et 90)
    private ArrayList<Integer> unusedTokens = new ArrayList<Integer>();   //liste des jetons qui n'ont pas encore été utilisés

    public int getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(int nbPlayers) {
        this.nbPlayers = nbPlayers;
    }

    public int getNextToken() {
        return nextToken;
    }

    public void setNextToken(int nextToken) {
        this.nextToken = nextToken;
    }

    public Tirage(int nbPlayers, int nextToken) {
        this.nbPlayers = nbPlayers;
        this.nextToken = nextToken;
        for(int i=1;i<91;i++){
            unusedTokens.add(i-1,i);
        }
    }

    public Tirage(int nbPlayers, int nextToken, ArrayList<Integer> unusedTokens) {
        this.nbPlayers = nbPlayers;
        this.nextToken = nextToken;
        this.unusedTokens = unusedTokens;
    }

    public void initGrid(){   //3 lignes de 5 numéros, 9 colonnes de 15 numéros
        Random fill=new Random();
        int n;
        int Grid[3][9];
        int count=15;   //il y a 15 nombres à placer
        for(int i=0;i<3;i++){
            for(int j=0;j<9;j++){
                if(count>0){
                    n=fill.nextInt(2);        //une chance sur deux de remplir la case
                    if(n==1){   //on remplit la case

                    }
                }
            }
        }
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