package bataille;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku.JeuSudoku;
import sudoku.MenuSudoku;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AffichageBN extends Application implements Initializable {
	private static Bataille bataille;

	@FXML
	private Scene scene;

	@FXML
	private Parent root;

	@FXML
	private Stage stage;

	@FXML // GridPane choix placement bateau
	private GridPane tab1;

	@FXML
	private GridPane tab2;

	@FXML
	private ImageView[] bateaux;

	@FXML
	private ImageView torpilleur;

	@FXML
	private ImageView destroyer1;

	@FXML
	private ImageView destroyer2;

	@FXML
	private ImageView cuirasse;

	@FXML
	private ImageView porteAvions;
	@FXML
	private TabPane tabPane;

	public void tirer(MouseEvent event) {
		Button button = (Button) event.getSource();
		GridPane tab = (GridPane) button.getParent();
		GridPane nextTab;
		JoueurBataille joueur;
		int i = GridPane.getColumnIndex(button);
		int j = GridPane.getRowIndex(button);
		boolean touche;

		if (tab.equals(tab1)) {
			nextTab = tab2;
			joueur = bataille.getJ1();
		} else {
			nextTab = tab1;
			joueur = bataille.getJ2();
		}
		tab.setDisable(true);
		nextTab.setDisable(false);
		button.setDisable(true);
		touche = joueur.tir(i, j);

		Alert a = new Alert(Alert.AlertType.INFORMATION);
		actualise(joueur, i, j, touche);
		if (touche)
			a.setContentText("TouchÃ© !");
		else
			a.setContentText("RatÃ© !");
		a.show();
		event.consume();
	}

	@FXML
	private GridPane j1;

	@FXML
	private GridPane j2;

	public void actualise(JoueurBataille joueur, int i, int j, boolean touche) {
		String path;
		ImageView img;

		System.out.println(i + " " + " " + j);
		if (touche)
			path = "resources/image/touche.png";
		else
			path = "resources/image/rate.png";
		img = new ImageView(path);
		img.setPreserveRatio(true);
		img.setFitHeight(70);
		if (joueur.equals(bataille.getJ1()))
			j1.add(img, i, j);
		else
			j2.add(img, i, j);
	}

	//Actualise les listes des coordonnées des bateaux des joueurs
	ArrayList<Case> bJ1 = new ArrayList<Case>();
	ArrayList<Case> bJ2 = new ArrayList<Case>();
	public void placementBateaux(int joueur, int i, int j, int taille, int rotation) {
		if(joueur == 1) {
		bJ1.addAll(ajoutBateaux(i, j, taille, rotation));	
		} else {
		bJ2.addAll(ajoutBateaux(i, j, taille, rotation));
		}
	}
	
	//Retourne une liste de cases occupées par un bateau
	public ArrayList<Case> ajoutBateaux(int i, int j, int taille, int rotation) {
		ArrayList<Case> temp = new ArrayList<Case>();
		if(rotation == 90){
			switch(taille) {
			case 2 :
				temp.add(new Case(i,j));
				temp.add(new Case(i,j+1));
				break;
			case 3 :
				temp.add(new Case(i,j-1));
				temp.add(new Case(i,j));
				temp.add(new Case(i,j+1));
				break;
			case 4 :
				temp.add(new Case(i,j-1));
				temp.add(new Case(i,j));
				temp.add(new Case(i,j+1));
				temp.add(new Case(i,j+2));
				break;
			case 5 :
			temp.add(new Case(i,j-2));
			temp.add(new Case(i,j-1));
			temp.add(new Case(i,j));
			temp.add(new Case(i,j+1));
			temp.add(new Case(i,j+2));
				break;
			}
		}else {
			switch(taille) {
			case 2 :
				temp.add(new Case(i,j));
				temp.add(new Case(i+1,j));
				break;
			case 3 :
				temp.add(new Case(i-1,j));
				temp.add(new Case(i,j));
				temp.add(new Case(i+1,j));
				break;
			case 4 :
				temp.add(new Case(i-1,j));
				temp.add(new Case(i,j));
				temp.add(new Case(i+1,j));
				temp.add(new Case(i+2,j));
				break;
			case 5 :
			temp.add(new Case(i-2,j-2));
			temp.add(new Case(i-1,j-1));
			temp.add(new Case(i,j));
			temp.add(new Case(i+1,j));
			temp.add(new Case(i+2,j));
				break;
			}
		}
		System.out.println("Ajout du bateau sur les cases: " + temp.toString());
		return temp;
	}
	
	//Gére la fin du remplissage de la grille des bateaux des deux joueurs
	static boolean turn1 = true;
	public void entrerBateaux() {
		if(turn1) {
			placementBateaux(1,0,0,2,(int)torpilleur.getRotate());
			placementBateaux(1,0,0,3,(int)destroyer1.getRotate());
			placementBateaux(1,0,0,3,(int)destroyer2.getRotate());
			placementBateaux(1,0,0,4,(int)cuirasse.getRotate());
			placementBateaux(1,0,0,5,(int)porteAvions.getRotate());
			turn1 = false;
			Stage temp = (Stage) this.tab1.getScene().getWindow();
			AffichageBN m = new AffichageBN();
			try {
				m.start(temp);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			placementBateaux(2,0,0,2,(int)torpilleur.getRotate());
			placementBateaux(2,0,0,3,(int)destroyer1.getRotate());
			placementBateaux(2,0,0,3,(int)destroyer2.getRotate());
			placementBateaux(2,0,0,4,(int)cuirasse.getRotate());
			placementBateaux(2,0,0,5,(int)porteAvions.getRotate());
		}
	}

	// Fonction rotation avec clic droit, gére les rotations des bateaux pairs
	@FXML
	protected void rotate(MouseEvent event) {
		if (event.getButton() == MouseButton.SECONDARY) {
			boolean pair = ((((ImageView) event.getSource()).getFitHeight() / 40 == 2) || (((ImageView) event.getSource()).getFitHeight() / 40 == 4));
			double current = ((ImageView) event.getSource()).getRotate();
			if (current + 90 == 180) {
				((ImageView) event.getSource()).setRotate(0);
				if(pair) {
				((ImageView) event.getSource()).setTranslateX(0);
				((ImageView) event.getSource()).setTranslateY(20);
				}
			}else {
				((ImageView) event.getSource()).setRotate(current + 90);
				if(pair) {
				((ImageView) event.getSource()).setTranslateY(0);
				((ImageView) event.getSource()).setTranslateX(20);
				}
			}
		}
	}

	
	//Inutile atm
	@FXML
	protected Boolean mouseMoved() {
		System.out.println("Got it");
		return true;
	}

	// Fonctions pour Drag and drop
	Dragboard db;
	ImageView bateau;

	public void dragDetected(MouseEvent event) {
		bateau = (ImageView) event.getSource();
		db = bateau.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();
		content.putString(bateau.getId());
		db.setContent(content);
		event.consume();
	}

	public void dragDone(DragEvent event) {
		if (event.getTransferMode() == TransferMode.MOVE) {
			//Modifier le tab pour mettre à jourle nolbre de bateaux
		}
		event.consume();
	}

	// Objet target
	//Inutile atm
	public void dragEntered(DragEvent event) {
		if (event.getGestureSource() != tab1 && event.getDragboard().hasString()) {

		}

		event.consume();
	}

	//Inutile atm
	public void dragExited(DragEvent event) {

		event.consume();
	}

	////////////////////////////////////
	//Chargement des régles pour le placement des bateaux
	private final int n = 440; // taille de la Vbox; une case = 40*40px; il y a en tout 11 cases

	public int g(int a, int tailleBateau) {
		// cas horizontal
		switch (tailleBateau) {
		case 0:
			if (a < 80)
				return 1;
			if (a < 120)
				return 2;
			if (a < 160)
				return 3;
			if (a < 200)
				return 4;
			if (a < 240)
				return 5;
			if (a < 280)
				return 6;
			if (a < 320)
				return 7;
			if (a < 360)
				return 8;
			if (a < 400)
				return 9;
			if (a < 440)
				return 10;
		case 2:
			if (a < 80)
				return 1;
			if (a < 120)
				return 2;
			if (a < 160)
				return 3;
			if (a < 200)
				return 4;
			if (a < 240)
				return 5;
			if (a < 280)
				return 6;
			if (a < 320)
				return 7;
			if (a < 360)
				return 8;
			if (a > 360)
				return 9;
			else
				return 5;
		case 3:
			if (a < 120)
				return 2;
			if (a < 160)
				return 3;
			if (a < 200)
				return 4;
			if (a < 240)
				return 5;
			if (a < 280)
				return 6;
			if (a < 320)
				return 7;
			if (a < 360)
				return 8;
			if (a > 360)
				return 9;
			else
				return 5;
		case 4:
			if (a < 80)
				return 1;
			if (a < 120)
				return 2;
			if (a < 160)
				return 3;
			if (a < 200)
				return 4;
			if (a < 240)
				return 5;
			if (a < 280)
				return 6;
			if (a < 320)
				return 7;
			if (a < 360)
				return 8;
			if (a < 400)
				return 9;
			if (a < 440)
				return 10;
			else
				return 5;
		case 5:
			if (a < 160)
				return 3;
			if (a < 200)
				return 4;
			if (a < 240)
				return 5;
			if (a < 280)
				return 6;
			if (a < 320)
				return 7;
			if (a > 320)
				return 8;
			else
				return 5;
		default:
			return 5;
		}
	}

	
	public void dragDropped(DragEvent event) {
		//System.out.println("Objet laché en " + x + "-" + y);;
		db = event.getDragboard();
		boolean success = false;
		if (db.hasString()) {
			System.out.println("Taille du bateau: " + bateau.getFitHeight() + "- " + bateau.getFitWidth());
			// Si le bateau est de taille paire il faut faire des choses pour empecher les
			// bugs
			if (((bateau.getFitHeight() / 40 == 2) || ((bateau.getFitHeight() / 40 == 4)))&& ((int) bateau.getRotate()) == 90)bateau.setTranslateX(20);

			int taille = (int) (bateau.getFitHeight() / 40);
			//if((taille == 2)||(taille==4)) x++;
			switch ((int) bateau.getRotate()) {
			case 90:
				tab1.add(bateau, g(x, taille), g(y, 0));
				System.out.println(ajoutBateaux(g(x, taille),g(y, 0),taille,(int)bateau.getRotate()));
				break;
			case 270:
				tab1.add(bateau, g(x, taille), g(y, 0));
				break;
			case 0:
				tab1.add(bateau, g(x, 0), g(y, taille));
				break;
			case 180:
				tab1.add(bateau, g(x, 0), g(y, taille));
				break;
			}
			success = true;
		}

		event.setDropCompleted(success);

		event.consume();
	}

	//Actualise les coordonées du bateau pour le placer
	public void dragOver(DragEvent event) {
		x = ((int) event.getX());
		y = ((int) event.getY());
		if (event.getGestureSource() != tab1 && event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}

		event.consume();
	}

	int x;
	int y;

	//Inutile atm
	public boolean mousePressed(MouseEvent event) {
		System.out.println("Coordonnées du clic: " + (int) event.getX() + "; " + (int) event.getY());
		return true;
	}

	//Inutile atm
	protected boolean mouseDragged(MouseEvent event) {

		return true;
	}

	//Inutile atm
	protected boolean mouseReleased(int x, int y) {
		// setCursor(Cursor.DEFAULT);
		return true;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
		this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/ChoixBateau.fxml"));
		this.scene = new Scene(root);
		this.stage.setTitle("Bataille navale !");
		this.stage.setScene(this.scene);
		JoueurBataille j1 = new JoueurBataille("Gabriel");
		JoueurBataille j2 = new JoueurBataille("Romane");
		AffichageBN.bataille = new Bataille(j1, j2);

		this.stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}