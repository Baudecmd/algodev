package loto;

import commun.Joueur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import menu.Menu;

import java.util.List;

public class Affichage extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Loto :p");

		Loto l = new Loto(Menu.nomsJoueurs);
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


		btn.setOnAction(event -> {
			l.tourSuivant();
			text2.setText(String.valueOf(l.getTirage().getNextToken()));
			afficherTableau(root, l.getJoueurs(), l.getTiree());
		});


		primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				ajouterCercle(root,l,(int)mouseEvent.getX(),(int)mouseEvent.getY());
				afficherTableau(root, l.getJoueurs(), l.getTiree());

				if (l.partieFinie()){
					if(l.gagnant())afficherEcranFin(root,l.retournerGagnant());
					else afficherPasDeGagnant(root);
				}
			}
		});


		root.getChildren().add(btn);
		root.getChildren().add(text);

		afficherTableau(root, l.getJoueurs(), l.getTiree());
		primaryStage.setScene(new Scene(root, 650, 200 * l.getJoueurs().size()));
		primaryStage.show();
	}

	private void afficherPasDeGagnant(Pane root) {

		root.getChildren().clear();
		Label text = new Label();
		text.setText(" NO GAGNANT");
		text.setLayoutX(250);
		text.setLayoutY(160);
		root.getChildren().add(text);

	}

	private void afficherEcranFin(Pane root, Joueur retournerGagnant) {
		//gagnant
		root.getChildren().clear();
		Label text = new Label();
		text.setText(retournerGagnant.toString());
		text.setLayoutX(250);
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
					j++;

				}
				i++;

			}
			nbJoueur++;
		}
	}

	public void ajouterCercle(Pane root,Loto l,int x,int y){
		if(x<500){
			x-=50;
			x=(int)x/45;
			int j=(int)y/185;
			y=((int)((y%185)/50)-1);

			System.out.println("x "+x+" y "+y+ " j "+j);
			System.out.println(l.getJoueurs().get(j).getNom());
			if(l.getJoueurs().get(j).cocher(y,x)){
				Circle c = new Circle(55 + x * 45, j * 200 + 58 + y * 50, 15);
				c.setFill(Color.TRANSPARENT);
				c.setStroke(Color.RED);
				root.getChildren().add(c);

			}
		}
	}

}