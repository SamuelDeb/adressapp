package fr.sd.addressapp.model;

import javafx.beans.property.*;


import java.time.LocalDate;

public class Personne {
    private final StringProperty prenom;
    private final StringProperty nom;
    private final StringProperty rue;
    private final IntegerProperty codePostale;
    private final StringProperty ville;
    private final ObjectProperty<LocalDate> anniversaire;


    public Personne() {
        this(null,null);
    }

    public Personne(String prenom, String nom) {
        this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.rue = new SimpleStringProperty("47 rue Marceau");
        this.codePostale = new SimpleIntegerProperty(59200);
        this.ville = new SimpleStringProperty("Tourcoing");
        this.anniversaire = new SimpleObjectProperty<>(LocalDate.of(1988, 1, 16));

            }


    public String getPrenom() {
        return prenom.get();
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getRue() {
        return rue.get();
    }

    public StringProperty rueProperty() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue.set(rue);
    }

    public int getCodePostale() {
        return codePostale.get();
    }

    public IntegerProperty codePostaleProperty() {
        return codePostale;
    }

    public void setCodePostale(int codePostale) {
        this.codePostale.set(codePostale);
    }

    public String getVille() {
        return ville.get();
    }

    public StringProperty villeProperty() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }

    public LocalDate getAnniversaire() {
        return anniversaire.get();
    }

    public ObjectProperty<LocalDate> anniversaireProperty() {
        return anniversaire;
    }

    public void setAnniversaire(LocalDate anniversaire) {
        this.anniversaire.set(anniversaire);
    }
}

