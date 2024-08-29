package domain.combo;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record Combo(
        UUID uuid,

        @NotEmpty
        @Size(max = 255)
        String fantasyName,

        @NotNull
        Category category,

        @NotNull
        @Size(max = 511)
        String description,

        @NotNull
        @Positive
        Float price,

        @NotNull
        Boolean available
) {}