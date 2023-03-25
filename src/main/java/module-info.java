module com.example.fitappproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.fitappproject to javafx.fxml;
    exports com.example.fitappproject;
}