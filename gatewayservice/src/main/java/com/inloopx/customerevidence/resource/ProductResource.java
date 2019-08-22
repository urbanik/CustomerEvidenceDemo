package com.inloopx.customerevidence.resource;

import com.inloopx.customerevidence.dto.ProductDto;
import com.inloopx.customerevidence.entity.Product;
import com.inloopx.customerevidence.repository.ProductRepository;
import com.inloopx.customerevidence.structuremapper.ProductMapper;
import io.swagger.annotations.Api;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.Principal;
import java.util.List;

@Path("/products")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Product CRUD service")
@RolesAllowed("admin")
public class ProductResource extends BaseResource<Product, ProductDto>{

    @Inject
    Principal principal;

    @Inject
    JsonWebToken token;

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

    @Override
    @RolesAllowed("admin")
    public List<ProductDto> getAll() {
        return super.getAll();
    }

    @GET
    @PermitAll
    @Path("all")
    public String hello(){
        return "principal " + this.principal.getName() + " groups: " + token.getGroups();
    }

    @GET
    @PermitAll
    @RolesAllowed("admin")
    @Path("test")
    public String dukesOnly(){
        return "principal " + this.principal.getName() + " groups: " + token.getGroups();
    }

}
