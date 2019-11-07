package menu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import loto.Affichage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import commun.*;

public class Menu extends Application {

	public static ArrayList<Joueur> nomsJoueurs = new ArrayList<Joueur>();

	public static int nbJoueurInt;
	public static int nbJoueurInt2;
	public static int i = 1 ;

	@FXML
	private TextField nbJoueur;

	@FXML
	private TextField nomJoueur;

	@FXML
	private Button entrerNomJoueur;

	@FXML
	private Button entrerNbJoueur;

	@FXML
	private Scene scene;

	@FXML
	private Parent root;

	@FXML
	private Stage stage;

	@FXML
	private Button loto;

	@FXML
	private Label labelNomJoueur;

	public static void stringtoint(String s, int a) {
		a = Integer.parseInt(s);
	}

	public int stringtoint2(String s) {
		int a = Integer.parseInt(s);
		return a;
	}

/////////////////////////////////////////
//Ajouter les méthodes pour lancer les parties ici

	public void buttonBataille() {
		Window w = loto.getScene().getWindow();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Jeu encore en développement");
		alert.initOwner(w);
		alert.show();

	}

	public void buttonPoker() {
		Window w = loto.getScene().getWindow();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Jeu encore en développement");
		alert.initOwner(w);
		alert.show();

	}

	public void buttonSudoku() {
		Window w = loto.getScene().getWindow();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Jeu encore en développement");
		alert.initOwner(w);
		alert.show();
		return;
	}
/////////////////////////////////////////

	@FXML
	public void handleNbJoueur(ActionEvent event) throws IOException {
		Window w = entrerNbJoueur.getScene().getWindow();
		// Vérif
		System.out.println("Nombre de joueurs entré :" + nbJoueur.getText());
		if (nbJoueur.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Entrer un nombre de joueurs !");
			alert.initOwner(w);
			alert.show();
			return;
		} else {
			Menu.nbJoueurInt = stringtoint2(nbJoueur.getText());
			Menu.nbJoueurInt2 = stringtoint2(nbJoueur.getText());
			// creation d'un stage car l'attribut se perd dans l'espace
			Stage stage2 = (Stage) nbJoueur.getScene().getWindow();
			this.root = FXMLLoader.load(getClass().getResource("../FXML/nomsJoueurs.fxml"));
			stage2.setScene(new Scene(this.root));

		}
		System.out.println(Menu.nbJoueurInt);
	}

	public void creationLabel(String s) {
		this.labelNomJoueur.setText(s);
		System.out.println(this.labelNomJoueur.getText());
		this.labelNomJoueur.setText(s);
	}

	@FXML
	public void handleNomsJoueurs(ActionEvent event) throws IOException, InterruptedException {
		Window w = entrerNomJoueur.getScene().getWindow();
		if (nomJoueur.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Entrer un nom de joueur !");
			alert.initOwner(w);
			alert.show();
			return;
		} else {
			if (Menu.nbJoueurInt > 0) {
				Joueur j = new Joueur(nomJoueur.getText());
				Menu.nomsJoueurs.add(j);
				creationLabel("Bonjour, " + nomJoueur.getText() + " vous êtes le joueur " + this.i++ + "!");
				Menu.nbJoueurInt--;
				System.out.println("Nombre de joueurs restants:" + Menu.nbJoueurInt);

			}
		}
		if (Menu.nbJoueurInt == 0) {
			affichageJeu();
		}
		nomJoueur.setText("");

	}

	public void handlePartieLoto(ActionEvent Event) {
		Stage stage2 = (Stage) this.loto.getScene().getWindow();
		Affichage a = new Affichage();
		a.start(stage2);

	}

	public void affichageJeu() {
		try {
			Stage stage2 = (Stage) nomJoueur.getScene().getWindow();
			switch (Menu.nbJoueurInt2) {
			case 1:

				this.root = FXMLLoader.load(getClass().getResource("../FXML/choixJeu1.fxml"));
				stage2.setScene(new Scene(this.root));

				break;
			case 2:

				this.root = FXMLLoader.load(getClass().getResource("../FXML/choixJeu2.fxml"));
				stage2.setScene(new Scene(this.root));

				break;
			case 3:

				this.root = FXMLLoader.load(getClass().getResource("../FXML/choixJeu3-4.fxml"));
				stage2.setScene(new Scene(this.root));

				break;
			case 4:
				this.root = FXMLLoader.load(getClass().getResource("../FXML/choixJeu3-4.fxml"));
				stage2.setScene(new Scene(this.root));

				break;
			case 0:
				System.out.println(Menu.nbJoueurInt);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void start(Stage stage) throws IOException {
		this.stage = stage;
		this.root = FXMLLoader.load(getClass().getResource("../FXML/styleMenu.fxml"));
		this.scene = new Scene(root);
		this.stage.setTitle("Menu Projet !");
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
