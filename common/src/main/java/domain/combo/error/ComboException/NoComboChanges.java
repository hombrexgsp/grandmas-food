package domain.combo.error.ComboException;

public final class NoComboChanges extends RuntimeException {
    public NoComboChanges() {
        super("No changes detected on imminent update");
    }
}
