package com.inloopx.customerevidence.structuremapper;

import com.inloopx.customerevidence.dto.CustomerDto;
import com.inloopx.customerevidence.dto.CustomerDtoGETById;
import com.inloopx.customerevidence.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerGETByIdMapper extends BaseMapper<Customer, CustomerDtoGETById> {
}
