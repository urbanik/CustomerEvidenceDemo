package com.inloopx.customerevidence.structuremapper;

import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

public interface  BaseMapper<E, D> {

    BaseMapper INSTANCE = Mappers.getMapper(BaseMapper.class);

    D entityToDto(E entity);

    E dtoToEntity(D dto);

}
