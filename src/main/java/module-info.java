module fr.sd.addressapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires kernel;
    requires layout;
    requires io;

    opens fr.sd.addressapp to javafx.fxml;

    exports fr.sd.addressapp;
    exports fr.sd.addressapp.view;
    exports fr.sd.addressapp.pdf;
    exports fr.sd.addressapp.dao;

    opens fr.sd.addressapp.view to javafx.fxml;
    opens fr.sd.addressapp.pdf to javafx.fxml;
}