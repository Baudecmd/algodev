package poker;

import commun.Joueur;

import javax.swing.text.Caret;
import java.util.*;

public class JoueurPoker extends Joueur {

    private ArrayList<Carte>mainJoueur=new ArrayList<>();
    private int somme;
    private int mise;
    private Combinaisons combinaison;
    private boolean smallBlind;
    private boolean tapis;

    public ArrayList<Carte> getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(ArrayList<Carte> mainJoueur) {
        this.mainJoueur = mainJoueur;
    }

    public Combinaisons getCombinaison() {
        return combinaison;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    public int getMise() {
        return mise;
    }

    public void setMise(int mise) {
        this.mise = mise;
    }

    public boolean isTapis() {
        return tapis;
    }

    public void setTapis(boolean tapis) {
        this.tapis = tapis;
    }

    public boolean isSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(boolean smallBlind) {
        this.smallBlind = smallBlind;
    }

    public JoueurPoker(String nom, int somme){
        super(nom);
        this.somme=somme;
        this.smallBlind=false;
        this.mise=0;
        this.tapis=false;
    }

    public JoueurPoker(String nom, ArrayList<Carte> mainJoueur, int somme) {
        super(nom);
        this.mainJoueur = mainJoueur;
        this.somme=somme;
        this.smallBlind=false;
        this.mise=0;
        this.tapis=false;
    }
    
    public JoueurPoker(Joueur j) {
    	super(j.getNom());
    	this.mise = 0;
    	this.somme = 500;
    }

    public JoueurPoker(JoueurPoker player){
        super(player.getNom());
        this.mainJoueur=player.mainJoueur;
        this.somme=player.somme;
        this.smallBlind=player.smallBlind;
        this.mise=player.mise;
        this.combinaison=player.combinaison;
        this.tapis=player.tapis;
    }

    /**
     * Verifie si le joueur peut jouer la mise en cours de la partie
     * @param miseEnCours La valeur entière de la mise en cours de la partie
     * @return La valeur booléenne correspondant à la comparaison de la mise en cours avec la somme du joueur
     */
    public boolean canPlay(int miseEnCours){
        return miseEnCours <= somme;
    }
    /**
     * Demande au joueur son choix d'action pour son tour
     * @param hasChecked Le booléen indiquant si le check est possible
     * @return La valeur entière correspondant au type de choix du joueur
     */
/*
    public int actionJoueur(boolean hasChecked){   //true: le joueur précédent a check
        int choix=0;
        boolean correctValue=true;
        Scanner sc=new Scanner(System.in);
        while(correctValue){
            System.out.println(this.getNom() + ", vous avez " + this.getSomme() + ", quelle action souhaitez-vous faire?");  //on met le nom du joueur pour améliorer la lisibilité
            System.out.print("1.Miser 2.Suivre 3.Se Coucher");
            if(hasChecked){
                System.out.print(" 4.Check");
                System.out.println(" 5.Tapis");
            }
            else{
                System.out.print(" 4.Check (Impossible)");
                System.out.println(" 5.Tapis");
            }
            choix=sc.nextInt();
            if((!hasChecked && choix==4) || choix<1 || choix >6){
                System.out.println("Choix invalide");
            }
            else
                correctValue=false;
        }
        return choix;
    }*/

    /**
     * Verifie si la main est de couleur unie
     * @param hand La liste des cartes de la main
     * @return La valeur booléenne indiquant si la main est de couleur unie ou non
     */
    private boolean memeCouleur(ArrayList<Carte>hand){
        Carte temp=hand.get(0);
        for(Carte a:hand){
            if(a.getCouleur()!=temp.getCouleur())
                return false;
        }
        return true;
    }

    /**
     * Renvoie le nombre de cartes maximum de même hauteur
     * @param hand La liste des cartes de la main
     * @return Le nombre entier de cartes de même hauteur
     */
    private int nbCartesMemeHauteur(ArrayList<Carte>hand){      //renvoie le nombre de cartes semblables à la carte la plus présente dans la liste. Si il y a 4 deux, la fonction renverra 3
        int result=0,total,index,i;
        ArrayList<Carte>temp=new ArrayList<>(hand);
        sortHand(temp);
        for(Carte current:temp){
            total=0;
            index=temp.indexOf(current);
            i=index+1;
            while(i<5){
                if(temp.get(i).getHauteur()==current.getHauteur()){
                    total++;
                    i++;
                }
                else
                    break;
            }
            if(total>result)
                result=total;
        }
        return result;
    }

    /**
     * Verifie si la main est une suite
     * @param hand La liste des cartes de la main
     * @return La valeur booléenne indiquant si la main est une suite ou non
     */
    private boolean checkSuite(ArrayList<Carte>hand){
        int i,val1,val2;
        ArrayList<Carte>temp=new ArrayList<>(hand);
        sortHand(temp);
        for(i=0;i<temp.size()-1;i++){
            val1=temp.get(i).getHauteur().getValue();
            val2=temp.get(i+1).getHauteur().getValue()+1;
            if(!(val1==val2))
                return false;
        }
        return true;
    }

    /**
     * Verifie si la main est un full
     * @param hand La liste des cartes de la main
     * @return La valeur booléenne indiquant si la main est un full ou non
     */
    private boolean checkFull(ArrayList<Carte>hand){
        ArrayList<Carte>temp=new ArrayList<>(hand);
        ArrayList<Carte>triple=new ArrayList<>();
        sortHand(temp);
        for(int i=0;i<temp.size()-2;i++){
            if(temp.get(i).getHauteur()==temp.get(i+1).getHauteur() && temp.get(i).getHauteur()==temp.get(i+2).getHauteur()){
                triple.add(temp.get(i));
                triple.add(temp.get(i+1));
                triple.add(temp.get(i+2));
                temp.remove(triple.get(0));
                temp.remove(triple.get(1));
                temp.remove(triple.get(2));
                break;
            }
        }
        return temp.get(0).getHauteur() == temp.get(1).getHauteur();
    }

    /**
     * Verifie si la main est une double paire
     * @param hand La liste des cartes de la main
     * @return La valeur booléenne indiquant si la main est une double paire ou non
     */
    private boolean checkDouble(ArrayList<Carte>hand){
        ArrayList<Carte>temp=new ArrayList<>(hand);
        ArrayList<Carte>paire=new ArrayList<>();
        sortHand(temp);
        for(int i=0;i<temp.size()-2;i++){
            if(temp.get(i).getHauteur()==temp.get(i+1).getHauteur()){
                paire.add(temp.get(i));
                paire.add(temp.get(i+1));
                temp.remove(paire.get(0));
                temp.remove(paire.get(1));
                break;
            }
        }
        //note: les cartes ont été triées. Si il y a une seconde paire, les cartes sont forcément côte à côte
        return temp.get(0).getHauteur() == temp.get(1).getHauteur() || temp.get(1).getHauteur() == temp.get(2).getHauteur();
    }

    /**
     * Attribue la meilleure main possible au joueur
     * @param listCombinations La liste des mains possibles du joueur
     */
    public void setCombinationHand(ArrayList<ArrayList<Carte>>listCombinations){
        Combinaisons result=Combinaisons.plus_haute;
        ArrayList<Carte>newHand=new ArrayList<>(listCombinations.get(0));
        for(ArrayList<Carte>temp:listCombinations){
            if(!result.isGreater(getCombinationHand(temp))){
                if(result==getCombinationHand(temp)){   //on a  la même combinaison, il faut maintenant sélectionner la meilleure main
                    if(bestHand(newHand,temp)<0)
                        newHand=temp;
                }
                else{
                    result=getCombinationHand(temp);
                    newHand=temp;
                }
            }
        }
        combinaison=result;
        mainJoueur=newHand;
    }

    /**
     * Determine la combinaison correspondant à la main considérée
     * @param hand La liste des cartes de la main
     * @return La combinaison correspondant à la main
     */
    public Combinaisons getCombinationHand(ArrayList<Carte>hand){
        Combinaisons result=Combinaisons.plus_haute;
        int memeHauteur=nbCartesMemeHauteur(hand);
        if(memeCouleur(hand)){      //cas des quinte flush ou d'une couleur
            if(checkSuite(hand)){        //quinte flush
                for(Carte a:hand){
                    if(a.getHauteur()==Hauteurs.as){
                        return Combinaisons.quinte_flush_royale;
                    }
                }
                return Combinaisons.quinte_flush;
            }
            else
                result=Combinaisons.couleur;
        }
        else{
            if(memeHauteur==3){     //cas d'un carré
                return Combinaisons.carre;
            }
            else{
                if(memeHauteur==2){     //cas d'un full ou d'un brelan
                    if(checkFull(hand)){
                        return Combinaisons.full;
                    }
                    else{
                        if(result!=Combinaisons.couleur)
                            return Combinaisons.brelan;
                    }
                }
                if(result==Combinaisons.couleur)
                    return result;
                else{
                    if(checkSuite(hand)){
                            return Combinaisons.quinte;
                    }
                    else{
                        if(memeHauteur==1){     //au moins une paire
                            if(checkDouble(hand)){
                                return Combinaisons.deux_paires;
                            }
                            else
                                return Combinaisons.une_paire;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Compare les mains de deux joueurs
     * @param firstHand La liste des cartes de la main du premier joueur
     * @param secondHand La liste des cartes de la main du second joueur
     * @return La valeur entière correspondant au résultat de la comparaison des deux mains
     */
    public int bestHand(ArrayList<Carte>firstHand, ArrayList<Carte>secondHand){     //compare deux mains ayant la même combinaison
        int i;
        sortHand(firstHand);
        sortHand(secondHand);
        Combinaisons combination=getCombinationHand(firstHand);
        if(combination==Combinaisons.quinte_flush_royale)
            return 0;       //pas la peine de comparer deux quinte flush royales, elles sont forcément similaires
        if(combination==Combinaisons.quinte_flush || combination==Combinaisons.couleur || combination==Combinaisons.quinte)
            return Integer.compare(firstHand.get(0).getHauteur().getValue(), secondHand.get(0).getHauteur().getValue());
        if(combination==Combinaisons.carre){
            if(Integer.compare(firstHand.get(1).getHauteur().getValue(),secondHand.get(1).getHauteur().getValue())!=0)
                return Integer.compare(firstHand.get(1).getHauteur().getValue(), secondHand.get(1).getHauteur().getValue());
            else{   //on a le même carré, il faut donc comparer selon la dernière carte, qui peut se trouver en première ou en dernière position de la liste
                if(firstHand.get(0).getHauteur()==firstHand.get(1).getHauteur()){   //la carte se trouve en fin de liste
                    if(secondHand.get(0).getHauteur()==secondHand.get(1).getHauteur())  //pareil pour la deuxième main
                        return Integer.compare(firstHand.get(4).getHauteur().getValue(), secondHand.get(4).getHauteur().getValue());
                    else
                        return Integer.compare(firstHand.get(4).getHauteur().getValue(), secondHand.get(0).getHauteur().getValue());
                }
                else{   //la carte se trouve en début de première liste
                    if(secondHand.get(0).getHauteur()==secondHand.get(1).getHauteur())
                        return Integer.compare(firstHand.get(0).getHauteur().getValue(), secondHand.get(4).getHauteur().getValue());
                    else
                        return Integer.compare(firstHand.get(0).getHauteur().getValue(), secondHand.get(0).getHauteur().getValue());
                }
            }
        }
        if(combination==Combinaisons.full || combination==Combinaisons.brelan){     //grâce au tri, la troisième carte (d'index 2) fait forcément partie du brelan
            if(Integer.compare(firstHand.get(2).getHauteur().getValue(), secondHand.get(2).getHauteur().getValue())!=0)
                return Integer.compare(firstHand.get(2).getHauteur().getValue(), secondHand.get(2).getHauteur().getValue());
            else{
                if(combination==Combinaisons.full){
                    if(firstHand.get(1).getHauteur()==firstHand.get(2).getHauteur()){   //les deux cartes restantes du full sont à la fin de la liste
                        if(secondHand.get(1).getHauteur()==secondHand.get(2).getHauteur())  //pareil pour la deuxième liste
                            return Integer.compare(firstHand.get(3).getHauteur().getValue(), secondHand.get(3).getHauteur().getValue());
                        else
                            return Integer.compare(firstHand.get(3).getHauteur().getValue(), secondHand.get(0).getHauteur().getValue());
                    }
                    else{
                        if(secondHand.get(1).getHauteur()==secondHand.get(2).getHauteur())
                            return Integer.compare(firstHand.get(0).getHauteur().getValue(), secondHand.get(3).getHauteur().getValue());
                        else
                            return Integer.compare(firstHand.get(0).getHauteur().getValue(), secondHand.get(0).getHauteur().getValue());
                    }
                }
                else{   //brelan. 3 Possibilités:AAABC, BCAAA, BAAAC
                    return checkBestBrelan(firstHand,secondHand);
                }
            }
        }
        if(combination==Combinaisons.une_paire || combination==Combinaisons.deux_paires)
            return checkBestPairs(firstHand, secondHand, combination);
        else{
            for(i=0;i<5;i++){
                if(i==4)
                    return Integer.compare(firstHand.get(i).getHauteur().getValue(), secondHand.get(i).getHauteur().getValue());
                else{
                    if(Integer.compare(firstHand.get(i).getHauteur().getValue(),secondHand.get(i).getHauteur().getValue())==0)
                        continue;
                    else
                        return Integer.compare(firstHand.get(i).getHauteur().getValue(), secondHand.get(i).getHauteur().getValue());
                }
            }
        }
        return 0;
    }

    /**
     * Compare les brelans des deux joueurs
     * @param firstHand La liste des cartes de la main du premier joueur
     * @param secondHand La liste des cartes de la main du second joueur
     * @return La valeur entière correspondant au résultat de la comparaison des deux mains
     */
    public int checkBestBrelan(ArrayList<Carte>firstHand, ArrayList<Carte>secondHand){
        ArrayList<Carte>temp1=new ArrayList<>(firstHand);
        ArrayList<Carte>temp2=new ArrayList<>(secondHand);
        ArrayList<Carte>brelan1=new ArrayList<>();
        ArrayList<Carte> brelan2=new ArrayList<>();
        sortHand(temp1);
        sortHand(temp2);
        brelan1.add(temp1.get(2));      //la carte centrale de la main appartient forcément au brelan
        brelan2.add(temp2.get(2));
        //on récupère les brelans
        for(Carte a:temp1){
            if(a.getHauteur()==brelan1.get(0).getHauteur())
                brelan1.add(a);
        }
        for(Carte b:temp2){
            if(b.getHauteur()==brelan2.get(0).getHauteur())
                brelan2.add(b);
        }
        temp1.removeAll(brelan1);
        temp2.removeAll(brelan2);
        if(Integer.compare(brelan1.get(0).getHauteur().getValue(), brelan2.get(0).getHauteur().getValue())==0){
            if(Integer.compare(temp1.get(0).getHauteur().getValue(), temp2.get(0).getHauteur().getValue())==0){
                return Integer.compare(temp1.get(1).getHauteur().getValue(), temp2.get(1).getHauteur().getValue());
            }
            else
                return Integer.compare(temp1.get(0).getHauteur().getValue(), temp2.get(0).getHauteur().getValue());
        }
        else
            return Integer.compare(brelan1.get(0).getHauteur().getValue(), brelan2.get(0).getHauteur().getValue());
    }

    /**
     * Compare les paires et doubles paires des deux joueurs
     * @param firstHand La liste des cartes de la main du premier joueur
     * @param secondHand La liste des cartes de la main du second joueur
     * @param combination Le type de combinaison de la main des deux joueurs
     * @return La valeur entière correspondant au résultat de la comparaison des deux mains
     */
    public int checkBestPairs(ArrayList<Carte>firstHand, ArrayList<Carte>secondHand,Combinaisons combination){
        ArrayList<Carte>temp1=new ArrayList<>(firstHand);
        ArrayList<Carte>temp2=new ArrayList<>(secondHand);
        ArrayList<Carte>firstPair1=new ArrayList<>();
        ArrayList<Carte>firstPair2=new ArrayList<>();
        ArrayList<Carte>secondPair1=new ArrayList<>();
        ArrayList<Carte>secondPair2=new ArrayList<>();
        sortHand(temp1);
        sortHand(temp2);
        firstPair1.add(temp1.get(1));       //la seconde carte de la liste appartient forcément à une paire. De même pour l'avant-dernière carte
        temp1.remove(1);
        if(temp1.get(0).getHauteur()==firstPair1.get(0).getHauteur()) {
            firstPair1.add(temp1.get(0));
            temp1.remove(0);
        }
        else{
            firstPair1.add(temp1.get(1));
            temp1.remove(1);
        }
        firstPair2.add(temp2.get(1));
        temp2.remove(1);
        if(temp2.get(0).getHauteur()==firstPair2.get(0).getHauteur()) {
            firstPair2.add(temp2.get(0));
            temp2.remove(0);
        }
        else{
            firstPair2.add(temp2.get(1));
            temp2.remove(1);
        }
        if(combination==Combinaisons.deux_paires){
            secondPair1.add(temp1.get(1));      //il ne reste que 3 cartes dans temp1. Celle du milieu appartient forcément à la paire
            temp1.remove(1);
            if(temp1.get(0).getHauteur()==secondPair1.get(0).getHauteur()) {
                secondPair1.add(temp1.get(0));
                temp1.remove(0);
            }
            else{
                secondPair1.add(temp1.get(1));
                temp1.remove(1);
            }
            secondPair2.add(temp2.get(1));
            temp2.remove(1);
            if(temp2.get(0).getHauteur()==secondPair2.get(0).getHauteur()) {
                secondPair2.add(temp1.get(0));
                temp2.remove(0);
            }
            else{
                secondPair2.add(temp2.get(1));
                temp2.remove(1);
            }
        }
        if(Integer.compare(firstPair1.get(0).getHauteur().getValue(), firstPair2.get(0).getHauteur().getValue())==0){
            if(combination==Combinaisons.deux_paires){
                if(Integer.compare(secondPair1.get(0).getHauteur().getValue(), secondPair2.get(0).getHauteur().getValue())==0){
                    return Integer.compare(temp1.get(0).getHauteur().getValue(),temp2.get(0).getHauteur().getValue());
                }
                else
                    return Integer.compare(secondPair1.get(0).getHauteur().getValue(), secondPair2.get(0).getHauteur().getValue());
            }
            else{
                if(Integer.compare(temp1.get(0).getHauteur().getValue(), temp2.get(0).getHauteur().getValue())==0){
                    if(Integer.compare(temp1.get(1).getHauteur().getValue(),temp2.get(1).getHauteur().getValue())==0){
                        return Integer.compare(temp1.get(2).getHauteur().getValue(), temp2.get(2).getHauteur().getValue());
                    }
                    else
                        return Integer.compare(temp1.get(1).getHauteur().getValue(), temp2.get(1).getHauteur().getValue());
                }
                else
                    return Integer.compare(temp1.get(0).getHauteur().getValue(), temp2.get(0).getHauteur().getValue());
            }
        }
        else
            return Integer.compare(firstPair1.get(0).getHauteur().getValue(), firstPair2.get(0).getHauteur().getValue());
    }

    /**
     * Crée toutes les mains possibles de la main du joueur avec les cartes communes de la partie
     * @param communityCards Les cartes communes de la partie
     * @return La liste des mains possibles
     */
    public ArrayList<ArrayList<Carte>> createAllCombinations(ArrayList<Carte>communityCards){
        ArrayList<ArrayList<Carte>>listCombinations=new ArrayList<>();
        ArrayList<Carte>listeCartes;
        ArrayList<Carte>tempList=new ArrayList<>(communityCards);
        listCombinations.add(tempList);      //le joueur peut très bien utiliser toutes les cartes communes
        for(Carte i:tempList){
            for(Carte j:tempList){
                for(Carte k:tempList){
                    if(!(i.equals(j) || i.equals(k) || j.equals(k))){
                        listeCartes = new ArrayList<>();
                        listeCartes.add(mainJoueur.get(0));
                        listeCartes.add(mainJoueur.get(1));
                        listeCartes.add(i);
                        listeCartes.add(j);
                        listeCartes.add(k);
                        listCombinations.add(listeCartes);
                        for(Carte l:tempList){
                            if(!(l.equals(i) || l.equals(j) || l.equals(k))){
                                listeCartes=new ArrayList<>();
                                listeCartes.add(mainJoueur.get(0));
                                listeCartes.add(i);
                                listeCartes.add(j);
                                listeCartes.add(k);
                                listeCartes.add(l);
                                listCombinations.add(listeCartes);
                                listeCartes=new ArrayList<>();
                                listeCartes.add(mainJoueur.get(1));
                                listeCartes.add(i);
                                listeCartes.add(j);
                                listeCartes.add(k);
                                listeCartes.add(l);
                                listCombinations.add(listeCartes);
                            }
                        }
                    }
                }
            }
        }
        listCombinations=eliminateDuplicates(listCombinations);
        return listCombinations;
    }

    /**
     * Elimine toutes les combinaisons déjà existantes
     * @param listCombinations La liste de toutes les mains possibles
     * @return La liste de toutes les mains possibles sans doublons
     */
    private ArrayList<ArrayList<Carte>> eliminateDuplicates(ArrayList<ArrayList<Carte>>listCombinations){
        Set<ArrayList<Carte>>noDuplicates;
        ArrayList<ArrayList<Carte>>result;
        for(ArrayList<Carte>temp:listCombinations){
            Collections.sort(temp, Carte::compareTo);
            Collections.reverse(temp);
        }
        noDuplicates=new HashSet<>(listCombinations);
        result=new ArrayList<>(noDuplicates);
        return result;
    }

    /**
     * Compare les combinaisons de deux joueurs
     * @param other Le joueur qui est comparé
     * @return La valeur entière correspondant à la comparaison des combinaisons des deux joueurs
     */
    public int compareCombination(JoueurPoker other){
        return Integer.compare(this.combinaison.getValue(), other.getCombinaison().getValue());
    }

    /**
     * Trie la main
     * @param hand La liste des cartes de la main
     */
    public void sortHand(ArrayList<Carte>hand){
        Collections.sort(hand, Carte::compareTo);
        Collections.reverse(hand);
    }

    /**
     * Montre la main du joueur
     */
    public void showHand(){
        sortHand(mainJoueur);
        for(Carte current:mainJoueur)
            System.out.println(current.getHauteur() + " de " + current.getCouleur());
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Carte>>result;
        ArrayList<Carte>communityCards=new ArrayList<>();
        JoueurPoker player=new JoueurPoker("Robert", 500);

        Carte deuxCoeur=new Carte(Couleurs.carreau,Hauteurs.deux);
        Carte deuxPique=new Carte(Couleurs.coeur,Hauteurs.valet);
        Carte dameCoeur=new Carte(Couleurs.carreau,Hauteurs.dix);
        Carte troisPique=new Carte(Couleurs.carreau,Hauteurs.roi);
        Carte asCarreau=new Carte(Couleurs.pique,Hauteurs.quatre);

        Carte deuxTrefle=new Carte(Couleurs.carreau,Hauteurs.as);
        Carte roiCarreau=new Carte(Couleurs.pique,Hauteurs.sept);

        player.mainJoueur.add(deuxTrefle);
        player.mainJoueur.add(roiCarreau);

        communityCards.add(deuxCoeur);
        communityCards.add(deuxPique);
        communityCards.add(dameCoeur);
        communityCards.add(troisPique);
        communityCards.add(asCarreau);
        player.sortHand(communityCards);

        System.out.println(player.nbCartesMemeHauteur(communityCards));
        System.out.println(player.getCombinationHand(communityCards));

        player.setCombinationHand(player.createAllCombinations(communityCards));
        player.showHand();
        System.out.println(player.getCombinaison());
    }

}
