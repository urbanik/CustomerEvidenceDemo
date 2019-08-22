package com.inloopx.customerevidence.structuremapper;

import com.inloopx.customerevidence.dto.ProductDtoGETById;
import com.inloopx.customerevidence.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductGETByIdMapper extends BaseMapper<Product, ProductDtoGETById> {
}
