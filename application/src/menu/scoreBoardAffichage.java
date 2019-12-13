package menu;

import commun.Joueur;
import commun.Partie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import loto.Loto;

import java.io.IOException;
import java.util.ArrayList;

import bataille.Bataille;

public class scoreBoardAffichage extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage, int jeu) throws IOException {
        String pathImg = "";
        String jeux = "";

        Pane root = new Pane();
        ArrayList<Joueur> lJ;
        String path;

        switch (jeu) {
            case 1:
                path = Loto.fileName;
                pathImg = "resources/image/piece.png";
                jeux = "Loto";
                break;
            case 2:
                path = "resources/scoreboardBataille.ser";
                pathImg = "resources/image/bateau.png";
                jeux = "Bataille";

                break;
            case 3:
                path = "scoreboardSudoku.ser";
                pathImg = "resources/image/grille.png";
                jeux = "Sudoku";

                break;
            case 4:
                path = "scoreboardPoker.ser";
                pathImg = "resources/image/mise.png";
                jeux = "Poker";

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + jeu);
        }


        //affichage du nom du jeux
        Label nomJeux = new Label(jeux);
        nomJeux.setLayoutX(350);
        nomJeux.setLayoutY(40);
        nomJeux.setAlignment(Pos.CENTER);
        root.getChildren().add(nomJeux);
        Partie.initialiser(path);
        lJ = Partie.recupererScore(path);

        //entourage du tableau
        Rectangle r1 = new Rectangle(185, 140, 400, 600);
        r1.setStroke(Color.BLACK);
        r1.setFill(Color.TRANSPARENT);
        root.getChildren().add(r1);

        for (int i = 0; i < 10; i++) {

            //affichage du rectangle en arrière plan
            Rectangle r = new Rectangle(185, 140 + 60 * i, 400, 60);
            r.setOpacity(0.5);
            if (i % 2 == 0) r.setFill(Color.GREY);
            else r.setFill(Color.BEIGE);
            r.setOnMouseEntered(e ->
                    r.setOpacity(0)
            );

            r.setOnMouseExited(e -> r.setOpacity(0.5)
            );
            root.getChildren().add(r);


            //affichage du nom du Joueur
            Label scoreJ = new Label(lJ.get(i).getNom());
            scoreJ.setLayoutX(230);
            scoreJ.setLayoutY(145 + i * 60);
            root.getChildren().add(scoreJ);


            //affichage du score
            scoreJ = new Label(String.valueOf(((int) lJ.get(i).getScore())));
            scoreJ.setLayoutX(480);
            scoreJ.setLayoutY(147 + i * 60);
            root.getChildren().add(scoreJ);


        }

        //affichage des 2 logos
        Image image2 = new Image(pathImg, 100, 100, true, true);
        ImageView imageView = new ImageView(image2);
        imageView.setX(50);
        imageView.setY(40);
        root.getChildren().add(imageView);
        image2 = new Image(pathImg, 100, 100, true, true);
        imageView = new ImageView(image2);
        imageView.setX(650);
        imageView.setY(40);
        root.getChildren().add(imageView);


        //creation de la scène
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("../resources/FXML/styleScoreboard.css").toExternalForm());
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();


    }

    @Override
    public void start(Stage stage) throws Exception {


    }
}
