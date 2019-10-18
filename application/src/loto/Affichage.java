package loto;

import commun.Grille;
import commun.Joueur;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Affichage extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		List listeJoueur = new ArrayList<Joueur>();
		Joueur j = new Joueur("Conrad");
		Joueur j2 = new Joueur("Patrick");
		Joueur j3 = new Joueur("LALALALA");
		Joueur j4 = new Joueur("PaEAick");

		listeJoueur.add(j);
		listeJoueur.add(j2);
		listeJoueur.add(j3);
		listeJoueur.add(j4);

		Loto l = new Loto(listeJoueur);
		primaryStage.setTitle("Loto :p");
		Pane root = new Pane();
		Button btn = new Button();
		Label text = new Label();
		text.setLayoutX(550);
		text.setLayoutY(160);
		Label text2 = new Label();
		text2.setLayoutX(550);
		text2.setLayoutY(160);
		root.getChildren().add(text2);

		btn.setText("Jouer");
		btn.setLayoutX(540);
		btn.setLayoutY(80);
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				l.tourSuivant();
				text2.setText(String.valueOf(l.getTirage().getNextToken()));

				afficherTableau(root, l.getListeGrille(), l.getTiree());

				if (l.partieFinie())
					afficherEcranFin(root, l.retournerGagnant());

			}
		});
		root.getChildren().add(btn);
		root.getChildren().add(text);

		afficherTableau(root, l.getListeGrille(), l.getTiree());
		primaryStage.setScene(new Scene(root, 650, 200 * l.getJoueurs().size()));
		primaryStage.show();
	}

	private void afficherEcranFin(Pane root, Joueur retournerGagnant) {
		Label text = new Label();
		text.setText(retournerGagnant.toString());
		text.setLayoutX(550);
		text.setLayoutY(160);
		root.getChildren().add(text);

	}

	public void afficherTableau(Pane root, List<Grille> lg, List<Integer> tokenDejaTire) {

		int nbJoueur = 0;
		for (Grille g : lg) {
			int i = 0;
			for (int[] x : g.getMatrice()) {
				int j = 0;
				for (int y : x) {
					Label text = new Label();
					text.setText(String.valueOf(y));
					text.setLayoutX(50 + j * 45);
					text.setLayoutY(nbJoueur * 200 + 50 + i * 50);
					root.getChildren().add(text);

					Rectangle rectangle = new Rectangle(35 + j * 45, nbJoueur * 200 + 35 + i * 50, 45, 50);
					if (y == 0)
						rectangle.setFill(Color.BLACK);
					else
						rectangle.setFill(Color.TRANSPARENT);
					rectangle.setStroke(Color.BLACK);
					root.getChildren().add(rectangle);

					if (tokenDejaTire.contains(y)) {
						Circle c = new Circle(55 + j * 45, nbJoueur * 200 + 58 + i * 50, 15);
						c.setFill(Color.TRANSPARENT);
						c.setStroke(Color.RED);

						root.getChildren().add(c);
					}
					j++;

				}
				i++;

			}
			nbJoueur++;
		}
	}

}
