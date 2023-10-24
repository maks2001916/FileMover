module com.example.projectthru {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires darculafx;
    requires org.kordamp.bootstrapfx.core;


    opens com.example.projectthru to javafx.fxml;
    exports com.example.projectthru;
}