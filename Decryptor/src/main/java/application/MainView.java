package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Decryptor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

public class MainView {

    @FXML
    private TextField keywordTextField;
    @FXML
    private Spinner<Integer> keySpinner;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private Button transformButton;
    @FXML
    private Label copyToClipboardLabel;

    @FXML
    void initialize() {
        keySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 31));
    }

    @FXML
    void transformButton_Action(ActionEvent event) {
        copyToClipboardLabel.setVisible(true);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            copyToClipboardLabel.setVisible(false);
        }).start();

        int key = keySpinner.getValue();
        String keyword = keywordTextField.getText();
        Decryptor encryptor = new Decryptor(key, keyword);
        resultTextArea.setText(encryptor.decryptMessage(messageTextArea.getText()));

        // копируем в буфер обмена
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(resultTextArea.getText()), null);
    }

    public static Stage newInstance(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainView.class.getResource("/view/fxml/main.fxml"));
        try {
            loader.setController(new MainView());
            Parent root = loader.load();
            //((MainView) loader.getController()).setStage(primaryStage);
            primaryStage.setTitle("Контрольная работа по ТЗИ. Вып. ст. гр. ИСзск-19, Сабитов К.А.");
            primaryStage.setScene(new Scene(root, 584, 450));
            return primaryStage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return primaryStage;
    }
}
