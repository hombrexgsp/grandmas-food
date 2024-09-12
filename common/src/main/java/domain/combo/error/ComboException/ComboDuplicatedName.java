package domain.combo.error.ComboException;

public class ComboDuplicatedName extends RuntimeException {
    public ComboDuplicatedName(String message) {
        super(message);
    }
}
