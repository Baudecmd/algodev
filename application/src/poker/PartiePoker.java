package poker;

import commun.Joueur;
import commun.Partie;

import java.util.*;

public class PartiePoker implements Partie {

    private int blinde;
    private int miseEnCours;
    private int pot;
    private Stack<Carte>pile=new Stack<>();
    private ArrayList<JoueurPoker>listeJoueurs=new ArrayList<>();
    private ArrayList<JoueurPoker>joueursCouches=new ArrayList<>();

    public int getBlinde() {
        return blinde;
    }

    public void setBlinde(int blinde) {
        this.blinde = blinde;
    }

    public int getMiseEnCours() {
        return miseEnCours;
    }

    public void setMiseEnCours(int miseEnCours) {
        this.miseEnCours = miseEnCours;
    }

    public int getPot() {
        return pot;
    }

    public void setPots(int pot) {
        this.pot = pot;
    }

    public ArrayList<JoueurPoker> getJoueurCouches() {
        return joueursCouches;
    }

    public void setJoueurCouches(ArrayList<JoueurPoker> joueursCouches) {
        this.joueursCouches = joueursCouches;
    }

    public Stack<Carte> getPile() {
        return pile;
    }

    public void setPile(Stack<Carte> pile) {
        this.pile = pile;
    }

    public ArrayList<JoueurPoker> getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(ArrayList<JoueurPoker> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public PartiePoker(int blinde){
        this.blinde=blinde;
        this.miseEnCours=blinde;        //la mise du tour est égale à la blinde au début
        this.pot=0;
        initPile();
        Collections.shuffle(pile);      //on mélange la pile des cartes;
    }

    private void initPile(){
        Carte temp;
        for(Couleurs c:Couleurs.values()){
            for(Hauteurs h:Hauteurs.values()){
                temp=new Carte(c,h);
                pile.push(temp);
            }
        }
    }

    public void selectSmallBlind(){
        Random playerIndex=new Random();
        int index=playerIndex.nextInt(listeJoueurs.size());
        listeJoueurs.get(index).setSmallBlind(true);
    }

    public void rotationInititale(){     //place la petite et la grosse blinde en tête de liste pour en faciliter l'accès
        for(JoueurPoker joueur:listeJoueurs){
            if(joueur.isSmallBlind()){
                Collections.rotate(listeJoueurs, listeJoueurs.indexOf(joueur));
            }
        }
    }

    public void rotation(){
        //la petite blinde a déjà été placée en tête de liste
        Collections.rotate(listeJoueurs, 1);    //pas besoin de vérifier si il y a plus d'un joueur car le poker ne peut pas être joué seul
    }

    //version finale de la fonction de tour suivant
    public void nextTurn(boolean preFlop) {     //effectue un tour de table des joueurs en lice
        boolean check = true, loop = true, terminated;
        int choix, mise;
        Scanner sc = new Scanner(System.in);
        ArrayList<JoueurPoker> turnTable = new ArrayList<>(listeJoueurs);
        turnTable.removeAll(joueursCouches);    //si des joueurs se sont couchés dans les tours précédents, ils n'ont toujours pas le droit de jouer
        ArrayList<JoueurPoker>allPlayers=new ArrayList<>(turnTable);
        if (preFlop) {
            verifyBlinds(turnTable);
            Collections.rotate(turnTable, 2);   //le premier joueur est celui à gauche de la grosse blinde
            check = false;    //interdiction de check au premier tour
        }
        do{      //il faut utiliser un do while, sinon la boucle ne commencera jamais! Tous les joueurs ont une mise initiale de 0!
            for (JoueurPoker joueur : turnTable) {
                terminated = false;
                while (!terminated) {
                    choix = joueur.actionJoueur(check);
                    switch (choix) {
                        case 1:     //Miser. Gérer le cas des tapis
                            while (loop) {
                                check = false;
                                System.out.println("Combien souhaitez-vous miser?");
                                mise = sc.nextInt();
                                if(joueur.canPlay(miseEnCours)){
                                    if(mise<joueur.getSomme()){
                                        if(mise>=(miseEnCours*2)){
                                            if(searchPlayerInAllIn(allPlayers)){    //surmiser un tapis revient simplement à suivre ce tapis
                                                pot+=miseEnCours-joueur.getMise();
                                                joueur.setSomme(joueur.getSomme()-(miseEnCours-joueur.getMise()));
                                                joueur.setMise(miseEnCours);
                                            }
                                            else{
                                                joueur.setSomme(joueur.getSomme()-mise);
                                                joueur.setMise(joueur.getMise()+mise);
                                                miseEnCours=mise;
                                                pot+=mise;
                                                loop=false;
                                                terminated=true;
                                            }
                                        }
                                        else
                                            System.out.println("La mise doit être au moins supérieure à deux fois la blinde, qui est de " + blinde);
                                    }
                                    else
                                        System.out.println("Vous n'avez pas les moyens de miser autant! Vous avez " + joueur.getSomme());
                                }
                                else{
                                    System.out.println("Vous n'avez pas les moyens de miser ou de suivre! Veuillez effectuer un autre choix.");
                                    loop=false;
                                }
                            }
                            break;
                        case 2:     //Suivre
                            if(joueur.canPlay(miseEnCours)){
                                pot+=miseEnCours-joueur.getMise();
                                joueur.setSomme(joueur.getSomme()-(miseEnCours-joueur.getMise()));
                                joueur.setMise(miseEnCours);    //le joueur se contente de miser suffisamment pour atteindre la mise en cours
                                check = false;
                                terminated=true;
                            }
                            else
                                System.out.println("Vous n'avez pas les moyens de miser ou de suivre! Veuillez effectuer un autre choix.");
                            break;
                        case 3:     //Se Coucher
                            joueursCouches.add(joueur);
                            terminated = true;
                            break;
                        case 4:     //Check
                            terminated = true;
                            break;
                        case 5:     //Tapis
                            if(searchPlayerInAllIn(allPlayers)){    //un joueur a déjà fait tapis
                                handleAllIn(joueur,allPlayers);
                                turnTable.remove(joueur);
                            }
                            else{
                                joueur.setMise(joueur.getMise()+joueur.getSomme());
                                joueur.setSomme(0);
                                miseEnCours=joueur.getMise();
                                turnTable.remove(joueur);   //on doit le retirer, sinon les mises ne seront plus équilibrées et le round ne s'arrêtera jamais
                                joueur.setTapis(true);
                            }
                            break;
                        default:        //par sécurité, si une mauvaise valeur est entrée, le joueur recommence son tour
                            break;
                    }
                }
            }
        }while (!sameBet());        //tant que les joueurs n'ont pas tous mis la même mise dans le pot, le tour de mises ne s'arrête pas
    }

    private boolean searchPlayerInAllIn(ArrayList<JoueurPoker>allPlayers){       //indique si un joueur précédent a fait tapis
        for(JoueurPoker player:allPlayers){
            if(player.isTapis())
                return true;
        }
        return false;
    }

    private void handleAllIn(JoueurPoker player,ArrayList<JoueurPoker>allPLayers){     //si deux joueurs font tapis, garde le tapis le plus faible et rend la somme superflue à l'autre joueur
        player.setMise(player.getMise()+player.getSomme());
        player.setSomme(0);
        for(JoueurPoker alreadyAllIn:allPLayers){
            if(alreadyAllIn.isTapis()){
                if(player.getMise()>alreadyAllIn.getMise()){
                    player.setSomme(player.getMise()-alreadyAllIn.getMise());
                    player.setMise(alreadyAllIn.getMise());
                    System.out.println("Un joueur a déjà fait tapis avec une somme plus faible que vous. Vous récupérez " + player.getSomme());
                }
                else{  // <=
                    player.setTapis(true);
                    alreadyAllIn.setTapis(false);
                    player.setMise(player.getMise());
                    alreadyAllIn.setSomme(miseEnCours-player.getMise());
                    miseEnCours=player.getMise();
                    alreadyAllIn.setMise(miseEnCours);
                    player.setSomme(0);
                    System.out.println(alreadyAllIn.getNom() + ", " + player.getNom() + " a fait tapis avec une somme moins importante que la vôtre. Vous récupérez " + alreadyAllIn.getSomme());
                }
            }
        }
    }

    private boolean sameBet(){      //vérifie si tous les joueurs ont misé la même somme
        int mise=listeJoueurs.get(0).getMise();
        for(JoueurPoker joueur:listeJoueurs){
            if(joueur.getMise()!=mise)
                return false;
        }
        return true;
    }

    private void verifyBlinds(ArrayList<JoueurPoker>turnTable){     //fonction qui gère les tours des deux joueurs gérant les blindes. Ils n'ont pas besoin de joueur, car pas le choix!
        JoueurPoker small=turnTable.get(0);
        JoueurPoker big=turnTable.get(1);
        if(small.getSomme()<=(blinde/2)){
            System.out.println(small.getNom() + ", vous êtes en all-in!");
            small.setTapis(true);
            small.setMise(small.getSomme());
            pot+=small.getSomme();
            small.setSomme(0);
            miseEnCours=small.getMise();
            turnTable.remove(small);
        }
        else{   //le joueur peut payer la petite blinde
            pot+=blinde/2;
            small.setSomme(small.getSomme()-(blinde/2));
            small.setMise(blinde/2);
        }
        if(big.getSomme()<blinde){
            System.out.println(big.getNom() + ", vous êtes en all-in!");
            big.setMise(big.getSomme());
            big.setSomme(0);
            turnTable.remove(big);
            if(small.isTapis()){     //si la petite blinde ET la grosse blinde font tapis au même tour
                if(miseEnCours>big.getMise()){
                    big.setTapis(true);
                    small.setTapis(false);
                    small.setMise(big.getMise());
                    small.setSomme(miseEnCours-big.getMise());
                    miseEnCours=big.getMise();
                    System.out.println(small.getNom() + ", la grosse blinde a fait tapis pour une somme inférieure à votre mise. Vous récupérez " + small.getSomme());
                }
                else{       // <=
                    big.setSomme(big.getMise()-miseEnCours);
                    big.setMise(miseEnCours);
                    System.out.println(big.getNom() + ", la petite blinde a fait tapis pour une somme inférieure à votre mise. Vous récupérez "+big.getSomme());
                }
            }
            pot+=small.getSomme();
            miseEnCours=small.getMise();
        }
        else{
            pot+=blinde;
            big.setSomme(big.getSomme()-blinde);
            big.setMise(blinde);
            miseEnCours=blinde;     //pour réinitialiser à chaque manche
        }
    }

    public void giveCardsToPlayer(){     //donne deux cartes à chaque joueur
        int i;
        for(JoueurPoker joueur:listeJoueurs){
            for(i=0;i<2;i++)
                joueur.getMainJoueur().add(pile.pop());
        }
    }

    public void addCommunityCards(ArrayList<Carte>communityCards){
        if(communityCards.isEmpty()){   //flop
            for(int i=0;i<3;i++)
                communityCards.add(pile.pop());
        }
        else
            communityCards.add(pile.pop());     //turn ou river: on n'ajoute qu'une carte
    }

    public void showCommunityCards(ArrayList<Carte>communityCards){
        for(Carte carte:communityCards)
            System.out.println(carte.getHauteur() + " de " + carte.getCouleur());
    }

    public Boolean partieFinie(){
        //partie terminée s'il ne reste plus qu'un joueur
        return listeJoueurs.size() == 1;
    }

    private void afficherPile(){
        for(Carte c:pile)
            System.out.println(c.getHauteur() + " de " + c.getCouleur());
    }

    public static void main(String[] args) {
        //partie à blinde constante ou non? On suppose que oui
        Scanner sc=new Scanner(System.in);
        System.out.println("Quelle est la mise de la grosse blinde?");
        PartiePoker partie=new PartiePoker(sc.nextInt());
        partie.afficherPile();
        System.out.println(partie.getPile().size());

        //les joueurs, à la fin de chaque manche, doivent avoir le droit de quitter la partie.
    }

}
