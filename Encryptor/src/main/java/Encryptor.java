import java.util.ArrayList;
import java.util.List;

public class Encryptor {

    private final List<Character> alphabetTemplate = new ArrayList<>();
    private List<Character> cryptStringTemplate = new ArrayList<>(32);

    private int key;// ключ
    private String keyword; // ключевое слово

    public Encryptor(int key, String keyword) {
        this.key = key;
        this.keyword = keyword.toUpperCase();
        setupCryptTemplate();
    }

    private void setupCryptTemplate() {

        // заполняем массив алфватом (1040 - Код Юникод символа А, 1071 - Код символа Я)
        for (int i = 1040; i <= 1071; i++) {
            alphabetTemplate.add( (char) i );
        }

        // заполняем список пустыми значениями
        for (int i = 0; i < alphabetTemplate.size(); i++) {
            cryptStringTemplate.add((char) 0);
        }

        // записываем ключевое слово
        int pos = key;
        for (int i = 0; i < keyword.length(); i++) {
            if (!cryptStringTemplate.contains(keyword.charAt(i))) {
                cryptStringTemplate.set(pos++, keyword.charAt(i));
            }
        }

        // дописывваем остальны буквы алфавита
        int indexOfAlphabet = 0;
        while(pos != key) {
            if (!cryptStringTemplate.contains(alphabetTemplate.get(indexOfAlphabet))) {
                cryptStringTemplate.set(pos, alphabetTemplate.get(indexOfAlphabet));
                pos = pos+1 >= 32 ? 0 : pos+1;
            }
            indexOfAlphabet = indexOfAlphabet+1 >= 32 ? 0 : indexOfAlphabet+1;
        }

        System.out.println("Шаблон алфавита: " + alphabetTemplate);
        System.out.println("Шаблон зашифрованного сообщения: " + cryptStringTemplate);
    }

    public String encryptMessage(String message) {

        return null;
    }
}
