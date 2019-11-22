package bataille;

import commun.Joueur;
import commun.Partie;
import commun.Popups;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import sudoku.JeuSudoku;
import sudoku.MenuSudoku;
import menu.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import commun.Popups;

import static java.lang.Integer.parseInt;

public class AffichageBN extends Application implements Initializable {
    public static Bataille bataille;

	@FXML
	private Scene scene;

	@FXML
	private Parent root;

	@FXML
	TitledPane pane;

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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.close();
        ArrayList<Joueur> joueurs = Menu.nomsJoueurs;
        this.stage = new Stage();
        this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/ChoixBateau.fxml"));
        this.scene = new Scene(root);
        this.stage.setTitle("Bataille navale !");
        this.stage.setScene(this.scene);
        bataille = new Bataille(new JoueurBataille(joueurs.get(0)), new JoueurBataille(joueurs.get(1)));
        partie();
        stage.setResizable(false);
        this.stage.show();
    }

    private void partie() {
        Partie.initialiser(bataille.getFileName());
        root = FXMLLoader.load(getClass().getResource("../resources/FXML/ChoixTire.fxml"));
        stage.setHeight(800);
        stage.setWidth(800);
    }



    private void partieFinie() {
        Joueur gagnant = bataille.retournerGagnant();
        Partie.ajouterVictoire(bataille.getFileName(), gagnant);
        ArrayList<Joueur> scores = Partie.recupererScore(bataille.getFileName());
        Popups.nom = gagnant.getNom();
        Popups.score = "Vous avez gagné "+ (int)(scores.get(scores.indexOf(gagnant)).getScore()) + " parties.";
        try {
            Popups.victoire((Stage) this.j1.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@FXML
	private ImageView destroyer1;

    @FXML
    private ImageView destroyer2;

    @FXML
    private ImageView cuirasse;

    public void tirer(MouseEvent event) {
        Button button = (Button)event.getSource();
        GridPane tab = (GridPane) button.getParent();
        GridPane nextTab;
        JoueurBataille joueur;
        int i = GridPane.getColumnIndex(button);
        int j = GridPane.getRowIndex(button);
        boolean touche;
        Alert a = new Alert(Alert.AlertType.INFORMATION);


        if(tab.equals(tab1)) {
            nextTab = tab2;
            joueur = bataille.getJ1();
        }
        else {
            nextTab = tab1;
            joueur = bataille.getJ2();
        }
        tab.setDisable(true);
        nextTab.setDisable(false);
        button.setDisable(true);
        touche = joueur.tir(i, j);
        actualiseTir(joueur, i, j, touche);
        if(touche) a.setContentText("Touché !");
        else a.setContentText("Raté !");
        if(bataille.partieFinie()) partieFinie();
        a.show();
        event.consume();
    }

	@FXML
	private ImageView porteAvions;
	@FXML
	private TabPane tabPane;

	//Les gridpanes ci dessous correspondent aux grilles des joueurs � afficher.
	@FXML
	protected static GridPane j1;
    public void actualiseBateau(MouseEvent event){
        ArrayList<Case> cases = new ArrayList<>();
        ArrayList<Bateau> bateaux = new ArrayList<>();
        String path;
        ImageView img;

        cases.add(new Case(2,3));
        bateaux.add(new Bateau("Torpilleur", cases,bataille.getJ1()));
        bataille.getJ1().setListeBateaux(bateaux);
        bataille.getJ2().setListeBateaux(bateaux);
        for (Bateau bateau : bataille.getJ1().getListeBateaux()) {
            path = "resources/image/"+bateau.getName()+".png";
            img = new ImageView(path);
            img.setPreserveRatio(true);
            img.setFitHeight(tab1.getHeight()/10);
            j1.add(img, bateau.getTabCases().get(0).getI(), bateau.getTabCases().get(0).getJ());
        }
        for (Bateau bateau : bataille.getJ2().getListeBateaux()) {
            path = "resources/image/"+bateau.getName()+".png";
            img = new ImageView(path);
            img.setPreserveRatio(true);
            img.setFitHeight(tab1.getHeight()/10);
            j2.add(img, bateau.getTabCases().get(0).getI(), bateau.getTabCases().get(0).getJ());
        }
        event.consume();
    }

    private void actualiseTir(JoueurBataille joueur, int i, int j, boolean touche){
        String path;
        ImageView img;

        if(touche) path="resources/image/touche.png";
        else path="resources/image/rate.png";
        img=new ImageView(path);
        img.setPreserveRatio(true);
        img.setFitHeight(tab1.getHeight()/10);
        if(joueur.equals(bataille.getJ1())) j1.add(img,i,j);
        else j2.add(img,i,j);
    }

	@FXML
	protected static GridPane j2;

	//Actualise les listes des coordonn�es des bateaux des joueurs
	protected static ArrayList<Case> bJ1 = new ArrayList<Case>();
	protected static ArrayList<Case> bJ2 = new ArrayList<Case>();

	public void placementBateaux(int joueur, int i, int j, int taille, int rotation) {
		if(joueur == 1) {
		bJ1.addAll(ajoutBateaux(i, j, taille, rotation));
		} else {
		bJ2.addAll(ajoutBateaux(i, j, taille, rotation));
		}
	}

	//Retourne une liste de cases occup�es par un bateau
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

	public int getColBateau(int i) {
		return GridPane.getColumnIndex(tab1.getChildren().get(i));
	}

	public int getRowBateau(int i) {
		return GridPane.getRowIndex(tab1.getChildren().get(i));
	}

	public void verifError(int a, int b) {

	}

	//G�re la fin du remplissage de la grille des bateaux des deux joueurs
	static boolean turn1 = true;
	public void entrerBateaux() {
		if(turn1) {
			try {
			placementBateaux(1,getColBateau(21),getRowBateau(21),2,(int)torpilleur.getRotate());
			placementBateaux(1,getColBateau(22),getRowBateau(22),3,(int)destroyer1.getRotate());
			placementBateaux(1,getColBateau(23),getRowBateau(23),3,(int)destroyer2.getRotate());
			placementBateaux(1,getColBateau(24),getRowBateau(24),4,(int)cuirasse.getRotate());
			placementBateaux(1,getColBateau(25),getRowBateau(25),5,(int)porteAvions.getRotate());
			System.out.println(bJ1);
			j1 = tab1;
			turn1 = false;
			Stage temp = (Stage) this.pane.getScene().getWindow();
			Popups.joueurDeux(temp, "Joueur 1", "L'ordinateur passe au Joueur 2");
			}catch(Exception e) { erreurBN(); }
		}else {
			try {
			placementBateaux(2,getColBateau(21),getRowBateau(21),2,(int)torpilleur.getRotate());
			placementBateaux(2,getColBateau(22),getRowBateau(22),3,(int)destroyer1.getRotate());
			placementBateaux(2,getColBateau(23),getRowBateau(23),3,(int)destroyer2.getRotate());
			placementBateaux(2,getColBateau(24),getRowBateau(24),4,(int)cuirasse.getRotate());
			placementBateaux(2,getColBateau(25),getRowBateau(25),5,(int)porteAvions.getRotate());
			System.out.println(bJ2);
			j2 = tab1;
			}catch(Exception e) { erreurBN(); }
		}
	}

	public void erreurBN() {
		Window w = pane.getScene().getWindow();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Erreur dans le placement de vos bateaux, il vous manque peut-�tre un bateau ou un de vos bateaux sort de la grille");
		alert.initOwner(w);
		alert.show();

	}

	// Fonction rotation avec clic droit, g�re les rotations des bateaux pairs
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
			//Modifier le tab pour mettre � jourle nolbre de bateaux
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
	//Chargement des r�gles pour le placement des bateaux
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
				return 8;
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
		//System.out.println("Objet lach� en " + x + "-" + y);;
		db = event.getDragboard();
		boolean success = false;
		if (db.hasString()) {
			System.out.println("Taille du bateau: " + bateau.getFitHeight() + "- " + bateau.getFitWidth());
			// Si le bateau est de taille paire il faut faire des choses pour empecher les
			// bugs
			if (((bateau.getFitHeight() / 40 == 2) || ((bateau.getFitHeight() / 40 == 4)))&& ((int) bateau.getRotate()) == 90)bateau.setTranslateX(20);

			int taille = (int) (bateau.getFitHeight() / 40);
			switch ((int) bateau.getRotate()) {
			case 90:
				System.out.println("Bateau pos� en: "+ g(x, taille) +" "+ g(y, 0));
				try{tab1.add(bateau, g(x, taille), g(y, 0));}
				catch(Exception e) {tab1.getChildren().remove(bateau);tab1.add(bateau, g(x, taille), g(y, 0));}
				break;
			case 270:
				System.out.println("Bateau pos� en: "+ g(x, taille) +" "+ g(y, 0));
				try{tab1.add(bateau, g(x, taille), g(y, 0));}
				catch(Exception e) {tab1.getChildren().remove(bateau);tab1.add(bateau, g(x, taille), g(y, 0));}
				break;
			case 0:
				System.out.println("Bateau pos� en: "+ g(x, 0) +" "+ g(y, taille));
				try{tab1.add(bateau, g(x, 0), g(y, taille));}
				catch(Exception e){tab1.getChildren().remove(bateau);tab1.add(bateau, g(x, 0), g(y, taille));}
				break;
			case 180:
				System.out.println("Bateau pos� en: "+ g(x, 0) +" "+ g(y, taille));
				try{tab1.add(bateau, g(x, 0), g(y, taille));}
				catch(Exception e){tab1.getChildren().remove(bateau);tab1.add(bateau, g(x, 0), g(y, taille));}
				break;
			}
			success = true;
		}

		event.setDropCompleted(success);
		event.consume();
	}

	//Actualise les coordon�es du bateau pour le placer
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
		System.out.println("Coordonn�es du clic: " + (int) event.getX() + "; " + (int) event.getY());
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





	public static void main(String[] args) {
		launch(args);
	}
}