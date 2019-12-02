package bataille;

import commun.Joueur;
import commun.Partie;
import commun.Popups;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import menu.Menu;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AffichageBN extends Application implements Initializable {
    public static Bataille bataille;
    private static boolean firstRunPlacement = true;
	private static boolean firstRun = true;

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

	//Grilles de boutons
	@FXML
	private GridPane t1;
	@FXML
	private GridPane t2;
	@FXML
	private GridPane bateauxJ1;
	@FXML
	private GridPane bateauxJ2;

	//images des bateaux
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

	private int x;
	private int y;

    private void partie() throws IOException {
    	Stage temp = (Stage) pane.getScene().getWindow();
        Partie.initialiser(bataille.getFileName());
        root = FXMLLoader.load(getClass().getResource("../resources/FXML/ChoixTire.fxml"));
		temp.setScene(new Scene(this.root));
        temp.setHeight(800);
        temp.setWidth(800);
        firstRun=true;
	}

    private void partieFinie() {
        Joueur gagnant = bataille.retournerGagnant();
        Partie.ajouterVictoire(bataille.getFileName(), gagnant);
        //ArrayList<Joueur> scores = Partie.recupererScore(bataille.getFileName());
        Popups.nom = gagnant.getNom();
        Popups.score = "Vous avez gagné "+ /*(int)(scores.get(scores.indexOf(gagnant)).getScore()) + */" parties.";
        try {
            Popups.victoire((Stage) this.bateauxJ1.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tirer(MouseEvent event) {
        Button button = (Button)event.getSource();
        GridPane t = (GridPane) button.getParent();
        GridPane nextTab;
        JoueurBataille tireur;
        JoueurBataille cible;
        int j = GridPane.getColumnIndex(button);
        int i = GridPane.getRowIndex(button);
        int touche;
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        if(t.equals(t1)) {
            nextTab = t2;
            tireur = bataille.getJ1();
            cible = bataille.getJ2();
        }
        else {
            nextTab = t1;
            tireur = bataille.getJ2();
			cible = bataille.getJ1();
        }
        t.setDisable(true);
        nextTab.setDisable(false);
        button.setDisable(true);
        touche = cible.tir(i, j);
        actualiseBateau(event);
        actualiseTir(tireur, j, i, touche);
		if(bataille.partieFinie()) partieFinie();
		else {
			switch (touche){
				case 0:
					a.setContentText("Raté !");
					break;
				case 1:
					a.setContentText("Touché !");
					break;
				case 2:
					a.setContentText("Coulé !");
					break;
			}
			a.show();
		}
        event.consume();
    }

	private void actualiseTir(JoueurBataille joueur, int j, int i, int touche){
		String path;
		String path2;
		ImageView img;
		ImageView img2;

		if(touche>0) {
			path="resources/image/touche.png";
			path2="resources/image/touche2.png";
		}
		else {
			path="resources/image/rate.png";
			path2="resources/image/rate2.png";
		}
		img=new ImageView(path);
		img.setPreserveRatio(true);
		img.setFitHeight(t1.getHeight()/16);
		img.setTranslateX(7);
		img2=new ImageView(path2);
		img2.setPreserveRatio(true);
		img2.setFitHeight(t1.getHeight()/12);
		img.setTranslateX(6);
		if(joueur.equals(bataille.getJ1())) {
			bateauxJ2.add(img2,j,i);
			t1.add(img,j,i);
			t1.setAlignment(Pos.CENTER);
			bateauxJ2.setAlignment(Pos.CENTER);
		}
		else {
			bateauxJ1.add(img2,j,i);
			t2.add(img,j,i);
			t2.setAlignment(Pos.CENTER);
			bateauxJ1.setAlignment(Pos.CENTER);
		}
	}

	public void actualiseBateau(MouseEvent event){
    	if(firstRun) {
			JoueurBataille j1 = bataille.getJ1();
			JoueurBataille j2 = bataille.getJ2();

			for (Bateau bateau : j1.getListeBateaux()) {
				pickImage(bateau, bateauxJ1);
			}
			for (Bateau bateau : j2.getListeBateaux()) {
				pickImage(bateau, bateauxJ2);
			}
			firstRun = false;
		}
    	event.consume();
    }

	private void pickImage(Bateau bateau, GridPane bateaux) {
		String path = "resources/image/"+bateau.getName()+".png";
		ImageView img = new ImageView(path);
		int i =0,j = 0;
		boolean verticale = bateau.getTabCases().get(0).getJ() == bateau.getTabCases().get(1).getJ();

		switch (bateau.getName()){
			case "Torpilleur":
				img.setFitWidth(40);
				img.setFitHeight(70);
				i = bateau.getTabCases().get(0).getI();
				j = bateau.getTabCases().get(0).getJ();
				if(!verticale) img.setRotate(90);
				break;
			case "Destroyeur":
				img.setFitWidth(40);
				img.setFitHeight(105);
				i = bateau.getTabCases().get(1).getI();
				j = bateau.getTabCases().get(1).getJ();
				if(!verticale) img.setRotate(90);
				break;
			case "Cuirasse":
				img.setFitWidth(40);
				img.setFitHeight(140);
				i = bateau.getTabCases().get(1).getI();
				j = bateau.getTabCases().get(1).getJ();
				if(!verticale) img.setRotate(90);
				break;
			case "Porte-avion":
				img.setFitWidth(40);
				img.setFitHeight(175);
				i = bateau.getTabCases().get(2).getI();
				j = bateau.getTabCases().get(2).getJ();
				if(!verticale) img.setRotate(90);
				break;
		}
		if( (bateau.getName().equals("Torpilleur") || bateau.getName().equals("Cuirasse") ) && !verticale) img.setTranslateX(35/2f);
		if( (bateau.getName().equals("Torpilleur") || bateau.getName().equals("Cuirasse") ) && verticale) img.setTranslateY(35/2f);

		bateaux.add(img,j,i);
    }

	//Gère la fin du remplissage de la grille des bateaux des deux joueurs
	public void entrerBateaux() {
		if(firstRunPlacement) {
			try {
				placementBateaux(1,getRowBateau(21),getColBateau(21),2,(int)torpilleur.getRotate());
				placementBateaux(1,getRowBateau(22),getColBateau(22),3,(int)destroyer1.getRotate());
				placementBateaux(1,getRowBateau(23),getColBateau(23),3,(int)destroyer2.getRotate());
				placementBateaux(1,getRowBateau(24),getColBateau(24),4,(int)cuirasse.getRotate());
				placementBateaux(1,getRowBateau(25),getColBateau(25),5,(int)porteAvions.getRotate());
				firstRunPlacement = false;
				Stage temp = (Stage) this.pane.getScene().getWindow();
				Popups.joueurDeux(temp, "Joueur 1", "Valider et passer la main au Joueur 2");
			}catch(Exception e) { erreurBN(); }
		}else {
			try {
				placementBateaux(2,getRowBateau(21),getColBateau(21),2,(int)torpilleur.getRotate());
				placementBateaux(2,getRowBateau(22),getColBateau(22),3,(int)destroyer1.getRotate());
				placementBateaux(2,getRowBateau(23),getColBateau(23),3,(int)destroyer2.getRotate());
				placementBateaux(2,getRowBateau(24),getColBateau(24),4,(int)cuirasse.getRotate());
				placementBateaux(2,getRowBateau(25),getColBateau(25),5,(int)porteAvions.getRotate());
				partie();
			}catch(Exception e) { erreurBN();e.printStackTrace(); }
		}
	}

	private void placementBateaux(int joueur, int i, int j, int taille, int rotation) {
		if(joueur == 1) {
			ArrayList<Bateau> bateaux = bataille.getJ1().getListeBateaux();
			bateaux.add(new Bateau(ajoutBateaux(i, j, taille, rotation),bataille.getJ1()));
			bataille.getJ1().setListeBateaux(bateaux);
		} else {
			ArrayList<Bateau> bateaux = bataille.getJ2().getListeBateaux();
			bateaux.add(new Bateau(ajoutBateaux(i, j, taille, rotation),bataille.getJ2()));
			bataille.getJ2().setListeBateaux(bateaux);
		}
	}

	//Retourne une liste de cases occup�es par un bateau
	private ArrayList<Case> ajoutBateaux(int i, int j, int taille, int rotation) {
		ArrayList<Case> temp = new ArrayList<>();
		i--;
		j--;
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
				temp.add(new Case(i-2,j));
				temp.add(new Case(i-1,j));
				temp.add(new Case(i,j));
				temp.add(new Case(i+1,j));
				temp.add(new Case(i+2,j));
				break;
			}
		}
		return temp;
	}

	private int getColBateau(int i) {
		return GridPane.getColumnIndex(tab1.getChildren().get(i));
	}

	private int getRowBateau(int i) {
		return GridPane.getRowIndex(tab1.getChildren().get(i));
	}

	//public void verifError(int a, int b) { }

	private void erreurBN() {
		Window w = pane.getScene().getWindow();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Erreur dans le placement de vos bateaux, il vous manque peut-être un bateau ou un de vos bateaux sort de la grille");
		alert.initOwner(w);
		alert.show();
	}

	// Fonction rotation avec clic droit, gère les rotations des bateaux pairs
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

	/*
	// Objet target
	//Inutile atm
	private void dragEntered(DragEvent event) {
		if (event.getGestureSource() != tab1 && event.getDragboard().hasString()) {

		}

		event.consume();
	}

	private void dragDone(DragEvent event) {
		if (event.getTransferMode() == TransferMode.MOVE) {
			//Modifier le tab pour mettre � jourle nolbre de bateaux
		}
		event.consume();
	}

	//Inutile atm
	private void dragExited(DragEvent event) {

		event.consume();
	}


	//Chargement des r�gles pour le placement des bateaux
	private final int n = 440; // taille de la Vbox; une case = 40*40px; il y a en tout 11 cases*/

	private int g(int a, int tailleBateau) {
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

	// Fonctions pour Drag and drop
	private Dragboard db;
	private ImageView bateau;

	@FXML
	private void dragDetected(MouseEvent event) {
		bateau = (ImageView) event.getSource();
		db = bateau.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();
		content.putString(bateau.getId());
		db.setContent(content);
		event.consume();
	}

	@FXML
	public void dragDropped(DragEvent event) {
		db = event.getDragboard();
		boolean success = false;
		if (db.hasString()) {
			// Si le bateau est de taille paire il faut faire des choses pour empecher les bugs
			if (((bateau.getFitHeight() / 40 == 2) || ((bateau.getFitHeight() / 40 == 4)))&& ((int) bateau.getRotate()) == 90) bateau.setTranslateX(20);
			int taille = (int) (bateau.getFitHeight() / 40);
			switch ((int) bateau.getRotate()) {
				case 90:
					try{tab1.add(bateau, g(x, taille), g(y, 0));}
					catch(Exception e) {tab1.getChildren().remove(bateau);tab1.add(bateau, g(x, taille), g(y, 0));}
					break;
				case 0:
					try{tab1.add(bateau, g(x, 0), g(y, taille));}
					catch(Exception e){tab1.getChildren().remove(bateau);tab1.add(bateau, g(x, 0), g(y, taille));}
					break;
			}
			success = true;
		}

		event.setDropCompleted(success);
		event.consume();
	}

	//Actualise les coordonées du bateau pour le placer
	@FXML
	public void dragOver(DragEvent event) {
		x = ((int) event.getX());
		y = ((int) event.getY());
		if (event.getGestureSource() != tab1 && event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}

		event.consume();
	}

	/*
	//Inutile atm
	public boolean mousePressed(MouseEvent event) {
		System.out.println("Coordonn�es du clic: " + (int) event.getX() + "; " + (int) event.getY());
		return true;
	}

	//Inutile atm
	protected boolean mouseDragged(MouseEvent event) {

		event.consume();
		return true;
	}

	//Inutile atm
	protected boolean mouseReleased(int x, int y) {
		// setCursor(Cursor.DEFAULT);
		return true;
	}*/

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
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
		if(firstRun)bataille = new Bataille(new JoueurBataille(joueurs.get(0)), new JoueurBataille(joueurs.get(1)));
		firstRun = false;
		stage.setResizable(false);
		this.stage.show();
	}

	public static void main(String[] args) { launch(args); }
}