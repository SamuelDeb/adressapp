package fr.sd.addressapp.pdf;


import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.List;
import fr.sd.addressapp.MainApp;
import fr.sd.addressapp.model.Personne;
import fr.sd.addressapp.service.RepertoireBean;
import javafx.collections.ObservableList;


import java.io.FileNotFoundException;


public class PdfController {
    private MainApp mainApp;
    private RepertoireBean repertoire;


    public void creerPDF(ObservableList<Personne> listePersonnes) {


        List contacts = new List();


        for (int i = 0; i < listePersonnes.size(); i++) {
            contacts.add(String.valueOf(listePersonnes.get(i).getPrenom() + " + " + listePersonnes.get(i).getNom()));
        }

        String chemin = "D:\\testpdf\\testPdf.pdf";

        try {
            PdfWriter pdfWriter = new PdfWriter(chemin);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument, PageSize.A4);

            document.add(contacts);

            document.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}

