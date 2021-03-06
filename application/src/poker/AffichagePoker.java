package poker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import commun.Popups;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import menu.Menu;

public class AffichagePoker extends Application implements Initializable {

	@FXML
	public Label argent1;
	@FXML
	public Label argent2;
	@FXML
	public Label argent3;
	@FXML
	public Label argent4;
	@FXML
	public Label tour;
	@FXML
	public Label tour2;
	@FXML
	public Label pot;
	@FXML
	public Label mise;

	@FXML
	public ImageView carte1j1;
	@FXML
	public ImageView carte2j1;
	@FXML
	public ImageView carte1j2;
	@FXML
	public ImageView carte2j2;
	@FXML
	public ImageView carte1j3;
	@FXML
	public ImageView carte2j3;
	@FXML
	public ImageView carte1j4;
	@FXML
	public ImageView carte2j4;

	@FXML
	public ImageView carte1;
	@FXML
	public ImageView carte2;
	@FXML
	public ImageView carte3;
	@FXML
	public ImageView carte4;
	@FXML
	public ImageView carte5;

	@FXML
	public ImageView paquet;

	@FXML
	public Button buttonCoucher;
	@FXML
	public Button buttonSuivre;
	@FXML
	public Button buttonCheck;
	@FXML
	public Button buttonMiser;
	@FXML
	public Button buttonTapis;
	@FXML
	public TextField lamise;
	@FXML
	Button buttonOPTION;

	@FXML
	public Parent root;
	@FXML
	public Stage stage;
	@FXML
	public Scene scene;

	public static ArrayList<JoueurPoker> joueurs;
	public static PartiePoker pp;
	public static int nbTour;
	
	public void buttonOPTIONPressed() {
		Popups.options((Stage) this.buttonOPTION.getScene().getWindow(), "Option", "Option de Jeu");
	}
	
	/** 
	 * Proc??dure qui affiche le dos des cartes du centre, sert ?? l'initialisation
	 */
	
	public void setDosCarteCentre() {
		Image dos = new Image("resources/image/dos.png");
		this.carte1.setImage(dos);
		this.carte2.setImage(dos);
		this.carte3.setImage(dos);
		this.carte4.setImage(dos);
		this.carte5.setImage(dos);		
	}
	
	/**
	 * Proc??dure qui affiche l'argent restant des joueurs, sert ?? l'actualisation
	 */

	public void afficherSommeJoueur() {
		if (this.pp.getListeJoueurs().size() >= 1) {
			this.argent1.setText(this.pp.getListeJoueurs().get(0).getNom() + " a "
					+ this.pp.getListeJoueurs().get(0).getSomme() + " euros !");
		}
		if (this.pp.getListeJoueurs().size() >= 2) {
			this.argent2.setText(this.pp.getListeJoueurs().get(1).getNom() + " a "
					+ this.pp.getListeJoueurs().get(1).getSomme() + " euros !");
		}
		if (this.pp.getListeJoueurs().size() >= 3) {
			this.argent3.setText(this.pp.getListeJoueurs().get(2).getNom() + " a "
					+ this.pp.getListeJoueurs().get(2).getSomme() + " euros !");
		}
		if (this.pp.getListeJoueurs().size() >= 4) {
			this.argent4.setText(this.pp.getListeJoueurs().get(3).getNom() + " a "
					+ this.pp.getListeJoueurs().get(3).getSomme() + " euros !"); // affichage de l'argent des joueurs
																					// s'ils existent
		}
	}

/**
 * Proc??dure actualisant le pot
 */
	public void actualiserPot() {
		this.pot.setText("Le pot s'??l??ve maintenant ?? :\n" + Integer.toString(AffichagePoker.pp.getPot()) + " euros !");
	}
	/**
	 * Proc??dure permettant d'afficher quel joueur doit jouer
	 */
	public void actualiserTour() {
		this.tour2.setText("Tour du joueur :");
		this.tour.setText(AffichagePoker.pp.joueurCourant.getNom() + " !");
	}
	/**
	 * Proc??dure actualisant la mise en jeu
	 */

	public void actualiserMise() {
		this.mise.setText("La mise actuelle est de : " + AffichagePoker.pp.getMiseEnCours() + " euros !");
	}
	
	public void refreshAffichage() {
		
	}
	/**
	 * Proc??dure affichant le dos des cartes du joueur courant une fois qu'il clique (apr??s qu'il les a retourner)
	 */

	public void handleAfficheDos(MouseEvent Event) throws FileNotFoundException {
		Image dos = new Image("resources/image/dos.png");
		System.out.println(this.pp.joueurCourant.getNom());
		switch (this.pp.getListeJoueurs().indexOf(this.pp.joueurCourant)) {
		case 0:
			if (this.pp.getListeJoueurs().size() >= 1) {
				this.carte1j1.setImage(dos);
				this.carte2j1.setImage(dos);
			}
			break;
		case 1:
			if (this.pp.getListeJoueurs().size() >= 2) {
				this.carte1j2.setImage(dos);
				this.carte2j2.setImage(dos);
			}
			break;
		case 2:
			if (this.pp.getListeJoueurs().size() >= 3) {
				this.carte1j3.setImage(dos);
				this.carte2j3.setImage(dos);
			}
			break;
		case 3:
			if (this.pp.getListeJoueurs().size() >= 4) {
				this.carte1j4.setImage(dos);
				this.carte2j4.setImage(dos);
			}
			break; // affiche le dos quand le joueur lache la souris

		}
	}
	
	/**
	 * Proc??dure affichant le dos des cartes des joueurs, permet d'initialiser un tour
	 */

	public void setDos() throws FileNotFoundException {
		Image dos = new Image("resources/image/dos.png");

		if (this.pp.getListeJoueurs().size() >= 1) {
			this.carte1j1.setImage(dos);
			this.carte2j1.setImage(dos);
		}

		if (this.pp.getListeJoueurs().size() >= 2) {
			this.carte1j2.setImage(dos);
			this.carte2j2.setImage(dos);
		}

		if (this.pp.getListeJoueurs().size() >= 3) {
			this.carte1j3.setImage(dos);
			this.carte2j3.setImage(dos);
		}

		if (this.pp.getListeJoueurs().size() >= 4) {
			this.carte1j4.setImage(dos);
			this.carte2j4.setImage(dos);
		}
		// affiche le dos des joueurs existant, pour le d??but de la partie 

	}

	int indiceJoueurCourant = 0;
	boolean lastPlayer = false;
	
	
	public void joueurSuivant() {
		int temp = 0;
		if((pp.joueursCouches.size() + 1) == pp.getListeJoueurs().size()) {
			finDuRound();
			return;
		}
		
		do {
		if(indiceJoueurCourant == pp.getListeJoueurs().size() - 1) indiceJoueurCourant = 0;
		else indiceJoueurCourant++;
		}while(pp.joueursCouches.contains(pp.getListeJoueurs().get(indiceJoueurCourant)));
		
		PartiePoker.joueurCourant = pp.getListeJoueurs().get(indiceJoueurCourant);

		
		if(pp.sameBet()) lastPlayer = true;
		if(lastPlayer) {
			System.out.println("Nouveau Tour !");
			for(JoueurPoker j : pp.getListeJoueurs()) { 
				j.setMise(temp);
				temp++;
			}
			AffichagePoker.nbTour++;
			if(nbTour == 1) pp.addCommunityCards();
			if(nbTour == 2) pp.addCommunityCards();
			if(nbTour == 3) pp.addCommunityCards();
			if(nbTour == 4) {
				System.out.println("Oui");
				showdown();
			}
			lastPlayer = false;
		}
	}
	
	public void showdown() {
		
	}
	
	@FXML
	public void handleEntrerMise(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) { PartiePoker.joueurCourant.setMise(Menu.stringtoint2(this.lamise.getText()));
		this.lamise.clear();

		int miseP = PartiePoker.joueurCourant.getMise();
		
		if (PartiePoker.joueurCourant.canPlay(pp.getMiseEnCours())) {
			if (miseP < PartiePoker.joueurCourant.getSomme()) {
				if (miseP >= (pp.getMiseEnCours() * 2)) {
					if (PartiePoker.joueurCourant.isTapis()) { // surmiser un tapis revient simplement ??
															// suivre ce tapis
						pp.pot += pp.getMiseEnCours() - miseP;
						PartiePoker.joueurCourant.setSomme(PartiePoker.joueurCourant.getSomme()- (pp.getMiseEnCours()- miseP));
						PartiePoker.joueurCourant.setMise(pp.getMiseEnCours());
						pp.getListeJoueurs().set(indiceJoueurCourant, PartiePoker.joueurCourant);
						joueurSuivant();
					} else {
						PartiePoker.joueurCourant.setSomme(PartiePoker.joueurCourant.getSomme() - miseP);
						PartiePoker.joueurCourant.setMise(miseP);
						pp.setMiseEnCours(miseP);
						pp.pot += miseP;
						pp.getListeJoueurs().set(indiceJoueurCourant, PartiePoker.joueurCourant);
						joueurSuivant();
					}
				} else
					Popups.alertPoker("La mise doit ??tre au moins sup??rieure ?? deux fois la mise actuelle, qui est de "+ pp.getMiseEnCours(), paquet.getScene().getWindow());
			} else
				Popups.alertPoker("Vous n'avez pas les moyens de miser autant! Vous avez "+ PartiePoker.joueurCourant.getSomme(),paquet.getScene().getWindow());
		} else {
			Popups.alertPoker("Vous n'avez pas les moyens de miser ou de suivre! Veuillez effectuer un autre choix.",paquet.getScene().getWindow());
		}
		actualiserPot();
		afficherSommeJoueur();
		actualiserTour();
		actualiserMise();
		afficherCarteCentre();
		}
	}
	
	@FXML
	public void handleSuivre(Event event) {
		if (PartiePoker.joueurCourant.canPlay(pp.getMiseEnCours())) {
			if (PartiePoker.joueurCourant.getMise() == pp.getMiseEnCours()) { // le joueur a d??j?? suivi la mise en cours,
																// il n'y a pas d'??quilibrage ?? faire
				pp.pot += pp.getMiseEnCours();
				PartiePoker.joueurCourant.setSomme(PartiePoker.joueurCourant.getSomme() - pp.getMiseEnCours());
				joueurSuivant();
			} else { // il y a un ??quilibrage ?? faire
				pp.pot += (pp.getMiseEnCours() - PartiePoker.joueurCourant.getMise());
				PartiePoker.joueurCourant.setSomme(PartiePoker.joueurCourant.getSomme() - (pp.getMiseEnCours() - PartiePoker.joueurCourant.getMise()));
				PartiePoker.joueurCourant.setMise(pp.getMiseEnCours()); // le joueur se contente de miser suffisamment pour
				joueurSuivant();											// atteindre la mise en cours
			}
		} else Popups.alertPoker("Vous n'avez pas les moyens de miser ou de suivre! Veuillez effectuer un autre choix.", paquet.getScene().getWindow());
		pp.getListeJoueurs().set(indiceJoueurCourant, PartiePoker.joueurCourant);
		actualiserPot();
		afficherSommeJoueur();
		actualiserTour();
		actualiserMise();
		afficherCarteCentre();
	}
	
	@FXML
	public void handleCoucher(Event event) {
		pp.joueursCouches.add(PartiePoker.joueurCourant);
		pp.getListeJoueurs().set(indiceJoueurCourant, PartiePoker.joueurCourant);
		joueurSuivant();
		actualiserPot();
		afficherSommeJoueur();
		actualiserTour();
		actualiserMise();
		afficherCarteCentre();
	}
	
	public void handleCheck(Event Event) {
		joueurSuivant();
	}
	
	@FXML
	public void handleTapis(Event event) {
		if (pp.searchPlayerInAllIn(pp.getListeJoueurs())) { // un joueur a d??j?? fait tapis
			pp.handleAllIn(PartiePoker.joueurCourant, pp.getListeJoueurs());
		} else {
			pp.pot += PartiePoker.joueurCourant.getSomme();
			PartiePoker.joueurCourant.setMise(PartiePoker.joueurCourant.getMise() + PartiePoker.joueurCourant.getSomme());
			PartiePoker.joueurCourant.setSomme(0);
			pp.setMiseEnCours(PartiePoker.joueurCourant.getMise());
			Popups.alertPoker(PartiePoker.joueurCourant.getNom() + " fait tapis !", paquet.getScene().getWindow());
			PartiePoker.joueurCourant.setTapis(true);
		}
		pp.getListeJoueurs().set(indiceJoueurCourant, PartiePoker.joueurCourant);
		joueurSuivant();
		actualiserPot();
		afficherSommeJoueur();
		actualiserTour();
		actualiserMise();
		afficherCarteCentre();
	}
	
	public void finDuRound() {
		ArrayList<JoueurPoker>winners;
        ArrayList<JoueurPoker>finalPlayers;
        finalPlayers=new ArrayList<JoueurPoker>(pp.getListeJoueurs());
        winners=new ArrayList<JoueurPoker>();
        
        if(nbTour == 0) pp.addCommunityCards();
        ArrayList<JoueurPoker>quit;
        if(pp.getListeJoueurs().size() - pp.joueursCouches.size()  != 1) {
        	Popups.alertPoker("C'est le showdown !", paquet.getScene().getWindow());
        for(JoueurPoker player : pp.getListeJoueurs()){
            player.setCombinationHand(player.createAllCombinations(pp.getCommunityCards()));    //on assigne ?? chaque joueur sa meilleure main
            /*System.out.println(player.getNom() + ", vous avez la main suivante:" );
            player.showHand();
            System.out.println("Vous avez la combinaison: " + player.getCombinaison());
            System.out.println();*/
        }
        
        
        System.out.println(finalPlayers.toString());
        Collections.sort(finalPlayers, JoueurPoker::compareCombination);      //on trie en fonction de la meilleure combinaison
        Collections.reverse(finalPlayers);
        winners.add(finalPlayers.get(0));
        
        for(JoueurPoker player:finalPlayers){
            if(player.getCombinaison()==winners.get(0).getCombinaison() && !winners.contains(player)) {    //la deuxi??me condition permet d'??viter que le premier joueur, qui est d??j?? dans la liste, soit plac?? deux fois dedans
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
        for(JoueurPoker j : pp.getListeJoueurs()) {
        	if(j.getSomme() == 0) pp.getListeJoueurs().remove(j);
        }
        if(winners.size()>1){
            String temp = new String("Les joueurs : ");
            for(JoueurPoker player:winners){
                temp = temp +(player.getNom())+" ";
            }
            temp = temp + "gagnent la manche!";
            Popups.alertPoker(temp, paquet.getScene().getWindow());
            for(JoueurPoker player:winners)
                player.setSomme(player.getSomme()+(pp.getPot()/winners.size()));
            newRound();
        }
        else{
        	Popups.alertPoker(winners.get(0).getNom() + " gagne la manche!", paquet.getScene().getWindow());
            winners.get(0).setSomme(winners.get(0).getSomme()+pp.getPot());
            newRound();
        }
        }else {
        	Popups.alertPoker(finalPlayers.get(0).getNom() + " gagne la manche!", paquet.getScene().getWindow());
        	finalPlayers.get(0).setSomme(finalPlayers.get(0).getSomme()+pp.getPot());
        	newRound();
        }
        quit=new ArrayList<>(pp.getListeJoueurs());
        
        for(JoueurPoker joueur:quit){
            joueur.setTapis(false);     //on en profite pour r??initialiser le tapis des joueurs
            joueur.setMise(0);
            if(joueur.getSomme()==0){
                PartiePoker.listeJoueurs.remove(joueur);
                Popups.alertPoker(joueur.getNom() + " n'a plus assez d'argent pour continuer! Il est ??limin??!",paquet.getScene().getWindow());
                if(PartiePoker.listeJoueurs.size()==1)
                    victoire();
            }
	}
	}

	public void victoire() {
		System.out.println("Le joueur "+pp.getListeJoueurs().get(0)+" gagne la partie !!!!!");
	}
	
	/**
	 * Proc??dure permettant d'afficher les cartes au centre en fonction du nombre de tour
	 */

	public void afficherCarteCentre() {
		switch (AffichagePoker.nbTour) {
		case 1:
			Image carte1 = new Image("resources/image/" + this.pp.getCommunityCards().get(0).toString() + ".png");
			this.carte1.setImage(carte1);
			Image carte2 = new Image("resources/image/" + this.pp.getCommunityCards().get(1).toString() + ".png");
			this.carte2.setImage(carte2);
			Image carte3 = new Image("resources/image/" + this.pp.getCommunityCards().get(2).toString() + ".png");
			this.carte3.setImage(carte3);
			break;
		case 2:
			Image carte4 = new Image("resources/image/" + this.pp.getCommunityCards().get(3).toString() + ".png"); 
			this.carte4.setImage(carte4);
			break;
		case 3:
			Image carte5 = new Image("resources/image/" + this.pp.getCommunityCards().get(4).toString() + ".png");
			this.carte5.setImage(carte5);
			break; // affiche les cartes en fonction du nbdetour

		}

	}
	/**
	 * Proc??dure permettant d'afficher les cartes du joueur si celui ci clique dessus Ou s'il essaye de voir sur d'autres cartes
	 * 
	 * 
	 */

	public void handleAfficheCarteJoueur(MouseEvent Event) throws FileNotFoundException {
		switch (this.pp.getListeJoueurs().indexOf(this.pp.joueurCourant)) {
		case 0:
			this.carte1j1.setImage(
					new Image("resources/image/" + this.pp.joueurCourant.getMainJoueur().get(0).toString() + ".png"));
			this.carte2j1.setImage(
					new Image("resources/image/" + this.pp.joueurCourant.getMainJoueur().get(1).toString() + ".png"));
			break;
		case 1:
			this.carte1j2.setImage(
					new Image("resources/image/" + this.pp.joueurCourant.getMainJoueur().get(0).toString() + ".png"));
			this.carte2j2.setImage(
					new Image("resources/image/" + this.pp.joueurCourant.getMainJoueur().get(1).toString() + ".png"));
			break;
		case 2:
			this.carte1j3.setImage(
					new Image("resources/image/" + this.pp.joueurCourant.getMainJoueur().get(0).toString() + ".png"));
			this.carte2j3.setImage(
					new Image("resources/image/" + this.pp.joueurCourant.getMainJoueur().get(1).toString() + ".png"));
			break;
		case 3:
			this.carte1j4.setImage(
					new Image("resources/image/" + this.pp.joueurCourant.getMainJoueur().get(0).toString() + ".png"));
			this.carte2j4.setImage(
					new Image("resources/image/" + this.pp.joueurCourant.getMainJoueur().get(1).toString() + ".png"));
			break;

		} // affiche les cartes du joueur courant en fonction de sa position dans la liste
			// de joeueur
	}

	public static ArrayList<JoueurPoker> getJoueurs() {
		return joueurs;
	}

	public static void setJoueurs(ArrayList<JoueurPoker> joueurs) {
		AffichagePoker.joueurs = joueurs;
	}

	public static PartiePoker getPp() {
		return pp;
	}

	public static void setPp(PartiePoker pp) {
		AffichagePoker.pp = pp;
	}

	public static int getNbTour() {
		return nbTour;
	}

	public static void setNbTour(int nbTour) {
		AffichagePoker.nbTour = nbTour;
	}
	
	public void newRound() {
		this.indiceJoueurCourant = 0;
		setDosCarteCentre();
		pp.joueursCouches.clear();
		pp.setMiseEnCours(5);
		pp.newRound();
		AffichagePoker.pp.initPile();
		AffichagePoker.pp.setPots(200);
		AffichagePoker.pp.giveCardsToPlayer(); // apr??s la cr??ation de la pp dan sle menu, il faut la set pour
		// AffichagePoker.pp.addCommunityCards(); // l'affichage
		AffichagePoker.nbTour = 0;
		try {
			setDos();
		} catch (IOException e) {
		}
		actualiserPot();
		afficherSommeJoueur();
		actualiserTour();
		actualiserMise();
	}

	@Override
	public void initialize(URL args0, ResourceBundle arg1) {
		AffichagePoker.pp.initPile();
		AffichagePoker.pp.setPots(200);
		AffichagePoker.pp.giveCardsToPlayer(); // apr??s la cr??ation de la pp dan sle menu, il faut la set pour
		//AffichagePoker.pp.addCommunityCards();	// l'affichage
		AffichagePoker.nbTour = 0;
		try {
		setDos();}
		catch(IOException e) {}
		actualiserPot();
		afficherSommeJoueur();
		actualiserTour();
		actualiserMise();
	}

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;
		this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/tablePoker.fxml"));
		this.scene = new Scene(root);
		this.stage.setTitle("Poker");
		this.stage.setScene(this.scene);
		this.stage.show();

	}

	public static void main(String args[]) {
		launch(args);

	}

}
