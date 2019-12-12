package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import commun.Partie;

public class PartiePoker implements Partie {

    private int blinde;
    private int miseEnCours;
    private int pot;
    private Stack<Carte>pile=new Stack<>();
    private static ArrayList<JoueurPoker>listeJoueurs=new ArrayList<>();
    private ArrayList<JoueurPoker>joueursCouches=new ArrayList<>();
    private ArrayList<Carte>communityCards=new ArrayList<>();

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
        PartiePoker.listeJoueurs = listeJoueurs;
    }

    public void addPlayer(JoueurPoker player){ PartiePoker.listeJoueurs.add(player); }

    public ArrayList<Carte> getCommunityCards() {
        return communityCards;
    }

    public void setCommunityCards(ArrayList<Carte> communityCards) {
        this.communityCards = communityCards;
    }

    public PartiePoker(int blinde){
        this.blinde=blinde;
        this.miseEnCours=blinde;        //la mise du tour est égale à la blinde au début
        this.pot=0;
        initPile();
    }

    /**
     * Initialise la pile de cartes
     */
    private void initPile(){
        Carte temp;
        for(Couleurs c:Couleurs.values()){
            for(Hauteurs h:Hauteurs.values()){
                temp=new Carte(c,h);
                pile.push(temp);
            }
        }
        Collections.shuffle(pile);
    }

    /**
     * Redéfinit les variables de classe qui doivent l'être en vue d'une prochaine manche
     */
    public void newRound(){
        setPots(0);
        setPile(new Stack<>());
        initPile();
        setCommunityCards(new ArrayList<>());
        setJoueurCouches(new ArrayList<>());
        rotation();
    }

    /**
     * Fait une rotation d'un cran de la liste de joueurs
     */
    private void rotation(){
        //la petite blinde a déjà été placée en tête de liste
        Collections.rotate(listeJoueurs, 1);    //pas besoin de vérifier si il y a plus d'un joueur car le poker ne peut pas être joué seul
    }

    /**
     * Effectue un tour de table des joueurs
     * @param preFlop Le booléen précisant si le tour est le tour de Preflop ou non
     */
    private void nextTurn(boolean preFlop) {
        boolean check = true, loop, terminated,lastPlayer=false;
        int choix, mise;
        Scanner sc = new Scanner(System.in);
        ArrayList<JoueurPoker> turnTable = new ArrayList<>(listeJoueurs);
        turnTable.removeAll(joueursCouches);    //si des joueurs se sont couchés dans les tours précédents, ils n'ont toujours pas le droit de jouer
        if (preFlop) {
            verifyBlinds(turnTable);
            Collections.rotate(turnTable, 2);   //le premier joueur est celui à gauche de la grosse blinde
            check = false;    //interdiction de check au premier tour
        }
        do{      //il faut utiliser un do while, sinon la boucle ne commencera jamais! Tous les joueurs ont une mise initiale de 0!
            for (JoueurPoker joueur : turnTable) {
                loop=true;  //on permet au joueur de miser
                if(joueur.isTapis())    //un joueur qui a fait tapis n'effectue plus d'action
                    continue;
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
                                            if(searchPlayerInAllIn(turnTable)){    //surmiser un tapis revient simplement à suivre ce tapis
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
                                            System.out.println("La mise doit être au moins supérieure à deux fois la mise actuelle, qui est de " + miseEnCours);
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
                                if(joueur.getMise()==miseEnCours){      //le joueur a déjà suivi la mise en cours, il n'y a pas d'équilibrage à faire
                                    pot+=miseEnCours;
                                    joueur.setSomme(joueur.getSomme()-miseEnCours);
                                }
                                else{       //il y a un équilibrage à faire
                                    pot+=(miseEnCours-joueur.getMise());
                                    joueur.setSomme(joueur.getSomme()-(miseEnCours-joueur.getMise()));
                                    joueur.setMise(miseEnCours);    //le joueur se contente de miser suffisamment pour atteindre la mise en cours
                                }
                                check = false;
                                terminated=true;
                            }
                            else
                                System.out.println("Vous n'avez pas les moyens de miser ou de suivre! Veuillez effectuer un autre choix.");
                            break;
                        case 3:     //Se Coucher
                            joueursCouches.add(joueur);
                            terminated = true;
                            if(turnTable.size()==joueursCouches.size()+1)   //vérifie qu'il reste plus d'un joueur en lice. Si ce n'est pas le cas, on quittera la boucle pour prématurément
                                lastPlayer=true;
                            break;
                        case 4:     //Check
                            terminated = true;
                            break;
                        case 5:     //Tapis
                            if(searchPlayerInAllIn(turnTable)){    //un joueur a déjà fait tapis
                                handleAllIn(joueur,turnTable);
                            }
                            else{
                                pot+=joueur.getSomme();
                                joueur.setMise(joueur.getMise()+joueur.getSomme());
                                joueur.setSomme(0);
                                miseEnCours=joueur.getMise();
                                joueur.setTapis(true);
                            }
                            terminated=true;
                            check=false;
                            break;
                        default:        //par sécurité, si une mauvaise valeur est entrée, le joueur recommence son tour
                            break;
                    }
                }
                if(lastPlayer)  //s'il ne reste qu'un joueur en lice, il ne sert à rien de demander au dernier joueur ce qu'il veut faire
                    break;
            }
            turnTable.removeAll(joueursCouches);    //on doit le refaire une fois dans le while pour que le tour s'arrête si trop de joueurs se sont couchés
        }while (!sameBet() && turnTable.size()!=1);        //tant que les joueurs n'ont pas tous mis la même mise dans le pot, le tour de mises ne s'arrête pas. Si tous les joueurs se sont couchés sauf un, il est, par défaut, le gagnant
    }

    /**
     * Indique si un joueur de la liste de joueurs a déjà fait tapis
     * @param allPlayers La liste des joueurs
     * @return Le booléen indiquant si un joueur est déjà en tapis ou non
     */
    private boolean searchPlayerInAllIn(ArrayList<JoueurPoker>allPlayers){       //indique si un joueur précédent a fait tapis
        for(JoueurPoker player:allPlayers){
            if(player.isTapis())
                return true;
        }
        return false;
    }

    /**
     * Gère la situation où deux joueurs ont fait tapis
     * @param player Le joueur qui vient de faire tapis
     * @param allPLayers La liste des joueurs
     */
    private void handleAllIn(JoueurPoker player,ArrayList<JoueurPoker>allPLayers){     //si deux joueurs font tapis, garde le tapis le plus faible et rend la somme superflue à l'autre joueur
        int temp=player.getSomme();
        player.setMise(player.getMise()+player.getSomme());
        player.setSomme(0);
        for(JoueurPoker alreadyAllIn:allPLayers){
            if(alreadyAllIn.isTapis() && alreadyAllIn.getSomme()==0){       //on compare bien par rapport au joueur en all-in ACTUEL. Il peut y avoir plusieurs joueurs en all-in, mais c'est celui au tapis le plus bas (et donc la somme est égale à 0) qui indique le tapis actuel
                player.setTapis(true);
                if(player.getMise()>alreadyAllIn.getMise()){
                    player.setSomme(player.getMise()-alreadyAllIn.getMise());
                    pot+=temp-player.getSomme();    //le pot récupère la valeur de la somme totale du joueur, à laquelle on soustrait la somme qu'il récupère
                    player.setMise(alreadyAllIn.getMise());
                    System.out.println("Un joueur a déjà fait tapis avec une somme plus faible que vous. Vous récupérez " + player.getSomme());
                }
                else{  // <=
                    if(alreadyAllIn.getMise()==player.getMise()){
                        player.setTapis(true);
                        pot+=temp;
                    }
                    else {
                        //le joueur étant déjà en all-in, même si ce n'est plus le cas, doit quand même attendre la fin de la manche!
                        alreadyAllIn.setSomme(alreadyAllIn.getMise() - player.getMise());
                        pot -= alreadyAllIn.getSomme();
                        miseEnCours = player.getMise();
                        alreadyAllIn.setMise(miseEnCours);
                        player.setSomme(0);
                        System.out.println(alreadyAllIn.getNom() + ", " + player.getNom() + " a fait tapis avec une somme moins importante que la vôtre. Vous récupérez " + alreadyAllIn.getSomme());
                    }
                }
                break;  //on a trouvé le bon joueur en tapis actuel, il n'est donc plus nécessaire de parcourir la liste
            }
        }
    }

    /**
     * Vérifie si tous les joueurs ont misé la même somme
     * @return Indique si tous les joueurs ont misé la même somme ou non
     */
    private boolean sameBet(){
        int mise=listeJoueurs.get(0).getMise();
        for(JoueurPoker joueur:listeJoueurs){
            if(joueur.getMise()!=mise)
                return false;
        }
        return true;
    }

    /**
     * Effectue les tours des deux joueurs gérant les blindes
     * @param turnTable La liste des joueurs
     */
    private void verifyBlinds(ArrayList<JoueurPoker>turnTable){
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
                    pot+=big.getSomme();
                    big.setMise(big.getSomme());
                    big.setSomme(0);
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

    /**
     * Donne deux cartes à chaque joueur
     */
    private void giveCardsToPlayer(){
        int i;
        for(JoueurPoker joueur:listeJoueurs){
            for(i=0;i<2;i++)
                joueur.getMainJoueur().add(pile.pop());
        }
    }

    /**
     * Ajoute une carte commune (ou 3 si c'est le tour de Flop)
     */
    private void addCommunityCards(){
        if(communityCards.isEmpty()){   //flop
            for(int i=0;i<3;i++)
                communityCards.add(pile.pop());
        }
        else
            communityCards.add(pile.pop());     //turn ou river: on n'ajoute qu'une carte
    }

    /**
     * Montre les cartes communes
     */
    private void showCommunityCards(){
        System.out.println("Les cartes communes sont:");
        for(Carte card:communityCards)
            System.out.println(card.getHauteur() + " de " + card.getCouleur());
    }

    /**
     * Vérifie si la condition de fin de partie est remplie
     * @return La valeur booléenne indiquant si il reste suffisamment de joueurs pour continuer à jouer ou non
     */
    public Boolean partieFinie(){
        //partie terminée s'il ne reste plus qu'un joueur
        return listeJoueurs.size() <= 1;
    }

    public static void main(String[] args) {
        int nbTurns=1;
        ArrayList<JoueurPoker>winners;
        ArrayList<JoueurPoker>finalPlayers;
        ArrayList<JoueurPoker>quit;     //liste permettant de demander aux joueurs si ils souhaitent quitter la partie
        JoueurPoker player1=new JoueurPoker("Joffrey", 500);
        JoueurPoker player2=new JoueurPoker("Gaby", 500);
        Scanner sc=new Scanner(System.in);
        int choice;
        System.out.println("Quelle est la mise de la grosse blinde?");
        PartiePoker partie=new PartiePoker(sc.nextInt());
        partie.addPlayer(player1);
        partie.addPlayer(player2);
        while(!partie.partieFinie()){
            if(nbTurns==1){
                partie.giveCardsToPlayer();
                for(JoueurPoker player:PartiePoker.listeJoueurs){
                    System.out.println(player.getNom() + ", vous avez les cartes suivantes:");
                    player.showHand();
                }
                partie.nextTurn(true);
                nbTurns++;
            }
            else{
                if(nbTurns==5){
                    System.out.println("C'est le Showdown!");       //A traiter
                    for(JoueurPoker player:PartiePoker.listeJoueurs){
                        player.setCombinationHand(player.createAllCombinations(partie.getCommunityCards()));    //on assigne à chaque joueur sa meilleure main
                        System.out.println(player.getNom() + ", vous avez la main suivante:" );
                        player.showHand();
                        System.out.println("Vous avez la combinaison: " + player.getCombinaison());
                        System.out.println();
                    }
                    finalPlayers=new ArrayList<>(partie.getListeJoueurs());
                    winners=new ArrayList<>();
                    Collections.sort(finalPlayers, JoueurPoker::compareCombination);      //on trie en fonction de la meilleure combinaison
                    Collections.reverse(finalPlayers);
                    winners.add(finalPlayers.get(0));   //c'est forcément un gagnant
                    for(JoueurPoker player:finalPlayers){
                        if(player.getCombinaison()==winners.get(0).getCombinaison() && !winners.contains(player)) {    //la deuxième condition permet d'éviter que le premier joueur, qui est déjà dans la liste, soit placé deux fois dedans
                            if (player.bestHand(player.getMainJoueur(), winners.get(0).getMainJoueur())==0)
                                winners.add(player);
                            else{
                                if (player.bestHand(player.getMainJoueur(),winners.get(0).getMainJoueur()) > 0) {
                                    winners.clear();
                                    winners.add(player);
                                }
                            }
                        }
                    }
                    if(winners.size()>1){
                        System.out.println("Les joueurs:");
                        for(JoueurPoker player:winners){
                            System.out.println(player.getNom());
                        }
                        System.out.println(" gagnent la manche!");
                        for(JoueurPoker player:winners)
                            player.setSomme(player.getSomme()+(partie.getPot()/winners.size()));
                    }
                    else{
                        System.out.println(winners.get(0).getNom() + " gagne la manche!");
                        winners.get(0).setSomme(winners.get(0).getSomme()+partie.getPot());
                    }
                    nbTurns=1;
                    partie.newRound();  //on réinitialise les variables de classe
                    quit=new ArrayList<>(partie.getListeJoueurs());
                    for(JoueurPoker joueur:quit){
                        joueur.setMainJoueur(new ArrayList<>());
                        joueur.setTapis(false);     //on en profite pour réinitialiser le tapis des joueurs
                        joueur.setMise(0);
                        if(joueur.getSomme()==0){
                            PartiePoker.listeJoueurs.remove(joueur);
                            System.out.println(joueur.getNom() + " n'a plus assez d'argent pour continuer! Il est éliminé!");
                            if(PartiePoker.listeJoueurs.size()==1)
                                break;
                        }
                        System.out.println(joueur.getNom() + ", souhaitez-vous partir? Tapez 1 pour dire oui");
                        choice=sc.nextInt();
                        if(choice==1)
                            PartiePoker.listeJoueurs.remove(joueur);
                        if(PartiePoker.listeJoueurs.size()==1)   //pas la peine de continuer à demander s'il ne reste qu'un seul joueur
                            break;
                    }
                }
                else {  //étapes de flop, turn ou river
                    partie.addCommunityCards();
                    partie.showCommunityCards();
                    partie.nextTurn(false);
                    nbTurns++;
                }
            }
            finalPlayers=new ArrayList<>(partie.getListeJoueurs());
            finalPlayers.removeAll(partie.getJoueurCouches());
            if(finalPlayers.size()==1){
                if(partie.getJoueurCouches().size()>0){     //tous les joueurs se sont couchés sauf un
                    System.out.println("Tous les joueurs se sont couchés sauf " + finalPlayers.get(0).getNom());
                    System.out.println("C'est donc le vainqueur de cette manche!");
                    finalPlayers.get(0).setSomme(finalPlayers.get(0).getSomme()+partie.getPot());
                    nbTurns=1;
                    partie.newRound();
                    quit=new ArrayList<>(partie.getListeJoueurs());
                    for(JoueurPoker joueur:quit){
                        if(joueur.getSomme()==0){
                            PartiePoker.listeJoueurs.remove(joueur);
                            System.out.println(joueur.getNom() + " n'a plus assez d'argent pour continuer! Il est éliminé!");
                            if(PartiePoker.listeJoueurs.size()==1)
                                break;
                        }
                        joueur.setTapis(false);
                        joueur.setMise(0);
                        joueur.setMainJoueur(new ArrayList<>());
                        System.out.println(joueur.getNom() + ", souhaitez-vous partir? Tapez 1 pour dire oui");
                        choice=sc.nextInt();
                        if(choice==1)
                            PartiePoker.listeJoueurs.remove(joueur);
                        if(PartiePoker.listeJoueurs.size()==1)   //pas la peine de continuer à demander s'il ne reste qu'un seul joueur
                            break;
                    }
                }
                else    //il ne reste qu'un joueur en lice
                    if(finalPlayers.size()==1)
                        break;
            }
        }
        System.out.println("La partie est terminée! Trop de joueurs ont quitté la partie");
    }

}
