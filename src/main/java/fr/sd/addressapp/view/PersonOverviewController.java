package fr.sd.addressapp.view;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import fr.sd.addressapp.model.Personne;
import fr.sd.addressapp.MainApp;
import fr.sd.addressapp.pdf.PdfController;
import fr.sd.addressapp.util.DateUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class PersonOverviewController {

    @FXML
    private TableView<Personne> personTable;
    @FXML
    private TableColumn<Personne, String> prenomColonne;
    @FXML
    private TableColumn<Personne, String> nomColonne;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label rueLabel;
    @FXML
    private Label codePostaleLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label anniversaireLabel;
    private PdfController pdfController;

    private MainApp mainApp;
    public PersonOverviewController() {
        this.pdfController = new PdfController();
    }

    /**
     * Initialise la classe controller, elle est automatiquement appelé
     * après le chargement du fichier fxml
     */
    @FXML
    private void initialize() {
        // Initialise le tableau de personnes avec les deux colonnes
        prenomColonne.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        nomColonne.setCellValueFactory(cellData -> cellData.getValue().nomProperty());

        //effacer les details d'une personne
        showPersonDetails(null);

        //Ecouter pour les changements et afficher les details de la personne qui a changé
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

    }

    /**
     * Remplie les labels pour montrer les détails d'une personne
     *
     * @param personne ou null
     */
    private void showPersonDetails(Personne personne) {
        if (personne != null) {
            //Remplie les labels avec les informations de la personne
            prenomLabel.setText(personne.getPrenom());
            nomLabel.setText(personne.getNom());
            rueLabel.setText(personne.getRue());
            codePostaleLabel.setText(Integer.toString(personne.getCodePostale()));
            villeLabel.setText(personne.getVille());
            anniversaireLabel.setText(DateUtil.format(personne.getAnniversaire()));


        } else {
            //Si personne == null on supprime tout les textes
            prenomLabel.setText("");
            nomLabel.setText("");
            rueLabel.setText("");
            codePostaleLabel.setText("");
            villeLabel.setText("");
            anniversaireLabel.setText("");

        }
    }

    /**
     * Methode appelée lorsque que le bouton delete est cliqué
     */
    @FXML
    private void handleDeletePersonne() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);

        } else {
            //si rien n'est selectionner
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de selection");
            alert.setHeaderText("Aucune personne de selectionner");
            alert.setContentText("Veuillez selectionner une personne de la table");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() {
        Personne tempPerson = new Personne();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getRepertoire().getPersonnes().add(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() {
        Personne selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de selection");
            alert.setHeaderText("Pas de personne sélectionner");
            alert.setContentText("Veuillez sélectionner une personne dans la table");

            alert.showAndWait();
        }
    }
    @FXML
    private void handlePDF(){
        ObservableList<Personne> listePersonnes = mainApp.getRepertoire().getPersonnes();

        pdfController.creerPDF(listePersonnes);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // ajouter la liste observable à la table
        personTable.setItems(mainApp.getRepertoire().getPersonnes());
    }


}
