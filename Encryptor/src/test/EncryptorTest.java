import org.junit.*;
import org.junit.rules.ExpectedException;

public class EncryptorTest {

    private Encryptor encryptor;

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
        System.out.println("Создание объекта Encryptor с key=0");
        Encryptor encryptor = new Encryptor(0, "ШИФРОВАНИЕ");
        String message = "Привет как дела";
        System.out.println("Начальное сообщение " + message);
        message = encryptor.encryptMessage(message);
        System.out.println("Зашированное сообщение " + message);
        Assert.assertEquals("КЛЕФВП ГШГ ОВДШ", message);
    }

    @Test
    public void whenCreateEncryptorWithZeroKeywordThenCreateEncryptTemplateLikeAlphabet() {
        System.out.println("Создание объекта Encryptor с keyword=''");
        Encryptor encryptor = new Encryptor(0, "");
        String message = "Привет как дела";
        System.out.println("Начальное сообщение " + message);
        message = encryptor.encryptMessage(message);
        System.out.println("Зашированное сообщение " + message);
        Assert.assertEquals("ПРИВЕТ КАК ДЕЛА", message);
    }

    @Test
    public void whenCreateEncryptorWithNegativeKeyThenThrowKeyOutOfBoundException() {
        System.out.println("Создание объекта Encryptor с key=-1. Ожидается исключение");
        /*thrown.expect(???);*/
        /*thrown.expectMessage("Key must be in 0..32");*/
        Encryptor encryptor = new Encryptor(-1, "ШИФРОВАНИЕ");
        Assert.fail();
    }

    @Test
    public void whenCreateEncryptorWithKey33ThenThrowKeyOutOfBoundException() {
        System.out.println("Создание объекта Encryptor с key=33. Ожидается исключение");
        /*thrown.expect(???);*/
        /*thrown.expectMessage("Key must be in 0..32");*/
        Encryptor encryptor = new Encryptor(33, "ШИФРОВАНИЕ");
        Assert.fail();
    }

    @Test
    public void whenCreateEncryptorWithKey32ThenCreateRightAlphabet() {
        System.out.println("Создание объекта Encryptor с key=32. Ожидается исключение");
        /*thrown.expect(???);*/
        /*thrown.expectMessage("Key must be in 0..32");*/
        Encryptor encryptor = new Encryptor(22, "ШИФРОВАНИЕ");
        Assert.fail();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Тесты завершены.");
    }

}