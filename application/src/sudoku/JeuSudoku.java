package sudoku;

import javafx.animation.KeyFrame;
import commun.Grille;
import commun.Joueur;
//import commun.Partie;
import javafx.animation.Timeline;
import javafx.application.Application;
import commun.Popups;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class JeuSudoku extends Application implements Initializable {
	final int coteCase = 50;

	@FXML
	Button buttonUN;
	@FXML
	Button buttonDEUX;
	@FXML
	Button buttonTROIS;
	@FXML
	Button buttonQUATRE;
	@FXML
	Button buttonCINQ;
	@FXML
	Button buttonSIX;
	@FXML
	Button buttonSEPT;
	@FXML
	Button buttonHUIT;
	@FXML
	Button buttonNEUF;
	@FXML
	Button buttonOPTION;
	@FXML
	Canvas grille;
	@FXML
	Parent root;
	@FXML
	Text text;
	
	public static ArrayList<Joueur> nomsJoueurs;


	int player_selected_row;
	int player_selected_col;
	
	public static Sudoku game;

	static Grille initial = new Grille(9,9,new int[][]{ { 0, 0, 0, 8, 0, 0, 0, 7, 0 }, { 0, 0, 0, 0, 0, 0, 8, 0, 0 },
		{ 0, 3, 0, 7, 5, 0, 0, 0, 4 }, { 0, 0, 9, 0, 2, 0, 0, 3, 0 }, { 0, 8, 0, 4, 1, 9, 0, 0, 0 },
		{ 0, 5, 0, 0, 0, 0, 4, 0, 0 }, { 3, 0, 0, 0, 0, 0, 5, 0, 6 }, { 0, 0, 0, 0, 9, 0, 7, 0, 8 },
		{ 0, 1, 6, 0, 8, 0, 0, 0, 0 } });// remplacer la matrice pour importer
	static Grille grilleJoueur = recopie(initial);
	static Grille grilleComplete = new Grille(9,9);

	public static void setInitial(Grille _initial) {
		initial = _initial;
		grilleJoueur = recopie(initial);
	}

	

	public static void setGrilleComplete(Grille grilleComplete) {
		JeuSudoku.grilleComplete = grilleComplete;
	}



	// Fonction recopie à remplacer lorsque j'aurai trouvé la solution du probléme
	public static Grille recopie(Grille a) {
		if (a != null) {
			Grille temp = new Grille(a.getNbLignes(),a.getNbLignes());
			for (int i = 0; i < a.getNbLignes(); i++) {
				for (int j = 0; j < a.getNbLignes(); j++) {
					temp.getMatrice()[i][j] = a.getMatrice()[i][j];
				}
			}
			return temp;
		} else
			return null;
	}

	public JeuSudoku(Sudoku s) {
		this.initial = Sudoku.getGrille();
		this.grilleJoueur = recopie(initial);
	}

	public JeuSudoku(Grille _initial) {
		this.initial = _initial;
		this.grilleJoueur = recopie(_initial);
	}
	
	public JeuSudoku() {
	}

	int valErr,rowErr,colErr;
	public void actualiserGrille(int val, int row, int col) {
		if ((val >= 0 && val <= 9) && (initial.getMatrice()[row][col] == 0)) {
			game.getJoueur().setCoutCourant(new Coup(row,col,val));
			if(game.possibilite()) grilleJoueur.getMatrice()[row][col] = val;
			else {
				valErr = val; rowErr = row; colErr = col;
			}
		}else // Si le joueur essaye de modifier une valeur de base de la grille ou si on sort
				// de la grille
			System.out.println("Valeur en dehors de la grille ou valeur inchangeable !");
		PartieFinie();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.player_selected_col = 0;
		this.player_selected_row = 0;
		// Get graphics context from canvas
		GraphicsContext context = grille.getGraphicsContext2D();
		// Dessine sur le canvas
		drawOnCanvas(context);

	}
	
	//here
	public void afficherErreur(int val,int row,int col) {
		int position_y = row * coteCase + 2;
		int position_x = col * coteCase + 2;
		int width = 30;
		grille.getGraphicsContext2D().setFill(Color.WHITE);
		grille.getGraphicsContext2D().fillRoundRect(position_x+10, position_y+10, width, width, 10, 10);
		position_y = row * coteCase + 30;
		position_x = col * coteCase + 20;
		grille.getGraphicsContext2D().setFill(Color.RED);
		grille.getGraphicsContext2D().setFont(new Font("Courier New"/* ,FontWeight.BOLD */, 20));
		grille.getGraphicsContext2D().fillText(val + "", position_x, position_y);
	}

	public void drawOnCanvas(GraphicsContext context) {
		// Dessine la grille
		context.clearRect(0, 0, 450, 450);
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				// pour trouver la postion de la case, on multiplie son indice par coteCase
				// (taille d'une case) on ajoute 2 pour l'espace intercase
				int position_y = row * coteCase + 2;
				int position_x = col * coteCase + 2;
				// 46 car 50 - 2*2
				int width = 46;

				// Couleur de la grille
				context.setFill(Color.WHITE);
				context.fillRoundRect(position_x, position_y, width, width, 10, 10);
			}
		}
		// Dessine le contour de la case une fois selectionnée par le joueur
		context.setStroke(Color.RED);
		context.setLineWidth(3);
		context.strokeRoundRect(player_selected_col * coteCase + 2, player_selected_row * coteCase + 2, 46, 46, 10, 10);
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				int position_y = row * coteCase + 30;
				int position_x = col * coteCase + 20;
				context.setFill(Color.BLACK);
				context.setFont(new Font("Courier New"/* ,FontWeight.BOLD */, 20));
				// affiche uniquement les valeurs non nulles de la grille
				if (grilleJoueur.getMatrice()[row][col] != 0) {
					context.fillText(grilleJoueur.getMatrice()[row][col] + "", position_x, position_y);
				}
			}
		}
		// recopie la grille de base en violet
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				int position_y = row * coteCase + 30;
				int position_x = col * coteCase + 20;
				context.setFill(Color.PURPLE);
				context.setFont(new Font("Courier New"/* ,FontWeight.BOLD */, 20));
				if (initial.getMatrice()[row][col] != 0) {
					context.fillText(initial.getMatrice()[row][col] + "", position_x, position_y);
				}
			}
		}
		// Dessine les séparations des régions du Sudoku
		context.setLineWidth(4);
		context.setStroke(Color.BLACK);
		context.strokeRoundRect(0, 0, 450, 450, 10, 10);
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int position_y = row * 3 * coteCase;
				int position_x = col * 3 * coteCase;
				context.strokeRoundRect(position_x, position_y, 3 * position_x + 3 * coteCase,
						3 * position_y + 3 * coteCase, 10, 10);
			}
		}
		if(valErr != 0) {
			afficherErreur(valErr,rowErr,colErr);
			valErr = 0;
		}
	}

	public void canvasMouseClicked() {
		//Change le EventHandler du canvas
		grille.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				// récupére les coordonées du clic et les transforme en int
				int mouse_x = (int) event.getX();
				int mouse_y = (int) event.getY();
				//Met à jour la case selectionnée par le joueur
				player_selected_row = (int) (mouse_y / coteCase); 
				player_selected_col = (int) (mouse_x / coteCase);

				// redessine la grille
				drawOnCanvas(grille.getGraphicsContext2D());
			}
		});
	}

	public void buttonUNPressed() {
		this.actualiserGrille(1, player_selected_row, player_selected_col);
		// refresh le canvas
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonDEUXPressed() {
		this.actualiserGrille(2, player_selected_row, player_selected_col);
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonTROISPressed() {
		this.actualiserGrille(3, player_selected_row, player_selected_col);
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonQUATREPressed() {
		this.actualiserGrille(4, player_selected_row, player_selected_col);
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonCINQPressed() {
		this.actualiserGrille(5, player_selected_row, player_selected_col);
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonSIXPressed() {
		this.actualiserGrille(6, player_selected_row, player_selected_col);
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonSEPTPressed() {
		this.actualiserGrille(7, player_selected_row, player_selected_col);
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonHUITPressed() {
		this.actualiserGrille(8, player_selected_row, player_selected_col);
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonNEUFPressed() {
		this.actualiserGrille(9, player_selected_row, player_selected_col);
		drawOnCanvas(grille.getGraphicsContext2D());
	}

	public void buttonOPTIONPressed() {
		Popups.options((Stage) this.buttonOPTION.getScene().getWindow(), "Option", "Option de Jeu");
	}

	boolean started = false;

	public void textSet() {
		if (!started) {
			Popups.timer(text);
			started = true;
		}
	}

	public void PartieFinie() {
		if (verification(grilleJoueur,grilleComplete)) {
			try {
				Popups.nom = nomsJoueurs.get(0).getNom(); // Nom du Joueur
				Popups.score = text.getText();// Score du Joueur
				Popups.victoire((Stage) this.buttonUN.getScene().getWindow());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean verification(Grille a,Grille b) {
		for(int i = 0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(a.getMatrice()[i][j]!= b.getMatrice()[i][j])
					return false;
			}
		}
		return true;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Welcome" + nomsJoueurs.get(0).getNom());
		this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/fenetre.fxml"));
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
