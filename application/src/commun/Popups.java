package commun;

import sudoku.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import menu.Menu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bataille.AffichageBN;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class Popups implements Initializable {
	static Timeline timeline;

	/**
	 * Génére la fenetre des options
	 * @param stage
	 * @param titre de la fenetre
	 * @param message contenu dans la fenetre
	 */
	public static void options(Stage stage, String titre, String message) {
		if (timeline != null)
			timeline.pause();
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(titre);
		window.setMinWidth(300);
		window.setMinHeight(300);
		

		Label label = new Label();
		label.setText(message);

		Button retour = new Button("Reprendre la partie");
		retour.setOnAction(e -> {
			window.close();
			if (timeline != null)
				timeline.play();

		});

		Button quitter = new Button("Quitter la partie");
		quitter.setOnAction(e -> {
			window.close();//
			resetTimer();
			if (timeline != null)
				timeline.stop();
			// Retour au menu principal
			Menu m = new Menu();
			Menu.nomsJoueurs.clear();
			try {
				m.start(stage);
			} catch (Exception a) {
				a.printStackTrace();
			}
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, retour, quitter);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-text-fill: black;\r\n" + 
				"    -fx-font-weight: bold;\r\n" + 
				"    -fx-font-size: 22;\r\n" + 
				"    -fx-font-family: \"Arcadepix Plus\";" +
				"	-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 130% , #56BFED, #0C181D);\r\n");

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}
	
/**
 * Timer utile pour le sudoku
 * Code originel venant de SatyaSnehith
 * Lien vers son github : https://gist.github.com/SatyaSnehith
 */
	static int mins = 0, secs = 0, millis = 0;

	public static void resetTimer() {
		mins = 0;
		secs = 0;
		millis = 0;
	}

	public static void timer(Text text) {
		timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (millis == 1000) {
					secs++;
					millis = 0;
				}
				if (secs == 60) {
					mins++;
					secs = 0;
				}
				text.setText((((mins / 10) == 0) ? "0" : "") + mins + ":" + (((secs / 10) == 0) ? "0" : "") + secs + ":"
						+ (((millis / 100) == 0) ? "0" : "") + (millis++/10));
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
		timeline.play();
	}

//Ecran de victoire
	@FXML
	Button menuPrincipal;
	@FXML
	Text nomGagnant;
	@FXML
	Text scoreGagnant;

	public static String nom;
	public static String score;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomGagnant.setText(nom);
		scoreGagnant.setText(score);

	}
	/**
	 * Gestion d'un Bouton renvoyant au menu principal
	 */
	public void menuPrincipalPressed() {
		Stage stage = (Stage) menuPrincipal.getScene().getWindow();
		Menu m = new Menu();
		Menu.nomsJoueurs.clear();
		try {
			m.start(stage);
		} catch (Exception a) {
			a.printStackTrace();
		}
	}

	/**
	 * Génére la fenetre de victoire
	 * @param primaryStage
	 * @throws Exception si le fichier est introuvable
	 */
	public static void victoire(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(Popups.class.getResource("../resources/FXML/victoire.fxml"));
			Scene scene = new Scene(root, 800, 600);
			primaryStage.setTitle("Victoire");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Popups() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Génére une fenetre de dialogue pour le poker
	 * @param message 
	 * @param w fenetre courante
	 */
	public static void alertPoker(String message, Window w) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(message);
		alert.initOwner(w);
		alert.show();
		return;
	}
	
	/*
	 * Génére la fenetre de transition entre le joueur 1 et 2 de la bataille navale
	 */
	public static void joueurDeux(Stage stage, String titre, String message) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(titre);
		window.setMinWidth(300);
		window.setMinHeight(300);

		Label label = new Label();
		label.setText(message);

		Button parti = new Button("C'est fait !");
		parti.setOnAction(e -> {
			window.close();
			AffichageBN m = new AffichageBN();
			try {
				m.start(stage);
			} catch (Exception a) {
				a.printStackTrace();
			}

		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, parti);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}

	public static void main(String[] args) {

	}

}
