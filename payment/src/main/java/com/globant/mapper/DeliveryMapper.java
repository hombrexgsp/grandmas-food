package com.globant.mapper;

import com.globant.domain.delivery.CreateDelivery;
import com.globant.domain.entity.DeliveryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    @Mapping(target = "id", ignore = true)
    DeliveryEntity fromDto(CreateDelivery createDelivery);

    CreateDelivery fromEntity(DeliveryEntity deliveryEntity);
}
