package domain.combo.error;

public class ComboDuplicatedName extends RuntimeException {
    public ComboDuplicatedName(String message) {
        super(message);
    }
}
