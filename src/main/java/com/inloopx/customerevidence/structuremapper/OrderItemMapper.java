package com.inloopx.customerevidence.structuremapper;

import com.inloopx.customerevidence.dto.OrderItemDto;
import com.inloopx.customerevidence.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProductMapper.class})
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemDto>{

    @Mapping(source = "count", target = "amount")
    OrderItemDto entityToDto(OrderItem order);

    @Mapping(source = "amount", target = "count")
    OrderItem dtoToEntity(OrderItemDto orderDto);

}
