package domain.combo.error;

public class ComboNotFound extends RuntimeException {
    public ComboNotFound(String message) {
        super(message);
    }
}
