package fr.sd.addressapp.dao;

import fr.sd.addressapp.model.Personne;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactDAO {
    private FichierTexte fichierTexte;


    public ContactDAO(File file) {
        this.fichierTexte = new FichierTexte(file);
    }


    public List<Personne> lire(){

        List<String> lignes = fichierTexte.lire();
        List<Personne> contacts = new ArrayList<>();

        for (String ligne : lignes){
            contacts.add(ligneToPerson(ligne));
        }
        return contacts;
    }
    private String personToLigne(Personne person) {
        String separator = "|";
        StringBuilder sb = new StringBuilder("");
        sb.append(person.getPrenom());
        sb.append(separator);
        sb.append(person.getNom());

        return sb.toString();
    }

private Personne ligneToPerson(String ligne) {
    String[] datas = ligne.split("\\|");
    Personne person = new Personne(datas[0], datas[1]);

    return person;
}

    public void sauver(List<Personne> personnes){

        List<String> lignes = new ArrayList<>();
        for (Personne person : personnes){
            lignes.add(personToLigne(person));
        }
        fichierTexte.ecrire(lignes);
    }
//    public void sauver(ObservableList<Personne> contacts){
//        List<String> lignes = new ArrayList<>();
//        for (Personne personne : contacts){
//            lignes.add(toLigne(personne));
//        }
//        fichierTexte.ecrire(lignes);
//    }

//    private String toLigne(Personne personne) {
//        String ligne;
//        ligne = personne.getPrenom() + "|" + personne.getNom() + "|" + personne.getRue() + "|" + personne.getCodePostale() + "|" + personne.getVille() + "|" + personne.getAnniversaire();
//        return ligne;
//    }

}
