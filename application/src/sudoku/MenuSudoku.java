package sudoku;

import javafx.application.Application;
import commun.Popups;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuSudoku extends Application {
	@FXML
	Button buttonFACILE;
	@FXML
	Button buttonMOYEN;
	@FXML
	Button buttonDIFFICILE;
	@FXML
	Button buttonOPTION;
	@FXML
	Parent root;

	
	public MenuSudoku() {
		
	}
	/**
	 * Gestion du bouton facile
	 */
	public void buttonFACILEPressed() {
		Popups.resetTimer();
		Stage stage2 = (Stage) this.buttonDIFFICILE.getScene().getWindow();
		JeuSudoku m = new JeuSudoku();
		JeuSudoku.game = new Sudoku(JeuSudoku.nomsJoueurs,1);
		JeuSudoku.setInitial(Sudoku.getGrille());
		JeuSudoku.setGrilleComplete(Sudoku.getGrilleSolution());
		try {
			m.start(stage2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gestion du bouton Moyen
	 */
	public void buttonMOYENPressed() {
		Popups.resetTimer();
		Stage stage2 = (Stage) this.buttonDIFFICILE.getScene().getWindow();
		JeuSudoku m = new JeuSudoku();
		JeuSudoku.game = new Sudoku(JeuSudoku.nomsJoueurs,2);
		JeuSudoku.setInitial(Sudoku.getGrille());
		JeuSudoku.setGrilleComplete(Sudoku.getGrilleSolution());
		try {
			m.start(stage2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gestion du bouton difficile
	 */
	public void buttonDIFFICILEPressed() {
		Popups.resetTimer();
		Stage stage2 = (Stage) this.buttonDIFFICILE.getScene().getWindow();
		JeuSudoku m = new JeuSudoku();
		JeuSudoku.game = new Sudoku(JeuSudoku.nomsJoueurs,3);
		JeuSudoku.setInitial(Sudoku.getGrille());
		JeuSudoku.setGrilleComplete(Sudoku.getGrilleSolution());
		try {
			m.start(stage2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gestion du bouton Option
	 */
	public void buttonOPTIONPressed() {
		Popups.options((Stage) this.buttonDIFFICILE.getScene().getWindow(), "Option", "Options de Jeu");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/choixdifficultes.fxml"));
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
