package domain.combo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    HAMBURGERS_AND_HOTDOGS("HAMBURGERS_AND_HOTDOGS"),
    CHICKEN("CHICKEN"),
    FISH("FISH"),
    MEATS("MEATS"),
    DESSERTS("DESSERTS"),
    VEGAN_FOOD("VEGAN_FOOD"),
    KIDS_MEALS("KIDS_MEALS");

    private final String value;
}
