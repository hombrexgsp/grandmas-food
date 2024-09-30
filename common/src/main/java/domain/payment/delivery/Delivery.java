package domain.payment.delivery;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = Delivered.class, name = "delivered"),
        @JsonSubTypes.Type(value = Pending.class, name = "pending")
})
public sealed interface Delivery permits Delivered, Pending {}
