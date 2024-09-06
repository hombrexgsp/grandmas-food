package com.globant.instances;

import com.globant.domain.combo.ComboEntity;
import com.globant.typeclass.Mappable;
import domain.combo.Combo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ComboMappable extends Mappable<ComboEntity, Combo> {
    @Override
    Combo to(ComboEntity comboEntity);
}
