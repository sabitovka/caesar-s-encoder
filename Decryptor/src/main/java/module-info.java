module caesars.decryptor {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.datatransfer;
    requires java.desktop;

    opens application to javafx.fxml;

    exports application;
}
