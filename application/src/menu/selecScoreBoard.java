package menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loto.Affichage;

import java.io.IOException;

public class selecScoreBoard extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Loto");
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 800);


        //affichage de scoreboard
        Label l=new Label("SCOREBOARD");
        root.getChildren().add(l);
        l.setLayoutY(90);
        l.setLayoutX(150);
        l.setStyle("-fx-font-size:80");

        //bouton Loto

        Button btn = new Button();
        root.getChildren().add(btn);
        btn.setText("LOTO !");
        btn.setLayoutY(600);
        btn.setLayoutX(100);
        btn.setStyle("-fx-font-size:40");
        btn.setOnAction(event -> {

            scoreBoardAffichage a = new scoreBoardAffichage();
            try {
                a.start(primaryStage,1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        //bouton bataille navale

        Button btn1 = new Button();
        root.getChildren().add(btn1);
        btn1.setText("BATAILLE NAVALE !");
        btn1.setLayoutY(400);
        btn1.setLayoutX(50);
        btn1.setStyle("-fx-font-size:40");

        btn1.setOnAction(event -> {

            scoreBoardAffichage a = new scoreBoardAffichage();
            try {
                a.start(primaryStage,2);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        //bouton sudoku

        Button btn2 = new Button();
        root.getChildren().add(btn2);
        btn2.setText("SUDOKU !");
        btn2.setLayoutY(400);
        btn2.setLayoutX(550);
        btn2.setStyle("-fx-font-size:40");

        btn2.setOnAction(event -> {

            scoreBoardAffichage a = new scoreBoardAffichage();
            try {
                a.start(primaryStage,3);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        //bouton poker

        Button btn3 = new Button();
        root.getChildren().add(btn3);
        btn3.setText("POKER !");
        btn3.setLayoutY(600);
        btn3.setLayoutX(500);
        btn3.setStyle("-fx-font-size:40");

        btn3.setOnAction(event -> {

            scoreBoardAffichage a = new scoreBoardAffichage();
            try {
                a.start(primaryStage,4);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        scene.getStylesheets().add(getClass().getResource("../resources/FXML/styleScoreboard.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);

        primaryStage.show();

    }
}