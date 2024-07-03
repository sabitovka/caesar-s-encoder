module caesars.encryptor {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.datatransfer;
    requires java.desktop;

    opens application to javafx.fxml;

    exports application;
}
