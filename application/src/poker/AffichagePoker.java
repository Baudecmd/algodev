package poker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AffichagePoker extends Application implements Initializable{

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
	public Parent root;
	@FXML
	public Stage stage;
	@FXML
	public Scene scene;

	public static ArrayList<JoueurPoker> joueurs;
	public static JoueurPoker premierJoueur;
	public static JoueurPoker joueurCourant;
	public static PartiePoker pp;
	public static int nbTour;

	public void afficherSommeJoueur() {
		if (AffichagePoker.joueurs.get(0) != null) {
			this.argent1.setText(AffichagePoker.joueurs.get(0).getNom() + " a " + AffichagePoker.joueurs.get(0).getSomme()
					+ " euros !");
		}
		if (AffichagePoker.joueurs.get(1) != null) {
			this.argent2.setText(AffichagePoker.joueurs.get(1).getNom() + " a " + AffichagePoker.joueurs.get(1).getSomme()
					+ " euros !");
		}
		if (AffichagePoker.joueurs.get(2) != null) {
			this.argent3.setText(AffichagePoker.joueurs.get(2).getNom() + " a " + AffichagePoker.joueurs.get(2).getSomme()
					+ " euros !");
		}
		if (AffichagePoker.joueurs.get(3) != null) {
			this.argent4.setText(AffichagePoker.joueurs.get(3).getNom() + " a " + AffichagePoker.joueurs.get(3).getSomme()
					+ " euros !");
		}
	}

	public void handleMiser(ActionEvent Event) {

	}

	public void handleCheck(ActionEvent Event) {
		// this.joueurCourant.actionJoueur(1);

	}

	public void handleSuivre(ActionEvent Event) {
		// this.joueurCourant.actionJoueur(2);

	}

	public void handleCoucher(ActionEvent Event) {
		// this.joueurCourant.actionJoueur(3);

	}
	@Override
	public void initialize(URL args0, ResourceBundle arg1) {
		this.pot.setText("Le pot s'éléve maintenant à :\n" + Integer.toString(AffichagePoker.pp.getPot()) + " euros !");
		afficherSommeJoueur();
	}
	

	public void handleAfficheDos(MouseEvent Event) throws FileNotFoundException {
		Image dos = new Image("resources/image/dos.png");
		System.out.println(AffichagePoker.joueurCourant.getNom());
		switch (AffichagePoker.joueurs.indexOf(AffichagePoker.joueurCourant)) {
		case 0:
			this.carte1j1.setImage(dos);
			this.carte2j1.setImage(dos);
			break;
		case 1:
			this.carte1j2.setImage(dos);
			this.carte2j2.setImage(dos);
			break;
		case 2:
			this.carte1j3.setImage(dos);
			this.carte2j3.setImage(dos);
			break;
		case 3:
			this.carte1j4.setImage(dos);
			this.carte2j4.setImage(dos);
			break;

		}
	}

	public void afficherCarteCentre() {
		switch (AffichagePoker.nbTour) {
		case 1:
			Image carte1 = new Image("resources/image/" + this.pp.communityCard.get(0).toString + ".png");
			this.carte1.setImage(carte1);
			Image carte2 = new Image("resources/image/" + this.pp.communityCard.get(1).toString + ".png");
			this.carte2.setImage(carte2);
			Image carte3 = new Image("resources/image/" + this.pp.communityCard.get(2).toString + ".png");
			this.carte3.setImage(carte3);
			break;
		case 2:
			Image carte4 = new Image("resources/image/" + this.pp.communityCard.get(3).toString + ".png");
			this.carte4.setImage(carte4);
			break;
		case 3:
			Image carte5 = new Image("resources/image/" + this.pp.communityCard.get(4).toString + ".png");
			this.carte5.setImage(carte5);
			break;

		}

	}

	public void handleAfficheCarteJoueur(MouseEvent Event) throws FileNotFoundException {
		switch (AffichagePoker.joueurs.indexOf(AffichagePoker.joueurCourant)) {
		case 0:
			this.carte1j1.setImage(new Image(
					"resources/image/" + AffichagePoker.joueurCourant.getMainJoueur().get(0).toString() + ".png"));
			this.carte2j1.setImage(new Image(
					"resources/image/" + AffichagePoker.joueurCourant.getMainJoueur().get(1).toString() + ".png"));
			break;
		case 1:
			this.carte1j2.setImage(new Image(
					"resources/image/" + AffichagePoker.joueurCourant.getMainJoueur().get(0).toString() + ".png"));
			this.carte2j2.setImage(new Image(
					"resources/image/" + AffichagePoker.joueurCourant.getMainJoueur().get(1).toString() + ".png"));
			break;
		case 2:
			this.carte1j3.setImage(new Image(
					"resources/image/" + AffichagePoker.joueurCourant.getMainJoueur().get(0).toString() + ".png"));
			this.carte2j3.setImage(new Image(
					"resources/image/" + AffichagePoker.joueurCourant.getMainJoueur().get(1).toString() + ".png"));
			break;
		case 3:
			this.carte1j4.setImage(new Image(
					"resources/image/" + AffichagePoker.joueurCourant.getMainJoueur().get(0).toString() + ".png"));
			this.carte2j4.setImage(new Image(
					"resources/image/" + AffichagePoker.joueurCourant.getMainJoueur().get(1).toString() + ".png"));
			break;

		}
	}


	@Override
	public void start(Stage stage) throws Exception {
		AffichagePoker.joueurs = new ArrayList<JoueurPoker>();

		for (int i = 1; i < 5; i++) {
			JoueurPoker j = new JoueurPoker("Joueur" + i , 500);
			AffichagePoker.joueurs.add(j);
		}
		AffichagePoker.joueurCourant = new JoueurPoker(AffichagePoker.joueurs.get(0));
		System.out.println(AffichagePoker.joueurCourant.getNom());
		AffichagePoker.premierJoueur = AffichagePoker.joueurs.get(0);
		ArrayList<Carte> mainJoueur = new ArrayList<Carte>();
		mainJoueur.add(new Carte(Couleurs.pique, Hauteurs.as));
		mainJoueur.add(new Carte(Couleurs.pique, Hauteurs.dame));

		AffichagePoker.joueurs.get(0).setMainJoueur(mainJoueur);
		AffichagePoker.joueurCourant.setMainJoueur(mainJoueur);
		AffichagePoker.pp = new PartiePoker(10);
		AffichagePoker.pp.setPots(200);

		
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
