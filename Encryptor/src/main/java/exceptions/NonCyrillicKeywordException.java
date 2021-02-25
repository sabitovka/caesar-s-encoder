package exceptions;

public class NonCyrillicKeywordException extends RuntimeException {
    public NonCyrillicKeywordException(String message) {
        super(message);
    }
}
