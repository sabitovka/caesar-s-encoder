package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {

    private Stage stage;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    public static Stage newInstance(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainView.class.getResource("/view/fxml/main.fxml"));
        try {
            loader.setController(new MainView());
            Parent root = loader.load();
            ((MainView) loader.getController()).setStage(primaryStage);
            primaryStage.setTitle("Контрольная работа по ТЗИ. Вып. ст. гр. ИСзск-19, Сабитов К.А.");
            primaryStage.setScene(new Scene(root, 584, 450));
            return primaryStage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return primaryStage;
    }

}
