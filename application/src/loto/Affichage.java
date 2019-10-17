package loto;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Affichage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        int premiersNombres[][] = { {1,2,4,6,5,3,11,4,13},{87,44,76,32,16,13,28,45,19},{47,54,21,31,17,78,76,15,45} };
        int i=0;
        primaryStage.setTitle("Loto :p");
        Pane root = new Pane();

        ArrayList r=new ArrayList<Integer>();
        Button btn = new Button();
        Label text = new Label();
        text.setLayoutX(550);
        text.setLayoutY(160);

        btn.setText("Jouer");
        btn.setLayoutX(540);
        btn.setLayoutY(80);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Random re = new Random();
                int low = 10;
                int high = 100;
                int result = re.nextInt(high-low) + low;

                text.setText(String.valueOf(result));
                r.add(result);
                afficherTableau(root, premiersNombres,r);

            }
        });
        root.getChildren().add(btn);
        root.getChildren().add(text);

        afficherTableau(root, premiersNombres,r);
        primaryStage.setScene(new Scene(root, 650, 280));
        primaryStage.show();
    }

    public void afficherTableau(Pane root, int[][]tableau, List<Integer> tokenDejaTire){
        int i=0;



        for(int[] x:tableau){
            int j=0;
            for(int y:x){
                Label text = new Label();
                text.setText(String.valueOf(y));
                text.setLayoutX(50+j*45);
                text.setLayoutY(50+i*50);
                root.getChildren().add(text);

                Rectangle rectangle = new Rectangle(35+j*45,35+i*50,45, 50);
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                root.getChildren().add(rectangle);


                if(tokenDejaTire.contains(y)){
                    Circle c=new Circle(55+j*45,58+i*50,15);
                    c.setFill(Color.TRANSPARENT);
                    c.setStroke(Color.RED);

                    root.getChildren().add(c);
                }
                j++;

            }
            i++;

        }

    }

    }

