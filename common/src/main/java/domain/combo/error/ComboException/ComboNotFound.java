package domain.combo.error.ComboException;

import java.util.UUID;

public final class ComboNotFound extends RuntimeException {
    public ComboNotFound(UUID comboId) {
        super(STR."Combo with uuid \{comboId} not found");
    }
}
