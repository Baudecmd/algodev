package poker;

import commun.Joueur;

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

    public JoueurPoker(JoueurPoker player){
        super(player.getNom());
        this.mainJoueur=player.mainJoueur;
        this.somme=player.somme;
        this.smallBlind=player.smallBlind;
        this.mise=player.mise;
        this.combinaison=player.combinaison;
        this.tapis=player.tapis;
    }

    public boolean canPlay(int miseEnCours){
        return miseEnCours <= somme;
    }

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
    }

    private boolean memeCouleur(ArrayList<Carte>hand){
        Carte temp=hand.get(0);
        for(Carte a:hand){
            if(a.getCouleur()!=temp.getCouleur())
                return false;
        }
        return true;
    }

    private int nbCartesMemeHauteur(ArrayList<Carte>hand){
        int total=-1;   //on a une itération de trop
        Carte temp=hand.get(0);
        for(Carte a:hand){
            if(a.getHauteur()==temp.getHauteur())
                total+=1;
        }
        return total;
    }

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

    public void setCombinationHand(ArrayList<ArrayList<Carte>>listCombinations){
        Combinaisons result=Combinaisons.plus_haute;
        ArrayList<Carte>newHand=new ArrayList<>();
        for(ArrayList<Carte>temp:listCombinations){
            if(!result.isGreater(getCombinationHand(temp))){
                if(result==getCombinationHand(temp)){   //on a  la même combinaison, il faut maintenant sélectionner la meilleure main
                    //généraliser hasBetterHand pour pouvoir prendre une simple ArrayList
                }
                result=getCombinationHand(temp);
                newHand=temp;
            }
        }
        combinaison=result;
        mainJoueur=newHand;
    }

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

    public int hasBetterHand(JoueurPoker player){       //positif si le joueur de cette instanciation a une meilleure main, négatif si player en a une meilleure, 0 si main égale
        ArrayList<Carte>secondHand=player.getMainJoueur();
        if(combinaison==Combinaisons.quinte_flush_royale){
            if(player.getCombinaison()==Combinaisons.quinte_flush_royale)
                return 0;
            return 1;
        }
        if(combinaison==Combinaisons.quinte_flush || combinaison==Combinaisons.couleur || combinaison==Combinaisons.quinte || combinaison==Combinaisons.plus_haute){
            if(mainJoueur.get(0).getHauteur().getValue()>secondHand.get(0).getHauteur().getValue())
                return 1;
            else
                if(mainJoueur.get(0).getHauteur().getValue()<secondHand.get(0).getHauteur().getValue())
                    return -1;
                else
                    return 0;
        }
        if(combinaison==Combinaisons.carre){    //les cartes étant triées, la deuxième carte fait forcément partie du carré. On compare donc celle-ci
            if(mainJoueur.get(1).getHauteur().getValue()>secondHand.get(1).getHauteur().getValue())
                return 1;
            else
                if(mainJoueur.get(1).getHauteur().getValue()<secondHand.get(1).getHauteur().getValue())
                    return -1;
                else{   //même carré: on compare selon la dernière carte. Celle-ci étant triée, elle peut précéder le carré ou être à la fin de la liste, il faut donc la trouver
                    if(mainJoueur.get(0).getHauteur()==mainJoueur.get(1).getHauteur()){     //la carte seule est en fin de liste du joueur 1
                        if(secondHand.get(0).getHauteur()==secondHand.get(1).getHauteur())     //pareil pour le joueur 2
                            return Integer.compare(mainJoueur.get(4).getHauteur().getValue(),secondHand.get(4).getHauteur().getValue());
                        else
                            return Integer.compare(mainJoueur.get(4).getHauteur().getValue(),secondHand.get(0).getHauteur().getValue());
                    }
                    else
                        if(secondHand.get(0).getHauteur()==secondHand.get(1).getHauteur())
                            return Integer.compare(mainJoueur.get(0).getHauteur().getValue(),secondHand.get(4).getHauteur().getValue());
                        else
                            return Integer.compare(mainJoueur.get(0).getHauteur().getValue(),secondHand.get(0).getHauteur().getValue());
                }
        }
        if(combinaison==Combinaisons.full || combinaison==Combinaisons.brelan){     //grâce au tri, la troisième carte fait forcément partie du groupe de 3
            if(mainJoueur.get(2).getHauteur().getValue()>secondHand.get(2).getHauteur().getValue())
                return 1;
            else
                if(mainJoueur.get(2).getHauteur().getValue()<secondHand.get(2).getHauteur().getValue())
                    return -1;
                else{
                    if(combinaison==Combinaisons.full){
                        if(mainJoueur.get(1).getHauteur()==mainJoueur.get(2).getHauteur()){     //les deux cartes restantes sont à la fin
                            if(secondHand.get(1).getHauteur()==secondHand.get(2).getHauteur())  //même situation pour la seconde liste
                                return Integer.compare(mainJoueur.get(3).getHauteur().getValue(), secondHand.get(3).getHauteur().getValue());   //on compare par rapport à la quatrième carte car la cinquième est forcément moins ou aussi forte que la quatrième
                            else
                                return Integer.compare(mainJoueur.get(3).getHauteur().getValue(), secondHand.get(0).getHauteur().getValue());   //si les deux dernière cartes sont devant, la première est forcément au moins aussi bonne que la seconde, on compare donc la première carte
                        }
                        else{   //elles sont au début
                            if(secondHand.get(1).getHauteur()==secondHand.get(2).getHauteur())
                                return Integer.compare(mainJoueur.get(0).getHauteur().getValue(),secondHand.get(3).getHauteur().getValue());
                            else
                                return Integer.compare(mainJoueur.get(0).getHauteur().getValue(), secondHand.get(0).getHauteur().getValue());
                        }
                    }
                    else{   //brelan. Trois possibilités: AAABC, BAAAC, BCAAA
                        return checkBestBrelan(secondHand);
                    }
                }
        }
        else{   //cas des paires simples et doubles
            return checkBestPairs(secondHand);
        }
    }

    public int checkBestBrelan(ArrayList<Carte>checkedList){
        ArrayList<Carte>temp1=new ArrayList<>(mainJoueur);
        ArrayList<Carte>temp2=new ArrayList<>(checkedList);
        sortHand(temp1);
        sortHand(temp2);
        ArrayList<Carte>brelan1=new ArrayList<>();
        ArrayList<Carte> brelan2=new ArrayList<>();
        ArrayList<Carte>otherCards1=new ArrayList<>();
        ArrayList<Carte>otherCards2=new ArrayList<>();
        for(int i=0;i<checkedList.size()-2;i++){
            if(temp1.get(i).getHauteur()==temp1.get(i+1).getHauteur() && temp1.get(i).getHauteur()==temp1.get(i+2).getHauteur()){
                brelan1.add(temp1.get(i));
                brelan1.add(temp1.get(i+1));
                brelan1.add(temp1.get(i+2));
                temp1.remove(brelan1.get(0));
                temp1.remove(brelan1.get(1));
                temp1.remove(brelan1.get(2));
            }
            otherCards1.add(temp1.get(0));
            otherCards1.add(temp1.get(1));
            if(temp2.get(i).getHauteur()==temp2.get(i+1).getHauteur() && temp2.get(i).getHauteur()==temp2.get(i+2).getHauteur()){
                brelan2.add(temp2.get(i));
                brelan2.add(temp2.get(i+1));
                brelan2.add(temp2.get(i+2));
                temp2.remove(brelan2.get(0));
                temp2.remove(brelan2.get(1));
                temp2.remove(brelan2.get(2));
            }
            otherCards2.add(temp2.get(0));
            otherCards2.add(temp2.get(1));
        }
        if(Integer.compare(brelan1.get(0).getHauteur().getValue(), brelan2.get(0).getHauteur().getValue())==0){
            sortHand(otherCards1);
            sortHand(otherCards2);
            if(Integer.compare(otherCards1.get(0).getHauteur().getValue(), otherCards2.get(0).getHauteur().getValue())==0){
                return Integer.compare(otherCards1.get(1).getHauteur().getValue(), otherCards2.get(1).getHauteur().getValue());
            }
            else
                return Integer.compare(otherCards1.get(0).getHauteur().getValue(), otherCards2.get(0).getHauteur().getValue());
        }
        else
            return Integer.compare(brelan1.get(0).getHauteur().getValue(), brelan2.get(0).getHauteur().getValue());
    }

    public int checkBestPairs(ArrayList<Carte>checkedList){
        int i;
        ArrayList<Carte>temp1=new ArrayList<>(mainJoueur);
        ArrayList<Carte>temp2=new ArrayList<>(checkedList);
        sortHand(temp1);
        sortHand(temp2);
        ArrayList<Carte>firstPair1=new ArrayList<>();
        ArrayList<Carte>firstPair2=new ArrayList<>();
        ArrayList<Carte>secondPair1=new ArrayList<>();
        ArrayList<Carte>secondPair2=new ArrayList<>();
        for(i=0;i<temp1.size()-1;i++){
            if(temp1.get(i).getHauteur()==temp1.get(i+1).getHauteur()){
                firstPair1.add(temp1.get(i));
                firstPair1.add(temp1.get(i+1));
                temp1.remove(firstPair1.get(0));
                temp1.remove(firstPair1.get(1));
            }
            if(temp2.get(i).getHauteur()==temp2.get(i+1).getHauteur()){
                firstPair2.add(temp2.get(i));
                firstPair2.add(temp2.get(i+1));
                temp2.remove(firstPair2.get(0));
                temp2.remove(firstPair2.get(1));
                break;
            }
        }
        if(combinaison==Combinaisons.deux_paires){
            for(i=0;i<temp1.size()-1;i++){
                if(temp1.get(i).getHauteur()==temp1.get(i+1).getHauteur()){
                    secondPair1.add(temp1.get(i));
                    secondPair1.add(temp1.get(i+1));
                    temp1.remove(secondPair1.get(0));
                    temp1.remove(secondPair1.get(1));
                }
                if(temp2.get(i).getHauteur()==temp2.get(i+1).getHauteur()){
                    secondPair2.add(temp2.get(i));
                    secondPair2.add(temp2.get(i+1));
                    temp2.remove(secondPair2.get(0));
                    temp2.remove(secondPair2.get(1));
                    break;
                }
            }
        }
        if(Integer.compare(firstPair1.get(0).getHauteur().getValue(), firstPair2.get(0).getHauteur().getValue())==0){
            if(combinaison==Combinaisons.deux_paires){
                if(Integer.compare(secondPair1.get(0).getHauteur().getValue(), secondPair2.get(0).getHauteur().getValue())==0){
                    return Integer.compare(temp1.get(0).getHauteur().getValue(),temp2.get(0).getHauteur().getValue());
                }
                else
                    return Integer.compare(secondPair1.get(0).getHauteur().getValue(), secondPair2.get(0).getHauteur().getValue());
            }
            else
                return Integer.compare(temp1.get(0).getHauteur().getValue(),temp2.get(0).getHauteur().getValue());
        }
        else
            return Integer.compare(firstPair1.get(0).getHauteur().getValue(), firstPair2.get(0).getHauteur().getValue());
    }

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

    public int compareCombination(JoueurPoker other){
        if(this.combinaison.getValue()>other.getCombinaison().getValue())
            return 1;
            if(this.combinaison.getValue()==other.getCombinaison().getValue())
                return 0;
            return -1;
    }

    public void sortHand(ArrayList<Carte>hand){
        Collections.sort(hand, Carte::compareTo2);
        Collections.reverse(hand);
    }

    public void showHand(){
        sortHand(mainJoueur);
        for(Carte current:mainJoueur)
            System.out.println(current.getHauteur() + " de " + current.getCouleur());
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Carte>>result;
        ArrayList<Carte>communityCards=new ArrayList<>();
        ArrayList<Carte>mainJoueur=new ArrayList<>();

        Carte asTrefle=new Carte(Couleurs.trefle,Hauteurs.as);
        Carte huitPique=new Carte(Couleurs.pique,Hauteurs.huit);
        Carte troisTrefle=new Carte(Couleurs.trefle,Hauteurs.trois);
        Carte huitCarreau=new Carte(Couleurs.carreau,Hauteurs.huit);
        Carte dixPique=new Carte(Couleurs.pique,Hauteurs.dix);

        Carte deuxTrefle=new Carte(Couleurs.trefle,Hauteurs.deux);
        Carte asCoeur=new Carte(Couleurs.coeur,Hauteurs.as);

        communityCards.add(asTrefle);
        communityCards.add(huitPique);
        communityCards.add(troisTrefle);
        communityCards.add(huitCarreau);
        communityCards.add(dixPique);

        JoueurPoker player=new JoueurPoker("Robert", mainJoueur, 100);

        player.mainJoueur.add(deuxTrefle);
        player.mainJoueur.add(asCoeur);

        result=player.createAllCombinations(communityCards);
        player.setCombinationHand(result);

        System.out.println(player.combinaison);
        player.showHand();

        JoueurPoker player2=new JoueurPoker("Bertrand", 200);
        Carte newCard1=new Carte(Couleurs.carreau,Hauteurs.trois);
        Carte newCard2=new Carte(Couleurs.pique,Hauteurs.huit);
        Carte newCard3=new Carte(Couleurs.pique,Hauteurs.trois);
        Carte newCard4=new Carte(Couleurs.pique,Hauteurs.dix);
        Carte newCard5=new Carte(Couleurs.carreau,Hauteurs.huit);
        player2.mainJoueur.add(newCard1);
        player2.mainJoueur.add(newCard2);
        player2.mainJoueur.add(newCard3);
        player2.mainJoueur.add(newCard4);
        player2.mainJoueur.add(newCard5);
        player2.sortHand(player2.mainJoueur);
        player2.combinaison=Combinaisons.deux_paires;
        player.checkBestPairs(player2.mainJoueur);
//        if(player2.hasBetterHand(player)>0)
//            System.out.println(player2.hasBetterHand(player) + ", ça marche!");
        System.out.println(player.checkBestPairs(player2.mainJoueur));
    }

}
