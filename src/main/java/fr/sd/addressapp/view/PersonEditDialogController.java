package fr.sd.addressapp.view;

import fr.sd.addressapp.model.Personne;
import fr.sd.addressapp.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
    @FXML
    private TextField prenomField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField rueField;
    @FXML
    private TextField villeField;
    @FXML
    private TextField codePostaleField;

    @FXML
    private TextField anniversaireField;

    private Stage dialogStage;
    private Personne personne;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    /**
     * Défini le stage de la boite de dialogue
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Définit la personne à modifier dans la boîte de dialogue
     *
     * @param personne
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;

        prenomField.setText(personne.getPrenom());
        nomField.setText(personne.getNom());
        rueField.setText(personne.getRue());
        villeField.setText(personne.getVille());
        codePostaleField.setText(Integer.toString(personne.getCodePostale()));
        anniversaireField.setText(DateUtil.format(personne.getAnniversaire()));
        anniversaireField.setPromptText("dd.mm.yyyy");

    }

    /**
     * Reroune true si le bouton valider est cliqué, sinon false
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Methode appelée quand le bouton accepter est cliqué.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            personne.setPrenom(prenomField.getText());
            personne.setNom(nomField.getText());
            personne.setRue(rueField.getText());
            personne.setVille(villeField.getText());
            personne.setCodePostale(Integer.parseInt(codePostaleField.getText()));
            personne.setAnniversaire(DateUtil.parse(anniversaireField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Methode appelée quand le bouton annuler est cliqué.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isInputValid() {
        String messageErreur = "";

        if (prenomField.getText() == null || prenomField.getText().length() == 0) {
            messageErreur += "Prénom non valide! \n";
        }
        if (nomField.getText() == null || nomField.getText().length() == 0) {
            messageErreur += "Nom non valide! \n";
        }
        if (rueField.getText() == null || rueField.getText().length() == 0) {
            messageErreur += "Rue non valide! \n";
        }
        if (codePostaleField.getText() == null || codePostaleField.getText().length() == 0) {
            messageErreur += "Code postale non valide! \n";
        } else {
            //Tenter de parse le code postale en int
            try {
                Integer.parseInt(codePostaleField.getText());
            } catch (NumberFormatException e) {
                messageErreur += "Code postale non valide (Doit contenir que des chiffre)!\n";
            }
        }
        if (villeField.getText() == null || villeField.getText().length() == 0) {
            messageErreur += "Ville non valide! \n";
        }
        if (anniversaireField.getText() == null || anniversaireField.getText().length() == 0) {
            messageErreur += "Anniversaire non valide! \n";
        } else {
            if (!DateUtil.isValidDate(anniversaireField.getText())) {
                messageErreur += "Format de date non valide, utilisez le format dd.mm.yyyy ! \n";
            }
        }
        if (messageErreur.length() == 0) {
            return true;
        } else {
            //Afficher le message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(messageErreur);

            alert.showAndWait();
            return false;
        }


    }
}
