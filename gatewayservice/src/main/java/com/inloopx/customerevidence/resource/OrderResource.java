package com.inloopx.customerevidence.resource;

import com.inloopx.customerevidence.dto.OrderDto;
import com.inloopx.customerevidence.entity.Order;
import com.inloopx.customerevidence.repository.OrderRepository;
import com.inloopx.customerevidence.structuremapper.OrderMapper;
import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/orders")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Order CRUD service")
public class OrderResource extends BaseResource<Order, OrderDto> {

    public OrderResource() {
        super(Order.class);
    }

    @EJB
    private OrderRepository repository;

    @Inject
    private OrderMapper mapper;

    @Override
    public OrderRepository getRepository() {
        return repository;
    }

    @Override
    public OrderMapper getMapper() {
        return mapper;
    }


}
