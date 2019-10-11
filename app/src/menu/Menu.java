package menu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Menu extends Application {

	public static Button ButtonLoto() {

		Button Bouton = new Button("Lancer une partie de Loto");
		Bouton.setOnAction(actionEvent -> System.out.println("�a fonctionne on dirait")); //lancement de la partie si clique
		Bouton.setLayoutX(250);
		Bouton.setLayoutX(200);
		return Bouton;

	}

	public static Button ButtonBataille() {

		Button Bouton = new Button("Lancer une partie de bataille navale");
		Bouton.setOnAction(actionEvent -> System.out.println("Jeu non disponible")); //lancement de la partie si clique
		Bouton.setLayoutX(250);
		Bouton.setLayoutX(200);
		return Bouton;

	}

	public static Button ButtonPoker() {

		Button Bouton = new Button("Lancer une partie de poker");
		Bouton.setOnAction(actionEvent -> System.out.println("Jeu non disponible")); //lancement de la partie si clique
		Bouton.setLayoutX(250);
		Bouton.setLayoutX(200);
		return Bouton;

	}

	public static Button ButtonSudoku() {

		Button Bouton = new Button("Lancer une partie de Sudoku");
		Bouton.setOnAction(actionEvent -> System.out.println("Jeu non disponible")); //lancement de la partie si clique
		Bouton.setLayoutX(250);
		Bouton.setLayoutX(200);
		return Bouton;

	}

	public static Text zoneDeTexte(String txt, int x, int y, int taille){
		Text t = new Text(x, y, "txt");
		t.setFont(new Font(taille));
	return t;

	}


	public static int nbJoueur(TextField txtJoueur){
			if(nbJoueur.onAction){
		int nbJoueur = (int) txtJoueur.getText;
}
return nbJoueur;
}

	public static nomJoueur(int nbJoueur){
		String tabNomJoueur[];
		for(int i=0;i<nbJoueur;i++){
			TextField txtJoueur = new TextField("Joueur" + i );
			textField.setMinWidth(120);
			if(txtJoueur.onAction){
				tabNomJoueur[i] = txtJoueur.getText();

			}
		}
	}



public void start(Stage stage) {

	TextField tf = new TextField("0");
  tf.setMinWidth(120);
	FlowPane root = new FlowPane();
	root.setHgap(10);
	root.setVgap(20);
	root.setPadding(new Insets(15, 15, 15, 15));
  root.getChildren().add(zoneDeTexte("Menu Principal !"), 100, 100, 30);
  root.getChildren().add(zoneDeTexte("A combien de Joueur voulez vous jouer ?"), 100, 60, 10);
  root.getChildren().add(tf);



	root.getChildren().add(ButtonLoto());
	root.getChildren().add(ButtonBataille());
	root.getChildren().add(ButtonPoker());
	root.getChildren().add(ButtonSudoku());

	Scene scene = new Scene(root, 1000, 500);
	stage.setTitle("Menu");
	stage.setScene(scene);
	stage.show();

}
public static void main(String[] args) {
	launch(args);
}
}
