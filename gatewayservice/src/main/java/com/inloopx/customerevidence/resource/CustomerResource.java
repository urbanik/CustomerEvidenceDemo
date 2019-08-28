package com.inloopx.customerevidence.resource;

import com.inloopx.customerevidence.dto.CustomerDto;
import com.inloopx.customerevidence.entity.Customer;
import com.inloopx.customerevidence.repository.CustomerRepository;
import com.inloopx.customerevidence.structuremapper.CustomerMapper;
import io.swagger.annotations.Api;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customers")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Customer CRUD service")
@RolesAllowed({"admin"})
public class CustomerResource extends BaseResource<Customer, CustomerDto>{

    public CustomerResource(){super(Customer.class);}

    @EJB
    private CustomerRepository repository;

    @Inject
    private CustomerMapper mapper;

    @Override
    public CustomerRepository getRepository() {
        return repository;
    }

    @Override
    public CustomerMapper getMapper(){ return mapper;}
}
