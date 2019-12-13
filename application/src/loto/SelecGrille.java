package loto;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class SelecGrille extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Loto");
        Pane root=new Pane();
        Scene scene = new Scene(root, 400,400);
        TextField t=new TextField();
        Button btn=new Button();
        Label l =new Label();
         root.getChildren().add(t);
        root.getChildren().add(btn);
        root.getChildren().add(l);

        l.setLayoutX(50);
        l.setLayoutY(100);
        l.setText("CHOISIR LE NOMBRE DE GRILLE");
        btn.setText("JOUER!");
        t.setLayoutY(200);
        btn.setLayoutY(200);
        t.setLayoutX(50);
        btn.setLayoutX(280);
        btn.setOnAction(event -> {

            Affichage a = new Affichage();
            a.start(primaryStage,Integer.parseInt(t.getText()));

        });

        scene.getStylesheets().add(getClass().getResource("../resources/FXML/style2.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
