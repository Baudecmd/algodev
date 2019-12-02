package loto;

import commun.Popups;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import java.util.Timer;
import java.util.TimerTask;

public class Affichage extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Loto");

		Loto l = new Loto(Menu.nomsJoueurs);
		Pane root = new Pane();
		Button btn = new Button();
		Label text = new Label();
		text.setLayoutX(550);
		text.setLayoutY(160);
		Label text2 = new Label();
		text2.setLayoutX(605);
		text2.setLayoutY(180);
		root.getChildren().add(text2);



		btn.setText("Jouer");
		btn.setLayoutX(580);
		btn.setLayoutY(100);
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
							timer.cancel();
							i.set(0);
						}
					});
				}
			}, 100, 13);







			//text2.setText(String.valueOf(l.getTirage().getNextToken()));
			afficherTableau(root, l.getJoueurs(), l.getTiree());
		});


		primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
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

		Scene scene = new Scene(root, 800, 10+	200 * l.getJoueurs().size());
		scene.getStylesheets().add(getClass().getResource("../resources/FXML/loto.css").toExternalForm());
		primaryStage.setScene(scene);
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


	public void afficherTableau(Pane root, List<JoueurLoto> lt, List<Integer> tokenDejaTire) {

		int nbJoueur = 0;
		for (JoueurLoto jl : lt) {
			int i = 0;

			Label text = new Label();
			text.setStyle("-fx-background-color:POWDERBLUE");
			text.setText(jl.getNom());
			text.setLayoutX(465);
			text.setLayoutY(nbJoueur * 200 + 102);
			root.getChildren().add(text);


			for (int[] x : jl.getGrille().getMatrice()) {
				int j = 0;
				for (int y : x) {

					Rectangle rectangle = new Rectangle(35 + j * 45, nbJoueur * 200 + 35 + i * 50, 45, 50);

					if (y == 0)
						rectangle.getStyleClass().add("caseVide");
					else
						rectangle.getStyleClass().add("caseRemplis");

					rectangle.setStroke(Color.BLACK);
					root.getChildren().add(rectangle);


					if (y != 0) {

						Label text2 = new Label();
						text2.setText(String.valueOf(y));
						text2.setLayoutX(43 + j * 45);
						text2.setLayoutY(nbJoueur * 200 + 48 + i * 50);
						root.getChildren().add(text2);


					}

					if (jl.getCochee().contains(y)) {
						System.out.println(i+" "+j+" "+nbJoueur);
						ajouterCercle(root, j, i, nbJoueur);
					}
					j++;

				}
				i++;

			}
			nbJoueur++;
		}
	}

	public void ajouterCercle(Pane root, int x, int y, int nbJoueur) {

					Circle c = new Circle(57 + x * 45, nbJoueur * 200 + 60 + y * 50, 20);
					c.setFill(Color.TRANSPARENT);
					c.setStroke(Color.RED);
					root.getChildren().add(c);

				}


	public void ajouterCoche(Loto l,int x, int y){
		if(x< 500){
		x=(x-35)/45;
		int j=(y-35)/200;
		y=((y-35)-200*j)/50;
		if(x<=8&&x>=0&&y< 4){
		l.getJoueurs().get(j).cocher(y,x);

		}
		}
		}
		}