import service.exceptions.KeyOutOfBoundException;
import service.exceptions.NonCyrillicKeywordException;
import org.junit.*;
import org.junit.rules.ExpectedException;
import service.Encryptor;

public class EncryptorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void globalSetup() {
        System.out.println("Запуск тестов...");
    }

    @Test
    public void whenSimpleEncryptMessageThenReturnRightMessage() {
        System.out.println("Запуск корректного шифрования {key=5, keyword='ШИФРОВАНИЕ'}");

        Encryptor encryptor = new Encryptor(5, "ШИФРОВАНИЕ");
        String message = "Привет как дела";
        System.out.println("Начальное сообщение " + message);
        message = encryptor.encryptMessage(message);
        System.out.println("Зашированное сообщение " + message);
        Assert.assertEquals("ГДРЭШЗ ВЫВ ЯШАЫ", message);
    }

    @Test
    public void whenSimpleEncryptMessageThenReturnWrongMessage() {
        System.out.println("Запуск некорректного шифрования {key=6, keyword='ШИФРОВАНИЕ'}");
        Encryptor encryptor = new Encryptor(6, "ШИФРОВАНИЕ");
        String message = "Привет как дела";
        System.out.println("Начальное сообщение " + message);
        message = encryptor.encryptMessage(message);
        System.out.println("Зашированное сообщение " + message + " Ожидалось \"ГДРЭШЗ ВЫВ ЯШАЫ\"");
        Assert.assertNotEquals("ГДРЭШЗ ВЫВ ЯШАЫ", message);
    }

    @Test
    public void whenCreateEncryptorWithZeroKeyThenCreateEncryptTemplateWithKeywordAtStart() {
        System.out.println("Создание объекта service.Encryptor с key=0");
        Encryptor encryptor = new Encryptor(0, "ШИФРОВАНИЕ");
        String message = "Привет как дела";
        System.out.println("Начальное сообщение " + message);
        message = encryptor.encryptMessage(message);
        System.out.println("Зашированное сообщение " + message);
        Assert.assertEquals("КЛЕФВП ГШГ ОВДШ", message);
    }

    @Test
    public void whenCreateEncryptorWithZeroKeywordThenCreateEncryptTemplateLikeAlphabet() {
        System.out.println("Создание объекта service.Encryptor с keyword=''");
        Encryptor encryptor = new Encryptor(0, "");
        String message = "Привет как дела";
        System.out.println("Начальное сообщение " + message);
        message = encryptor.encryptMessage(message);
        System.out.println("Зашированное сообщение " + message);
        Assert.assertEquals("ПРИВЕТ КАК ДЕЛА", message);
    }

    @Test
    public void whenCreateEncryptorWithNegativeKeyThenThrowKeyOutOfBoundException() {
        System.out.println("Создание объекта service.Encryptor с key=-1. Ожидается исключение KeyOutOfBoundException");
        thrown.expect(KeyOutOfBoundException.class);
        thrown.expectMessage("Key must be in 0..31");
        Encryptor encryptor = new Encryptor(-1, "ШИФРОВАНИЕ");
    }

    @Test
    public void whenCreateEncryptorWithKey32ThenThrowKeyOutOfBoundException() {
        System.out.println("Создание объекта service.Encryptor с key=32. Ожидается исключение KeyOutOfBoundException");
        thrown.expect(KeyOutOfBoundException.class);
        thrown.expectMessage("Key must be in 0..31");
        Encryptor encryptor = new Encryptor(33, "ШИФРОВАНИЕ");
    }

    @Test
    public void whenCreateEncryptorWithKey31ThenCreateRightAlphabet() {
        System.out.println("Создание объекта service.Encryptor с key=31. Ожидается Создание правильного алфавита");
        Encryptor encryptor = new Encryptor(31, "ШИФРОВАНИЕПРИЗНАК");
        Assert.assertEquals("ЙЛПРАС", encryptor.encryptMessage("Привет"));
    }

    @Test
    public void whenCreateEncryptorWithKeyThatContainsSpacesThenRemoveThey() {
        Encryptor encryptor = new Encryptor(3, "ШИФРОВАНИЕ ПРИЗНАК");
    }

    @Test
    public void whenCreateEncryptorWithNonCyrillicKeywordThenThrowNonCyrillicKeywordException() {
        thrown.expect(NonCyrillicKeywordException.class);
        thrown.expectMessage("The keyword contains non cyrillic chars");
        new Encryptor(3, "Hi");
    }

    @Test
    public void whenEncryptEmptyMessageThenReturnEmptyString() {
        Encryptor encryptor = new Encryptor(31, "ШИФРОВАНИЕ");
        Assert.assertEquals("", encryptor.encryptMessage(""));
    }

    @Test
    public void whenEncryptMsgWithNonCyrillicCharsThenReturnOnlyCyrillicCharsOrEmptyString() {
        Encryptor encryptor = new Encryptor(3, "ШИФРОВАНИЕ");
        String msg1 = "Hello Мир!";
        String msg2 = "Hello, World!";
        Assert.assertEquals("БВЗ", encryptor.encryptMessage(msg1));
        Assert.assertEquals("", encryptor.encryptMessage(msg2));
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Тесты завершены.");
    }

}