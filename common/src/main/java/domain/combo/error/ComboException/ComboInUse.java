package domain.combo.error.ComboException;

public class ComboInUse extends RuntimeException {
    public ComboInUse(String message) {
        super(message);
    }
}
