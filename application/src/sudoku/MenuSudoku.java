package sudoku;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import commun.Grille;
import commun.Joueur;
import commun.Popups;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sudoku.JeuSudoku;

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
	
	public void buttonOPTIONPressed() {
		Popups.options((Stage) this.buttonDIFFICILE.getScene().getWindow(), "Option", "Option de Jeu");
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
