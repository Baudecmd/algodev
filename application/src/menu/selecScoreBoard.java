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
        Scene scene = new Scene(root, 400, 400);


        //bouton Loto

        Button btn = new Button();
        root.getChildren().add(btn);
        btn.setText("Loto!");
        btn.setLayoutY(200);
        btn.setLayoutX(20);
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
        btn1.setText("BATAILLENAV!");
        btn1.setLayoutY(200);
        btn1.setLayoutX(80);
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
        btn2.setText("SUDOKU!");
        btn2.setLayoutY(200);
        btn2.setLayoutX(200);
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
        btn3.setText("POKER!");
        btn3.setLayoutY(200);
        btn3.setLayoutX(290);
        btn3.setOnAction(event -> {

            scoreBoardAffichage a = new scoreBoardAffichage();
            try {
                a.start(primaryStage,4);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

//        scene.getStylesheets().add(getClass().getResource("../resources/FXML/style2.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
