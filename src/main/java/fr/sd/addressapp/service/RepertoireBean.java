package fr.sd.addressapp.service;

import fr.sd.addressapp.dao.ContactDAO;
import fr.sd.addressapp.dao.FichierTexte;
import fr.sd.addressapp.model.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

public class RepertoireBean {
    private ObservableList<Personne> allContact;
    private File file;
    private ContactDAO contactDAO;

    public RepertoireBean(File file) {
        this.file = file;
        contactDAO = new ContactDAO(file);
        allContact = FXCollections.observableArrayList(contactDAO.lire());


    }


    public void sauver() {
        contactDAO = new ContactDAO(file);
        ArrayList<Personne> listePersonnes = new ArrayList<>(allContact);
        contactDAO.sauver(listePersonnes);
    }

    public ObservableList<Personne> getPersonnes() {
        return allContact;
    }




}
