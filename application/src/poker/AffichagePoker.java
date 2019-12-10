package poker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
	public TextField lamise;

	@FXML
	public Parent root;
	@FXML
	public Stage stage;
	@FXML
	public Scene scene;

	public static ArrayList<JoueurPoker> joueurs;
	public static PartiePoker pp;
	public static int nbTour;

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

	public void handleMiser(ActionEvent Event) {
		this.pp.nextTurn(true, 1);
	}

	public void handleCheck(ActionEvent Event) {
		this.pp.nextTurn(true, 4);
	}

	// a faire
	public void handleSuivre(ActionEvent Event) {
		this.pp.nextTurn(true, 2);
	}

	public void handleCoucher(ActionEvent Event) {
		this.pp.nextTurn(true, 3);
	}

	@Override
	public void initialize(URL args0, ResourceBundle arg1) {
		AffichagePoker.pp.initPile();
		AffichagePoker.pp.setPots(200);
		AffichagePoker.pp.giveCardsToPlayer(); // après la création de la pp dan sle menu, il faut la set pour
												// l'affichage
		try {
		setDos();}
		catch(IOException e) {}
		actualiserPot();
		afficherSommeJoueur();
		actualiserTour();
		actualiserMise();
	}

	public void actualiserPot() {
		this.pot.setText("Le pot s'élève maintenant à :\n" + Integer.toString(AffichagePoker.pp.getPot()) + " euros !");
	}

	public void actualiserTour() {
		this.tour.setText("C'est le tour de " + this.pp.joueurCourant.getNom() + " !");
	}

	public void actualiserMise() {
		this.mise.setText("La mise actuelle est de : " + this.pp.getMiseEnCours() + "euros !");
	}

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
		// affiche le dos des joueurs existant, pour le début de la partie 

	}

	@FXML
	public void handleEntrerMise(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			this.pp.joueurCourant.setMise(Menu.stringtoint2(this.lamise.getText())); // a faire
	}

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
