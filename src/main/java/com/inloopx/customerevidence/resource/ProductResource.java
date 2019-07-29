package com.inloopx.customerevidence.resource;

import com.inloopx.customerevidence.dto.ProductDto;
import com.inloopx.customerevidence.entity.Product;
import com.inloopx.customerevidence.repository.ProductRepository;
import com.inloopx.customerevidence.structuremapper.ProductMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/products")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource extends BaseResource<Product, ProductDto>{

    public ProductResource(){super(Product.class);}
    @EJB
    private ProductRepository repository;

    @Inject
    private ProductMapper mapper;

    @Override
    public ProductRepository getRepository() {
        return repository;
    }

    @Override
    public ProductMapper getMapper(){ return mapper;}

}
