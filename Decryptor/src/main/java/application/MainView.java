package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
        System.out.println(event);
        System.out.println(keywordTextField.getText());
        messageTextArea.setText("Test");
        resultTextArea.setText("Test2");
        copyToClipboardLabel.setVisible(false);
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
