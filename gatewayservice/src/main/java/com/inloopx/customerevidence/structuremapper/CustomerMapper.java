package com.inloopx.customerevidence.structuremapper;

import com.inloopx.customerevidence.dto.CustomerDto;
import com.inloopx.customerevidence.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto>{

    @Mapping(source = "phone", target = "telephone")
    CustomerDto entityToDto(Customer entity);

    @Mapping(source = "telephone", target = "phone")
    Customer dtoToEntity(CustomerDto dto);

}
