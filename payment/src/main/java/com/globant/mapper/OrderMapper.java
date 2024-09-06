package com.globant.mapper;

import com.globant.model.PersistOrder;
import com.globant.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "orderId", target = "uuid")
    Order fromDto (PersistOrder persistOrder);


    @Mapping(source = "uuid", target = "orderId")
    PersistOrder fromEntity (Order order);


}

