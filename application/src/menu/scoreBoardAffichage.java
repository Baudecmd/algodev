package menu;

import commun.Joueur;
import commun.Partie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class scoreBoardAffichage extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage,int jeu) throws IOException {
        jeu=1;
        Pane root = new Pane();
        ArrayList<Joueur> lJ;
        String path;

        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("../resources/FXML/loto.css").toExternalForm());
       stage.setScene(scene);
        stage.show();

        switch(jeu){
            case 1:path="scoreboardLoto.ser";break;
            case 2:path="scoreboardBataille.ser";break;
            case 3:path="scoreboardSudoku.ser";break;
            case 4:path="scoreboardPoker.ser";break;
            default:
                throw new IllegalStateException("Unexpected value: " + jeu);
        }

        lJ=Partie.recupererScore(path);
        System.out.println(lJ);




    }

    @Override
    public void start(Stage stage) throws Exception {
        int jeu=1;
        Pane root = new Pane();
        ArrayList<Joueur> lJ;
        String path;


        switch(jeu){
            case 1:path="scoreboardLoto.ser";break;
            case 2:path="scoreboardBataille.ser";break;
            case 3:path="scoreboardSudoku.ser";break;
            case 4:path="scoreboardPoker.ser";break;
            default:
                throw new IllegalStateException("Unexpected value: " + jeu);
        }

        lJ=Partie.recupererScore(path);
        System.out.println(lJ);

        for(int i =0;i<10;i++){
            Label scoreJ =new Label();
            scoreJ.setLayoutX(250);
            scoreJ.setLayoutY(150+i*60);
            scoreJ.setText(lJ.get(i).getNom());
            root.getChildren().add(scoreJ);

            scoreJ =new Label();
            scoreJ.setLayoutX(500);
            scoreJ.setLayoutY(155+i*60);
            scoreJ.setText(String.valueOf(lJ.get(i).getScore()));
            root.getChildren().add(scoreJ);
        }

        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("../resources/FXML/styleScoreboard.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
}
