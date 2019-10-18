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
                matrice=new int[nbLignes][nbColonnes];
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
        matrice=premiersNombres;
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
