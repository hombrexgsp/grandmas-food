package com.globant.mapper;

import com.globant.domain.entity.CartComboEntity;
import domain.payment.cart.CartComboSimple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartComboMapper {

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    CartComboEntity fromDto (CartComboSimple cartCombo);

    CartComboSimple fromEntity (CartComboEntity cartCombo);

}
