import exceptions.KeyOutOfBoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Encryptor {

    private final List<Character> alphabetTemplate = new ArrayList<>();
    private List<Character> cryptStringTemplate = new ArrayList<>(32);

    private int key;// ключ
    private String keyword; // ключевое слово

    public Encryptor(int key, String keyword) throws KeyOutOfBoundException {
        if (key < 0 || key >= 32) {
            throw new KeyOutOfBoundException("Key must be in 0..31");
        }
        this.key = key;
        this.keyword = keyword.toUpperCase();
        setupCryptTemplate();
    }

    private void setupCryptTemplate() {

        // заполняем массив алфватом (1040 - Код Юникод символа А, 1071 - Код символа Я)
        for (int i = 1040; i <= 1071; i++) {
            alphabetTemplate.add( (char) i );
            cryptStringTemplate.add((char) 0);
        }

        // записываем ключевое слово
        int pos = key;
        for (int i = 0; i < keyword.length(); i++) {
            if (!cryptStringTemplate.contains(keyword.charAt(i))) {
                cryptStringTemplate.set(pos, keyword.charAt(i));
                pos = pos+1 >= 32 ? 0 : pos+1;
            }
        }

        // дописывваем остальны буквы алфавита
        int indexOfAlphabet = 0;

        while(pos != key || indexOfAlphabet != 32) {
            if (!cryptStringTemplate.contains(alphabetTemplate.get(indexOfAlphabet))) {
                cryptStringTemplate.set(pos, alphabetTemplate.get(indexOfAlphabet));
                pos = pos+1 >= 32 ? 0 : pos+1;
            }
            indexOfAlphabet++;/* = indexOfAlphabet+1 >= 32 ? 0 : indexOfAlphabet+1*/
        }

        System.out.println("Шаблон алфавита: \t\t\t\t\t" + alphabetTemplate);
        System.out.println("Шаблон зашифрованного алфавита: \t" + cryptStringTemplate);
    }

    public String encryptMessage(String message) {
        // приводим к верхнему регистру, делим на массив
        // собираем все в строку с помощью индексации по известным значениям
        return Stream.of(message.toUpperCase().split(""))
                .reduce("",
                        (s, s2) -> s += alphabetTemplate.indexOf(s2.charAt(0)) != -1 ?
                                                    cryptStringTemplate.get(alphabetTemplate.indexOf(s2.charAt(0))) : " ");
    }
}
