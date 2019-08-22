package com.inloopx.customerevidence.structuremapper;

import com.inloopx.customerevidence.dto.OrderDto;
import com.inloopx.customerevidence.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CustomerMapper.class, OrderItemMapper.class})
public interface OrderMapper extends BaseMapper<Order, OrderDto>{

    @Mapping(source = "created", target = "date")
    OrderDto entityToDto(Order order);

    @Mapping(source = "date", target = "created")
    Order dtoToEntity(OrderDto orderDto);

}
