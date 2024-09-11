package com.globant.mapper;

import com.globant.domain.cart.CartComboSimple;
import com.globant.domain.entity.CartComboEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartComboMapper {

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    CartComboEntity fromDto (CartComboSimple cartCombo);

    CartComboSimple fromEntity (CartComboEntity cartCombo);

}
