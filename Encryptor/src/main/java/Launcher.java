public class Launcher {

    public static void main(String[] args) {
        Encryptor encryptor = new Encryptor(3, "ШИФРМИФР");
        System.out.println(encryptor.encryptMessage("ПРИВЕТ Как дела"));
    }

}
