package bataille;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AffichageBN extends Application implements Initializable {
    private static Bataille bataille;

    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @FXML
    private Stage stage;

    @FXML
    private GridPane tab1;

    @FXML
    private GridPane tab2;

    @FXML
    private ImageView[] bateaux;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        this.root = FXMLLoader.load(getClass().getResource("../resources/FXML/ChoixBateau.fxml"));
        this.scene = new Scene(root);
        this.stage.setTitle("Bataille navale !");
        this.stage.setScene(this.scene);
        JoueurBataille j1 = new JoueurBataille("Gabriel");
        JoueurBataille j2 = new JoueurBataille("Romane");
        AffichageBN.bataille = new Bataille(j1,j2);
        this.stage.show();
    }

    public void tirer(MouseEvent event) {
        Button button = (Button)event.getSource();
        GridPane tab = (GridPane) button.getParent();
        GridPane nextTab;
        JoueurBataille joueur;
        int i = GridPane.getColumnIndex(button);
        int j = GridPane.getRowIndex(button);
        boolean touche;

        if(tab.equals(tab1)) {
            nextTab = tab2;
            joueur = bataille.getJ1();
        }
        else {
            nextTab = tab1;
            joueur = bataille.getJ2();
        }
        tab.setDisable(true);
        nextTab.setDisable(false);
        button.setDisable(true);
        touche = joueur.tir(i, j);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        actualise(joueur, i, j, touche);
        if(touche) a.setContentText("Touché !");
        else a.setContentText("Raté !");
        a.show();
        event.consume();
    }


    @FXML
    private GridPane j1;

    @FXML
    private GridPane j2;

    public void actualise(JoueurBataille joueur, int i, int j, boolean touche){
        String path;
        ImageView img;

        System.out.println(i + " " + " " + j);
        if(touche) path="resources/image/touche.png";
        else path="resources/image/rate.png";
        img=new ImageView(path);
        img.setPreserveRatio(true);
        img.setFitHeight(70);
        if(joueur.equals(bataille.getJ1())) j1.add(img,i,j);
        else j2.add(img,i,j);
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
        choix.set(0, origin);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("case "+ origin.toString());
        a.show();
        switch (dirTorpilleur.getValue()){
            case "Haut":

        }
        event.consume();
    }

    public void placer(MouseEvent event){
        ImageView source = (ImageView) event.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        //Put ImageView on dragboard
        ClipboardContent cbContent = new ClipboardContent();
        cbContent.putImage(source.getImage());
        //cbContent.put(DataFormat.)
        db.setContent(cbContent);
        source.setVisible(false);
        event.consume();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView[] bateauxJ1 = new ImageView[5];
        bateauxJ1[0] = new ImageView("resources/image/Torpilleur.png");
        bateauxJ1[0] = new ImageView("resources/image/Torpilleur.png");
        bateauxJ1[0] = new ImageView("resources/image/Destroyeur.png");
        bateauxJ1[0] = new ImageView("resources/image/Porte-avion.png");
        bateauxJ1[0] = new ImageView("resources/image/Cuirasse.png");
    }
}