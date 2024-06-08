module com.example.projetoacademia {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.projetoacademia.controllers to javafx.fxml;
    exports com.example.projetoacademia;
    exports com.example.projetoacademia.controllers;
}