package domain.combo.error;

public class ComboInUse extends RuntimeException {
    public ComboInUse(String message) {
        super(message);
    }
}
