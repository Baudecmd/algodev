package commun;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grille {
    private int nbLignes;
    private int nbColonnes;
    private int[][] matrice = new int[nbLignes][nbColonnes];

    public int getNbLignes() {
        return nbLignes;
    }

    public void setNbLignes(int nbLignes) {
        this.nbLignes = nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public void setNbColonnes(int nbColonnes) {
        this.nbColonnes = nbColonnes;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public void setMatrice(int[][] matrice) {
        this.matrice = matrice;
    }

    public Grille(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        for(int i=0;i<nbLignes;i++){
            for(int j=0;j<nbColonnes;j++){
                matrice[i][j]=0;
            }
        }
    }

    public boolean isEmpty(){
        for(int i=0;i<nbLignes;i++){
            for(int j=0;i<nbColonnes;i++){
                if(matrice[i][j]!=0)
                    return false;
            }
        }
        return true;
    }

    public boolean isContained(List<Integer> Liste){
        for(int i=0;i<nbLignes;i++){
            for(int j=0;j<nbColonnes;j++){
                if(matrice[i][j]!=0 && !(Liste.contains(matrice[i][j])))
                    return false;
            }
        }
        return true;
    }

    public void initGrid2(){    //3 lignes,  9 colonnes
        //5 nombres par ligne, 2 par colonne, 1 à 9, 10 à 19, 20 à 29, etc. jusqu'à 90, 15 numéros au total
        //première ligne : 9 possibilités (1 à 9), dernière ligne: 11 possibilités (80 à 90)
        Random fill=new Random();
        Random randomVar=new Random();
        ArrayList<Integer> availableValues=new ArrayList<>();
        int index;
        int n;
        int count=0;
        int countLine;
        int countColumn;
        for(int i=1;i<91;i++) {     //initialisation du tableau des valeurs possibles
            availableValues.add(i - 1, i);     //on remplit la liste des valeurs possibles
        }
        while(count<15){
            for(int i=0;i<3;i++){
                countLine=5;
                for(int j=0;j<9;j++){
                    countColumn=2;
                    n=fill.nextInt(2);
                    if(n==1 && countColumn>0){
                        index=randomVar.nextInt(10);
                        matrice[i][j]=availableValues.get(index);
                        availableValues.remove(index);
                        countColumn--;
                        count--;
                    }
                }
            }
        }
    }

    public void initTab(){
        int premiersNombres[][]={{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
        ArrayList<Integer> alreadyUsed=new ArrayList<>();
        int result,nbMis,tirage;
        for(int i=0;i<3;i++){
            nbMis=0;
            if(i<=1){       //cas des deux premières lignes
                while(nbMis<5){
                    result=hasard(0, 9);
                    if(premiersNombres[i][result]==0){
                        if(result==0){
                            tirage=hasard(1+result*10,10+result*10);
                        }
                        else{
                            if(result==8){
                                tirage=hasard(result*10,11+result*10);
                            }
                            else{
                                tirage=hasard(result*10,10+result*10);
                            }
                        }
                        tirage=hasard(1+result*10,9+result*10);
                        if(!alreadyUsed.contains(tirage)){
                            alreadyUsed.add(tirage);
                            premiersNombres[i][result]=tirage;
                            nbMis++;
                        }
                    }
                }
            }
            else{       //cas de la dernière ligne
                while(nbMis<5){
                    result=hasard(0,9);
                    if(!(premiersNombres[0][result]!=0 && premiersNombres[1][result]!=0)){
                        if(premiersNombres[i][result]==0){
                            if(result==0){
                                tirage=hasard(1+result*10,10+result*10);
                            }
                            else{
                                if(result==8){
                                    tirage=hasard(result*10,11+result*10);
                                }
                                else{
                                    tirage=hasard(result*10,10+result*10);
                                }
                            }
                            tirage=hasard(1+result*10, 9+result*10);
                            if(!alreadyUsed.contains(tirage)){
                                alreadyUsed.add(tirage);
                                premiersNombres[i][result]=tirage;
                                nbMis++;
                            }
                        }
                    }
                }
            }
        }
    }

    private static int hasard(int low, int high){
        Random re = new Random();
        return re.nextInt(high-low)+low;
    }

    private static void printTab(int tab[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<9;j++){
                System.out.print(tab[i][j] + "    ");
            }
            System.out.println();
        }
    }
}
