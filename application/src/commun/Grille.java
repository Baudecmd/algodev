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

	public Grille(int nbLignes, int nbColonnes, int[][] matrice) {
		super();
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.matrice = matrice;
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

    /**
     * Vérifie si une liste de numéros est contenue dans la matrice
     * @param Liste La liste de numéros
     * @return La valeur booléenne indiquant si tous les numéros sont contenus dans la matrice ou non
     */
    public boolean isContained(List<Integer> Liste){
        for(int i=0;i<nbLignes;i++){
            for(int j=0;j<nbColonnes;j++){
                if(matrice[i][j]!=0 && !(Liste.contains(matrice[i][j])))
                    return false;
            }
        }
        return true;
    }

    /**
     * Initialise la grille de loto
     */
    public void initTab(){
        int i,j;
        int premiersNombres[][]={{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
        ArrayList<Integer> alreadyUsed=new ArrayList<>();
        int result,nbMis;
        for(i=0;i<3;i++){
            nbMis=0;
            if(i<=1){       //cas des deux premières lignes
                while(nbMis<5){
                    result=hasard(0, 9);
                    if(premiersNombres[i][result]==0){
                        setNumber(i, result, premiersNombres, alreadyUsed);
                        nbMis++;
                    }
                }
            }
            else{       //cas de la dernière ligne
                for(j=0;j<9;j++){   //on complète la ligne
                    if(premiersNombres[0][j]==0 && premiersNombres[1][j]==0){
                        setNumber(i, j, premiersNombres, alreadyUsed);
                        nbMis++;
                    }
                }
                while(nbMis<5){
                    result=hasard(0,9);
                    if(premiersNombres[i][result]==0){
                        setNumber(i, result, premiersNombres, alreadyUsed);
                        nbMis++;
                    }
                }
            }
        }
        matrice=premiersNombres;
    }

    /**
     * Selectionne un nombre aléatoire et le place dans la grille
     * @param i La ligne de la grille considérée
     * @param j La colonne de la grille considérée
     * @param premiersNombres La matrice représentant la grille
     * @param alreadyUsed La liste des entiers déjà utilisés
     */
    private void setNumber(int i, int j, int[][] premiersNombres, ArrayList<Integer> alreadyUsed) {
        int tirage;
        boolean loop=true;
        while(loop){
            if(j==0){
                tirage=hasard(1+j*10,10+j*10);
            }
            else{
                if(j==8){
                    tirage=hasard(j*10, 11+j*10);
                }
                else{
                    tirage=hasard(j*10, 10+j*10);
                }
            }
            if(!alreadyUsed.contains(tirage)){
                alreadyUsed.add(tirage);
                premiersNombres[i][j]=tirage;
                loop=false;
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
