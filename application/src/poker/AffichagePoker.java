package poker;

import java.awt.Button;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import commun.Popups;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class affichagePoker extends Application {

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

	public ArrayList<JoueurPoker> joueurs;
	public JoueurPoker premierJoueur;
	public JoueurPoker joueurCourant;

	public void afficherSommeJoueur() {
		if (this.joueurs.get(0) != null) {
			// this.argent1.setText(this.joueurs.get(0).getNom() + "a" +
			// this.joueurs.get(0).getSomme + "d'euros !");
		}
		if (this.joueurs.get(1) != null) {
			// this.argent1.setText(this.joueurs.get(1).getNom() + "a" +
			// this.joueurs.get(1).getSomme + "d'euros !");
		}
		if (this.joueurs.get(2) != null) {
			// this.argent1.setText(this.joueurs.get(2).getNom() + "a" +
			// this.joueurs.get(2).getSomme + "d'euros !");
		}
		if (this.joueurs.get(3) != null) {
			// this.argent1.setText(this.joueurs.get(3).getNom() + "a" +
			// this.joueurs.get(3).getSomme + "d'euros !");
		}
	}

	public void handleMiser(ActionEvent Event) {
		// this.joueurCourant.actionJoueur(1);

	}

	public void handleCheck(ActionEvent Event) {
		// this.joueurCourant.actionJoueur(1);

	}

	public void handleSuivre(ActionEvent Event) {
		// this.joueurCourant.actionJoueur(2);

	}

	public void handleCoucher(ActionEvent Event) {
		// this.joueurCourant.actionJoueur(2);

	}

	public int getIndex(JoueurPoker jp) {
		int cpt = 0;
		for (JoueurPoker j : this.joueurs) {
			if (j != jp) {
				cpt++;
			}
		}
		return cpt;

	}

	public void setDos() throws FileNotFoundException {
		InputStream temp = new FileInputStream("../ressources/images/dos.png");
		Image dos = new Image(temp);
		this.carte1.setImage(dos);
		this.carte2.setImage(dos);
		this.carte3.setImage(dos);
		this.carte4.setImage(dos);
		this.carte5.setImage(dos);

		this.paquet.setImage(dos);

		if (this.joueurs.get(0) != null) {
			this.carte1j1.setImage(dos);
			this.carte2j1.setImage(dos);
		}
		if (this.joueurs.get(1) != null) {
			this.carte1j2.setImage(dos);
			this.carte2j2.setImage(dos);
		}
		if (this.joueurs.get(2) != null) {
			this.carte1j3.setImage(dos);
			this.carte2j3.setImage(dos);
		}
		if (this.joueurs.get(3) != null) {
			this.carte1j4.setImage(dos);
			this.carte2j4.setImage(dos);

		}

	}
	
	public void afficherPot() {
		//this.pot.setText(this.);
	}

	public void handleAfficheDos(MouseEvent Event) throws FileNotFoundException {
		InputStream temp = new FileInputStream("../ressources/images/dos.png");
		Image dos = new Image(temp);
		switch (getIndex(this.joueurCourant)) {
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

	public void handleAfficheCarte(MouseEvent Event) throws FileNotFoundException {
		ImageView temp = new ImageView(
				"../ressources/images/" + this.joueurCourant.getMainJoueur().get(0).toString() + ".png");
		ImageView source = ((ImageView) Event.getSource());
		if (temp.equals(source)) {
			switch (getIndex(this.joueurCourant)) {
			case 0:
				InputStream tuyo = new FileInputStream(
						"../ressources/images/" + this.joueurCourant.getMainJoueur().get(0).toString() + ".png");
				this.carte1j1 = new ImageView(new Image(tuyo));
				tuyo = new FileInputStream(
						"../ressources/images/" + this.joueurCourant.getMainJoueur().get(1).toString() + ".png");
				this.carte2j1 = new ImageView(new Image(tuyo));
				break;
			case 1:
				InputStream tuyo1 = new FileInputStream(
						"../ressources/images/" + this.joueurCourant.getMainJoueur().get(0).toString() + ".png");
				this.carte1j2 = new ImageView(new Image(tuyo1));
				tuyo1 = new FileInputStream(
						"../ressources/images/" + this.joueurCourant.getMainJoueur().get(1).toString() + ".png");
				this.carte2j2 = new ImageView(new Image(tuyo1));
				break;
			case 2:
				InputStream tuyo2 = new FileInputStream(
						"../ressources/images/" + this.joueurCourant.getMainJoueur().get(0).toString() + ".png");
				this.carte1j3 = new ImageView(new Image(tuyo2));
				tuyo2 = new FileInputStream(
						"../ressources/images/" + this.joueurCourant.getMainJoueur().get(1).toString() + ".png");
				this.carte2j3 = new ImageView(new Image(tuyo2));
				break;
			case 3:
				InputStream tuyo3 = new FileInputStream(
						"../ressources/images/" + this.joueurCourant.getMainJoueur().get(0).toString() + ".png");
				this.carte1j4 = new ImageView(new Image(tuyo3));
				tuyo3 = new FileInputStream(
						"../ressources/images/" + this.joueurCourant.getMainJoueur().get(1).toString() + ".png");
				this.carte2j4 = new ImageView(new Image(tuyo3));
				break;

			}
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/tablePoker.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Poker");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String args[]) {
		launch(args);

	}

}
