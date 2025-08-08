package fr.sd.addressapp;

import fr.sd.addressapp.model.Personne;
import fr.sd.addressapp.service.RepertoireBean;
import fr.sd.addressapp.view.MenuController;
import fr.sd.addressapp.view.PersonEditDialogController;
import fr.sd.addressapp.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private RepertoireBean repertoire;

    /**
     * Les données comme une liste de personne observable
     */
    private ObservableList<Personne> donneesPersonne = FXCollections.observableArrayList();

    /**
     * Constructeur
     */
    public MainApp() {


    }

    /**
     * Retourne les données comme une liste de personnes observable
     *
     * @return
     */
    public ObservableList<Personne> getDonneesPersonne() {
        return donneesPersonne;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.getIcons().add(new Image("file:src/main/resources/fr/sd/addressapp/carnet.png"));
        initRootLayout();
//        showPersonOverview();

    }


    /**
     * Initialisation de la couche racine.
     */
    public void initRootLayout() {
        try {
            //Chargement de la couche racine depuis le fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            MenuController controller = loader.getController();
            controller.setMainApp(this);
            //Montrer ma scene contenant la couche racine
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Montrer l'affichage des personnes dans la couche racine
     */
    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("personOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Met person overview au centre de la couche racine.
            rootLayout.setCenter(personOverview);

            //Donne accès au controller de main app
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne la scene principal.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }

    public boolean showPersonEditDialog(Personne personne) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier personne");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //appel la methode qui permet de modifier les informations de la personnel
            controller.setPersonne(personne);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }


    public void setRepertoire(RepertoireBean repertoire) {
        this.repertoire = repertoire;
        showPersonOverview();
    }

    public RepertoireBean getRepertoire() {
        return repertoire;
    }
}