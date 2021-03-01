import org.junit.*;
import org.junit.rules.ExpectedException;
import service.Decryptor;

import static org.junit.Assert.*;

public class DecryptorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void globalSetup() {
        System.out.println("Запуск тестов...");
    }

    @Test
    public void whenSimpleDecryptThenReturnRightMessage() {
        Decryptor decryptor = new Decryptor(3, "ШИФРОВАНИЕ");
        assertEquals("ШИФР ЦЕЗАРЯ", decryptor.decryptMessage("УВМЗ СФОЭЗЬ"));
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Тесты завершены.");
    }
}