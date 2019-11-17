package bataille;

import commun.Joueur;
import commun.Partie;
import commun.Popups;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import menu.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AffichageBN extends Application implements Initializable {
    public static Bataille bataille;

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
    private GridPane j1;

    @FXML
    private GridPane j2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ArrayList<Joueur> joueurs = Menu.nomsJoueurs;
        stage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("../resources/FXML/ChoixTire.fxml"));
        scene = new Scene(root);
        stage.setTitle("Bataille navale !");
        stage.setScene(this.scene);
        stage.setResizable(false);
        stage.show();
        bataille = new Bataille(new JoueurBataille(joueurs.get(0)), new JoueurBataille(joueurs.get(1)));
        partie();
    }

    private void partie() {
        Partie.initialiser(bataille.getFileName());
        stage.setHeight(800);
        stage.setWidth(800);
    }



    private void partieFinie() {
        Joueur gagnant = bataille.retournerGagnant();
        Partie.ajouterVictoire(bataille.getFileName(), gagnant);
        ArrayList<Joueur> scores = Partie.recupererScore(bataille.getFileName());
        Popups.nom = gagnant.getNom();
        Popups.score = "Vous avez gagné "+ (int)(scores.get(scores.indexOf(gagnant)).getScore()) + " parties.";
        try {
            Popups.victoire((Stage) this.j1.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tirer(MouseEvent event) {
        Button button = (Button)event.getSource();
        GridPane tab = (GridPane) button.getParent();
        GridPane nextTab;
        JoueurBataille joueur;
        int i = GridPane.getColumnIndex(button);
        int j = GridPane.getRowIndex(button);
        boolean touche;
        Alert a = new Alert(Alert.AlertType.INFORMATION);

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
        actualiseTir(joueur, i, j, touche);
        if(touche) a.setContentText("Touché !");
        else a.setContentText("Raté !");
        if(bataille.partieFinie()) partieFinie();
        a.show();
        event.consume();
    }

    public void actualiseBateau(MouseEvent event){
        ArrayList<Case> cases = new ArrayList<>();
        ArrayList<Bateau> bateaux = new ArrayList<>();
        String path;
        ImageView img;

        cases.add(new Case(2,3));
        bateaux.add(new Bateau("Torpilleur", cases,bataille.getJ1()));
        bataille.getJ1().setListeBateaux(bateaux);
        bataille.getJ2().setListeBateaux(bateaux);
        for (Bateau bateau : bataille.getJ1().getListeBateaux()) {
            path = "resources/image/"+bateau.getName()+".png";
            img = new ImageView(path);
            img.setPreserveRatio(true);
            img.setFitHeight(tab1.getHeight()/10);
            j1.add(img, bateau.getTabCases().get(0).getI(), bateau.getTabCases().get(0).getJ());
        }
        for (Bateau bateau : bataille.getJ2().getListeBateaux()) {
            path = "resources/image/"+bateau.getName()+".png";
            img = new ImageView(path);
            img.setPreserveRatio(true);
            img.setFitHeight(tab1.getHeight()/10);
            j2.add(img, bateau.getTabCases().get(0).getI(), bateau.getTabCases().get(0).getJ());
        }
        event.consume();
    }

    private void actualiseTir(JoueurBataille joueur, int i, int j, boolean touche){
        String path;
        ImageView img;

        if(touche) path="resources/image/touche.png";
        else path="resources/image/rate.png";
        img=new ImageView(path);
        img.setPreserveRatio(true);
        img.setFitHeight(tab1.getHeight()/10);
        if(joueur.equals(bataille.getJ1())) j1.add(img,i,j);
        else j2.add(img,i,j);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}