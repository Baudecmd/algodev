package menu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import loto.Affichage;
import loto.SelecGrille;
import sudoku.JeuSudoku;
import sudoku.MenuSudoku;
import poker.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import commun.*;

public class Menu extends Application {

	public static ArrayList<Joueur> nomsJoueurs = new ArrayList<Joueur>();

	public static int nbJoueurInt;
	public static int nbJoueurInt2;
	public static int i = 1;

	@FXML
	private TextField nbJoueur;

	@FXML
	private TextField nomJoueur;

	@FXML
	private Button entrerNomJoueur;

	@FXML
	private Button entrerNbJoueur;
	
	@FXML
	private Button credit;
	
	@FXML
	private Button jouer;

	@FXML
	private Scene scene;

	@FXML
	private Parent root;

	@FXML
	private Stage stage;

	@FXML
	private Button loto;

	@FXML
	private Label labelNomJoueur;
	
	/**Simple fonction de cast, d'un string en int d'une premi�re fa�on
	 */

	public static void stringtoint(String s, int a) {
		a = Integer.parseInt(s);
	}
	
	/**Simple fonction de cast, d'un string en int d'une deuxi�me fa�on 
	 */

	public static int stringtoint2(String s) {
		int a = Integer.parseInt(s);
		return a;
	}

	/**Proc�dure permettant de lancer une nouvelle partie de loto
	 * � l'aide des infomations sur les joueurs pr�c�dement rentr�es
	 */
	public void handlePartieLoto(ActionEvent Event) {
		Stage stage2 = (Stage) this.loto.getScene().getWindow();
		//SelecGrille s= new SelecGrille();
		//s.start(stage2);

	}

	/**Proc�dure permettant de lancer une nouvelle partie de bataille navale
	 * � l'aide des infomations sur les joueurs pr�c�dement rentr�es
	 */
	public void buttonBataille() {
		Window w = loto.getScene().getWindow();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Jeu encore en developpement");
		alert.initOwner(w);
		alert.show();

	}
	/**Proc�dure permettant de lancer une nouvelle partie de poker
	 * � l'aide des infomations sur les joueurs pr�c�dement rentr�es
	 */

	public void buttonPoker() {
		PartiePoker pp = new PartiePoker(5);
		Stage temp = (Stage) this.loto.getScene().getWindow();
		if (Menu.nomsJoueurs.size() >= 1)
			pp.getListeJoueurs().add(new JoueurPoker(Menu.nomsJoueurs.get(0)));
		if (Menu.nomsJoueurs.size() >= 2)
			pp.getListeJoueurs().add(new JoueurPoker(Menu.nomsJoueurs.get(1)));
		if (Menu.nomsJoueurs.size() >= 3)
			pp.getListeJoueurs().add(new JoueurPoker(Menu.nomsJoueurs.get(2)));
		if (Menu.nomsJoueurs.size() >= 4)
			pp.getListeJoueurs().add(new JoueurPoker(Menu.nomsJoueurs.get(3))); // creation d'autant de joueur que possible
		PartiePoker.joueurCourant = new JoueurPoker(pp.getListeJoueurs().get(0)); //le joueur courant au début d'une partie est le premier joueur
		PartiePoker.premierJoueur = pp.getListeJoueurs().get(0); //le premier joueur est celui tout à gauche soit le joueur 1
		AffichagePoker ap = new AffichagePoker();
		try {
			AffichagePoker.setPp(pp);
			System.out.print(pp.getListeJoueurs().toString());
			ap.start(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Proc�dure permettant de lancer une nouvelle partie de sudoku
	 * � l'aide des infomations sur le joueur pr�c�dement rentr�es
	 */
	public void buttonSudoku() {
		Stage temp = (Stage) this.loto.getScene().getWindow();
		JeuSudoku.nomsJoueurs = nomsJoueurs;
		MenuSudoku m = new MenuSudoku();
		try {
			m.start(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/////////////////////////////////////////

	@FXML
	public void handleEnterKey(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
			try {
				handleNbJoueur();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	/**Proc�dure permettant de rentrer le nombre de joueur
	 * on effectue un cast du string rentr� afin de pouvoir manipuler ce nombre
	 */
	@FXML
	public void handleNbJoueur() throws IOException {
		Window w = entrerNbJoueur.getScene().getWindow();
		boolean stop = false;
		try {
	        Double d = Double.parseDouble(nbJoueur.getText());
	        if(d >= 5.0) throw new NumberFormatException();
	    } catch (NumberFormatException nfe) {
	        stop = true;
	    }
		if ((nbJoueur.getText().isEmpty()) || stop ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Entrez un nombre de joueurs ! Pas plus de 4 attention");
			alert.initOwner(w);
			alert.show();
			return;
		} else {
			Menu.nbJoueurInt = stringtoint2(nbJoueur.getText());
			Menu.nbJoueurInt2 = stringtoint2(nbJoueur.getText());
			// creation d'un stage car l'attribut se perd dans l'espace
			Stage stage2 = (Stage) nbJoueur.getScene().getWindow();
			this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/nomsJoueurs.fxml"));
			stage2.setScene(new Scene(this.root));

		}
		System.out.println(Menu.nbJoueurInt);
	}

		
	@FXML
	public void handleLancerCredits(ActionEvent Event) throws IOException {
		Stage temp = (Stage) this.credit.getScene().getWindow();
		this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/credit.fxml"));
		temp.setScene(new Scene(this.root));
	}
	/**Proc�dure permettant d'afficher le nom du joueur venant d'�tre rentr�
	 */
	public void creationLabel(String s) {
		this.labelNomJoueur.setText(s);
		System.out.println(this.labelNomJoueur.getText());
		this.labelNomJoueur.setText(s);
	}
	
	public void handleEnterKeyNoms(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
			try {
				handleNomsJoueurs();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**Proc�dure permettant de rentrer le nom des joueurs
	 */
	@FXML
	public void handleNomsJoueurs() throws IOException, InterruptedException {
		Window w = entrerNomJoueur.getScene().getWindow();
		if (nomJoueur.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Entrer un nom de joueur !");
			alert.initOwner(w);
			alert.show();
			return;
		} else {
			if(nomJoueur.getText().length()>11) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Le pseudo doit faire moins de 11 caract�re !");
				alert.initOwner(w);
				alert.show();
				nomJoueur.setText("");
				return;
				
			}
			if (Menu.nbJoueurInt > 0) {
				Joueur j = new Joueur(nomJoueur.getText());
				Menu.nomsJoueurs.add(j);
				creationLabel("Bonjour, " + nomJoueur.getText() + " vous etes le joueur " + this.i++ + " !");
				Menu.nbJoueurInt--;
				System.out.println("Nombre de joueurs restants:" + Menu.nbJoueurInt);

			}
		}
		if (Menu.nbJoueurInt == 0) {
			affichageJeu();
		}
		nomJoueur.setText("");

	}
	
	/**Proc�dure permettant d'afficher les jeux disponibles en fonction du nombre de joueur,
	 * exemple on ne peut pas jouer au sudoku � plusieurs
	 */

	public void affichageJeu() {
		try {
			Stage stage2 = (Stage) nomJoueur.getScene().getWindow();
			switch (Menu.nbJoueurInt2) {
			case 1:

				this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/choixJeu1.fxml"));
				stage2.setScene(new Scene(this.root));

				break;
			case 2:

				this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/choixJeu2.fxml"));
				stage2.setScene(new Scene(this.root));

				break;
			case 3:

				this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/choixJeu3-4.fxml"));
				stage2.setScene(new Scene(this.root));

				break;
			case 4:
				this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/choixJeu3-4.fxml"));
				stage2.setScene(new Scene(this.root));

				break;
			case 0:
				System.out.println(Menu.nbJoueurInt);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void handleLancerMenu(ActionEvent Event ) throws IOException {
		Stage temp = (Stage) this.jouer.getScene().getWindow();
		this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/styleMenu.fxml"));
		temp.setScene(new Scene(this.root));
	}
	@FXML
	Button menuPrincipal;

	public void handleRetourMenu() {
	        Stage stage = (Stage) menuPrincipal.getScene().getWindow();
	        Menu m = new Menu();
	        try {
	            m.start(stage);
	        } catch (Exception a) {
	            a.printStackTrace();
	        }
	    }

		

	public void start(Stage stage) throws IOException {
		Popups.resetTimer();
		Font.loadFont(getClass().getResourceAsStream("../resources/fonts/ka1.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Arcadepix Plus.ttf"), 14);
		this.stage = stage;
		this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/menu.fxml"));
		this.scene = new Scene(root);
		this.stage.setTitle("Menu");
		this.stage.setScene(this.scene);
		this.stage.setResizable(false);
		this.stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
