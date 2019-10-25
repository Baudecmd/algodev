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
import menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Affichage extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Loto l = new Loto(Menu.nomsJoueurs);
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

				afficherTableau(root, l.getJoueurs(), l.getTiree());

				if (l.partieFinie())
					afficherEcranFin(root, l.retournerGagnant());

			}
		});
		root.getChildren().add(btn);
		root.getChildren().add(text);

		afficherTableau(root, l.getJoueurs(), l.getTiree());
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

	public void afficherTableau(Pane root, List<JoueurLoto> lt, List<Integer> tokenDejaTire) {

		int nbJoueur = 0;
		for (JoueurLoto jl : lt) {
			int i = 0;


			Label text = new Label();
			text.setText(jl.getNom());
			text.setLayoutX(465);
			text.setLayoutY(nbJoueur * 200 +102);
			root.getChildren().add(text);

			for (int[] x : jl.getGrille().getMatrice()) {
				int j = 0;
				for (int y : x) {
					Label text2 = new Label();
					text2.setText(String.valueOf(y));
					text2.setLayoutX(50 + j * 45);
					text2.setLayoutY(nbJoueur * 200 + 50 + i * 50);
					root.getChildren().add(text2);

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
