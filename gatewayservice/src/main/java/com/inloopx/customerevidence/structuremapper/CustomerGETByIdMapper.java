package com.inloopx.customerevidence.structuremapper;

import com.inloopx.customerevidence.dto.CustomerDtoGETById;
import com.inloopx.customerevidence.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerGETByIdMapper extends BaseMapper<Customer, CustomerDtoGETById> {
}
