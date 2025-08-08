package fr.sd.addressapp.view;

import fr.sd.addressapp.MainApp;
import fr.sd.addressapp.dao.ContactDAO;
import fr.sd.addressapp.pdf.PdfController;
import fr.sd.addressapp.service.RepertoireBean;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;

public class MenuController {


private MainApp mainApp;
public void setMainApp(MainApp mainApp){
    this.mainApp = mainApp;
}

@FXML
    private void handleNew(){

        mainApp.getDonneesPersonne().clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt")
        );
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (!file.exists()) {
            mainApp.setRepertoire(new RepertoireBean(file));
        }

    }


@FXML
    private void handleOpen(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("TXT", "*.txt")
    );
    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
    if(file != null){
        mainApp.setRepertoire(new RepertoireBean(file));
    }
}
@FXML
    private void handleSave(){
        mainApp.getRepertoire().sauver();
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".txt")) {
                file = new File(file.getPath() + ".txt");
            }
            ContactDAO contacts = new ContactDAO(file);
            contacts.sauver(mainApp.getRepertoire().getPersonnes());
        }
    }
    @FXML
    private void handleExit() {
        System.exit(0);
    }



}
