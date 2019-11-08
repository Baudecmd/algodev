package bataille;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AffichageBN extends Application {
    private Bataille bataille;

    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @FXML
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
      //  this.bataille = new Bataille(new JoueurBataille2("jean"), new JoueurBataille2("jacques"));
        this.stage = primaryStage;
        this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/ChoixTire.fxml"));
        this.scene = new Scene(root);
        this.stage.setTitle("Bataille navale !");
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void tirer(MouseEvent event) {
        Button button = (Button)event.getSource();
        int x;
        int y;

        try {
            x = GridPane.getColumnIndex(button) + 1;
        }
        catch (NullPointerException e) {x = 0;}

        try {
            y = GridPane.getRowIndex(button) + 1;
        }
        catch (NullPointerException e) {y = 0;}

        button.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("vous avez cliqué sur la case" + x + "-" + y);
        alert.show();
        event.consume();
    }

    @FXML
    ChoiceBox<String> xTorpilleur;

    @FXML
    ChoiceBox<String> yTorpilleur;

    @FXML
    ChoiceBox<String> dirTorpilleur;

    public void placerTorpilleur(MouseEvent event){
        ArrayList<Case> choix = new ArrayList<>(2);
        String alph = "ABCDEFGHIJ";
        Case origin = new Case(parseInt(yTorpilleur.getValue()), alph.indexOf(xTorpilleur.getValue().charAt(0)));
        //choix.set(0, origin);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("case "+ origin.toString());
        a.show();
        //switch (dirTorpilleur.getValue()){
          //  case "Haut":

        //}
    }
}