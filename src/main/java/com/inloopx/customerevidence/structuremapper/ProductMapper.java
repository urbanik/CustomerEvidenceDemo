package com.inloopx.customerevidence.structuremapper;

import com.inloopx.customerevidence.dto.ProductDto;
import com.inloopx.customerevidence.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper extends BaseMapper<Product, ProductDto>{

    @Mapping(source = "description", target = "about")
    ProductDto entityToDto(Product entity);

    @Mapping(source = "about", target = "description")
    Product dtoToEntity(ProductDto dto);
}
