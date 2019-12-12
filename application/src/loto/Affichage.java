package loto;

import commun.Joueur;
import commun.Popups;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Affichage extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage,int nbCarton) {
		primaryStage.setTitle("Loto");

		Loto l = new Loto();
			l.addJoueur(Menu.nomsJoueurs,nbCarton);
		int nbJoueur=l.getJoueurs().size();

		Pane root = new Pane();
		Button btn = new Button();
		Label text = new Label();
		text.setLayoutX(550);
		text.setLayoutY(160);
		Label text2 = new Label();


		root.getChildren().add(text2);

		btn.setText("Jouer");
		btn.setLayoutX(85);
		double fenetreY=0;
		if(l.getJoueurs().size()>3){
			btn.setLayoutY(820);
			text2.setLayoutY(825);
			fenetreY=880;
		} else	{
			fenetreY=nbJoueur*200+80;
			btn.setLayoutY(nbJoueur* 200 + 30);
			text2.setLayoutY(btn.getLayoutY()+5);


		}
		text2.setLayoutX(btn.getLayoutX()+150);

		final IntegerProperty i = new SimpleIntegerProperty(0);

		btn.setOnAction(event -> {
			l.tourSuivant();
			i.set(0);

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(() -> {
						text2.setText(String.valueOf(i.get()));

						i.set(i.get()+1);
						if(i.get()==l.getTirage().getNextToken()){
							text2.setText(String.valueOf(i.get()));

							timer.cancel();
							i.set(0);
						}
					});
				}
			}, 100, 13);







			afficherTableau(root, l.getJoueurs(), l.getTiree());
		});


		primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			/**
			 * Gestion du clic sur l'un des grilles
			 */
			public void handle(MouseEvent mouseEvent) {
				ajouterCoche(l,(int)mouseEvent.getX(),(int)mouseEvent.getY());
				afficherTableau(root, l.getJoueurs(), l.getTiree());

				if (l.partieFinie()) {
					if (l.gagnant()) {
						try {
							Popups.nom=l.retournerGagnant().getNom();
							Popups.victoire((Stage) primaryStage.getScene().getWindow());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else afficherPasDeGagnant(root);
				}
			}
		});


		root.getChildren().add(btn);
		root.getChildren().add(text);

		afficherTableau(root, l.getJoueurs(), l.getTiree());

		Scene scene = new Scene(root, (((int)l.getJoueurs().size()/4)+1)*470, fenetreY);
		scene.getStylesheets().add(getClass().getResource("../resources/FXML/loto.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Ecran d'affichage de l'écran pas de gagnant
	 * @param root
	 */
	private void afficherPasDeGagnant(Pane root) {

		root.getChildren().clear();
		Label text = new Label();
		text.setText(" NO GAGNANT");
		text.setLayoutX(250);
		text.setLayoutY(160);
		root.getChildren().add(text);

	}

	/**
	 * Affichage des grilles
	 * @param root
	 * @param lt liste des joueurs
	 * @param tokenDejaTire Liste des chiffres déja tiré
	 */
	public void afficherTableau(Pane root, List<JoueurLoto> lt, List<Integer> tokenDejaTire) {

		int nbJoueur = 0;
		for (JoueurLoto jl : lt) {
			int i = 0;

			Label text = new Label();
			text.setStyle("-fx-background-color:POWDERBLUE");
			text.setText(jl.getNom());
			text.setLayoutX(470*((int)(nbJoueur/4)));
			text.setLayoutY((nbJoueur%4 )* 200  + 102);
			root.getChildren().add(text);


			for (int[] x : jl.getGrille().getMatrice()) {
				int j = 0;
				for (int y : x) {

					Rectangle rectangle = new Rectangle(35 + j * 45+ 470*((int)(nbJoueur/4)), (nbJoueur%4 )* 200 + 35 + i * 50, 45, 50);

					if (y == 0)
						rectangle.getStyleClass().add("caseVide");
					else
						rectangle.getStyleClass().add("caseRemplis");

					rectangle.setStroke(Color.BLACK);
					root.getChildren().add(rectangle);


					if (y != 0) {

						Label text2 = new Label();
						text2.setText(String.valueOf(y));
						text2.setLayoutX(43 + j * 45+470*((int)(nbJoueur/4)));
						text2.setLayoutY((nbJoueur%4 ) * 200 + 48 + i * 50);
						root.getChildren().add(text2);


					}

					if (jl.getCochee().contains(y)) {
						ajouterCercle(root, j, i, nbJoueur);
					}
					j++;

				}
				i++;

			}
			nbJoueur++;
		}
	}

	/**
	 * Ajoute le cercle du coché
	 * @param root
	 * @param x Xieme Case a coché
	 * @param y Ieme Case à coché
	 * @param nbJoueur nIeme Joueur
	 */
	public void ajouterCercle(Pane root, int x, int y, int nbJoueur) {
					Circle c = new Circle(57 + x * 45+470*((int)(nbJoueur/4)), (nbJoueur%4) * 200 + 60 + y * 50, 20);
					c.setFill(Color.TRANSPARENT);
					c.setStroke(Color.RED);
					root.getChildren().add(c);
				}


	/**
	 * Ajoute dans la liste des cochés du joueur si il a bien cliqué sur une bonne case.
	 * @param l Partie de loto
	 * @param x position x du clic du joueur
	 * @param y position y du clic du joueur
	 */
	public void ajouterCoche(Loto l,int x, int y){
		if(l.getJoueurs().size()>3){
			if(y<690){
				int j=(y-35)/200+4*((int)x/470);

				x=(x-35-((470*((int)j/4))))/45;

				y=((y-35)-200*(j%4))/50;
				System.out.println(j+" "+ x +" "+ y);

				if(x<=8&&x>=0&&y< 4){
					System.out.println(l.getJoueurs().get(j).getGrille().getMatrice()[y][x]+"  "+l.getTirage().getNextToken());
					if(l.getJoueurs().get(j).getGrille().getMatrice()[y][x]==l.getTirage().getNextToken()){
						l.getJoueurs().get(j).cocher(y,x);

					}

				}
			}
		}

		else{
			if(y<l.getJoueurs().size()*200){
				int j=(y-35)/200+4*((int)x/470);

				x=(x-35-((470*((int)j/4))))/45;

				y=((y-35)-200*(j%4))/50;
				System.out.println(j+" "+ x +" "+ y);

				if(x<=8&&x>=0&&y< 4){
					System.out.println(l.getJoueurs().get(j).getGrille().getMatrice()[y][x]+"  "+l.getTirage().getNextToken());
					if(l.getJoueurs().get(j).getGrille().getMatrice()[y][x]==l.getTirage().getNextToken()){
						l.getJoueurs().get(j).cocher(y,x);

					}

				}			}
		}

		}

	@Override
	public void start(Stage stage) throws Exception {

	}
}
