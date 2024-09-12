package com.globant.mapper;

import domain.payment.Order;
import com.globant.domain.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartComboMapper.class)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "orderId")
    OrderEntity fromDto (Order order);

    @Mapping(source = "uuid", target = "orderId")
    Order fromEntity (OrderEntity order);

}

