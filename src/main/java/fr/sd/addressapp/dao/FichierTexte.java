package fr.sd.addressapp.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FichierTexte {

    private File file;
    private  List<String> lignes;


    public FichierTexte(File file) {
        this.file = file;
        if (!file.exists()) {
            createFile(file);
        }
    }

    private static void createFile(File file){
        try {
            if (file.createNewFile()) {
                System.out.println(("Fichier créé"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode qui permet de lire un fichier et d'extraire les données puis de les mettres dans une ArrayList ligne par ligne
     *
     * @return une ArrayList de String des lignes
     */
    public List<String> lire() {
        lignes = new ArrayList<String>();

        try{
            FileReader fileReader = new FileReader(file); // Un flux qui se connecte au fichier texte
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String ligne;
            while ((ligne= bufferedReader.readLine()) != null){
                lignes.add(ligne);
                System.out.println(ligne); // Affiche le contenu du fichier à l'écran, une ligne à la fois

            }
            bufferedReader.close();
        } catch (IOException e) {
        e.printStackTrace();            //nothing to do
        }

        return lignes;
    }


    /**
     * Ecrit dans un fichier .txt les lignes une par une.
     *
     * @param lignes
     */
    public void ecrire(List<String> lignes) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String ligne : lignes) {
                bufferedWriter.append(ligne);
                bufferedWriter.newLine();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
