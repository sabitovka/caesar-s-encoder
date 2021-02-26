package application;

import javafx.application.Application;
import javafx.stage.Stage;
import service.Encryptor;

import java.util.Scanner;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
        /*Scanner scanner = new Scanner(System.in);

        System.out.println("Программа для шифрования кода Цезаря.");
        System.out.print("Введите ключ (0-31): ");
        int key = scanner.nextInt();
        System.out.print("Введите ключевое слово: ");
        scanner.nextLine();
        String keyword = scanner.nextLine();

        Encryptor encryptor = new Encryptor(key, keyword);

        System.out.println("\nВведите сообщение");
        String message = scanner.nextLine();
        System.out.println("Зашифрованное сообщение: " + encryptor.encryptMessage(message));*/
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainView.newInstance(stage).show();
    }
}
