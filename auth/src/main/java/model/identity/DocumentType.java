package model.identity;

import java.util.Arrays;

public enum DocumentType {
    CC("National ID card"),
    TI("Identity card for minos"),
    PASSPORT("Passport"),
    CE("International ID card");

    private final String description;

    DocumentType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    //the main idea is use the document type with a select list.
    public static DocumentType fromString(String text){
        return Arrays.stream(DocumentType.values())
                .filter(type -> type.name().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid document type" + text));
    }
}
