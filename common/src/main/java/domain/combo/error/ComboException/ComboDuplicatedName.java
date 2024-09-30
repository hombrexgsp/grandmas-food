package domain.combo.error.ComboException;

public final class ComboDuplicatedName extends RuntimeException {
    public ComboDuplicatedName(String name) {
        super(STR."Combo with name \{name} already exists");
    }
}
