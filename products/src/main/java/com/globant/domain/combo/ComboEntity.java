package com.globant.domain.combo;

import domain.combo.Category;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection = "combos")
@Data
public class ComboEntity {

    @MongoId
    private ObjectId id;
    private final UUID uuid;
    private final String fantasyName;
    private final Category category;
    private final String description;
    private final Float price;
    private final boolean available;

}
