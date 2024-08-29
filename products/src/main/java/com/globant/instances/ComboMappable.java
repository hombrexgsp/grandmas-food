package com.globant.instances;

import com.globant.domain.ComboEntity;
import com.globant.typeclass.Mappable;
import domain.combo.Combo;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComboMappable extends Mappable<ComboEntity, Combo> {

    @Override
    Combo to(ComboEntity comboEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    ComboEntity from(Combo combo);
}
