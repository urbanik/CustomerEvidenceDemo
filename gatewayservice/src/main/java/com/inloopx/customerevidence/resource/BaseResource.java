package com.inloopx.customerevidence.resource;

import com.inloopx.customerevidence.exception.EntityNotFound;
import com.inloopx.customerevidence.exception.ErrorResponse;
import com.inloopx.customerevidence.repository.BaseRepository;
import com.inloopx.customerevidence.structuremapper.BaseMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;



public abstract class BaseResource<E, D> { // Entity, Dto

    protected Class<E> clazz;

    public BaseResource(Class<E> clazz) {
        this.clazz = clazz;
    }

    public BaseResource() {
    }

    public abstract BaseRepository getRepository();

    public abstract BaseMapper getMapper();


//    @Inject
//    private JsonWebToken callerPrincipal;

    @POST
    @ApiOperation(value = "Insert data of created entity", notes = "Return json data of created entity to client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "When entity attributes are incorrect or null",response = ErrorResponse.class)
    })
    public D post(@Valid D dto) {

        E entity = (E) getMapper().dtoToEntity(dto);
        getRepository().saveModel(entity);
        D result = (D) getMapper().entityToDto(entity);
        return result;

    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Retrieve data of entity with given ID", notes = "Return json data of the entity to client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "When entity with given ID is not found")
    })
    public D get(@PathParam("id") int id) {

        Optional<E> maybeEntity = getRepository().getById(id);

        if (!maybeEntity.isPresent()) {

            throw new EntityNotFound(clazz);

        }

        D dto = (D) getMapper().entityToDto(maybeEntity.get());
        return dto;

    }

    @GET
    @ApiOperation(value = "Retrieve all records (with data) of entity", notes = "Return json data of the entity to client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
    })
    public List<D> getAll() {
        List<D> listDto = new LinkedList<>();
        List<E> listEntity = getRepository().getAll();

        for (E e : listEntity) {

            listDto.add((D) getMapper().entityToDto(e));
        }

        return listDto;
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update data of entity with given ID", notes = "Return json data of updated entity to client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful update"),
            @ApiResponse(code = 400, message = "When entity attributes are incorrect or null"),
            @ApiResponse(code = 404, message = "When entity with given ID is not found")
    })
    public D update(@PathParam("id") int id, @Valid D dto) {

        Optional<E> maybeEntity = getRepository().getById(id);

        if (!maybeEntity.isPresent()) {

            throw new EntityNotFound(clazz);

        }

        E entity = (E) getMapper().dtoToEntity(dto);
        getRepository().updateModel(maybeEntity.get(), entity);
        return (D) getMapper().entityToDto(entity);

    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Update data of entity with given ID", notes = "Return json data of updated entity to client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "When entity with given ID is not found")
    })
            public void delete(@PathParam("id") int id) {

        Optional<E> maybeEntity = getRepository().getById(id);

        if (!maybeEntity.isPresent()) {

            throw new EntityNotFound(clazz);

        }

        getRepository().deleteModel(maybeEntity.get());

    }

}
