package com.inloopx.customerevidence.resource;

import com.inloopx.customerevidence.exception.EntityNotFound;
import com.inloopx.customerevidence.repository.BaseRepository;
import com.inloopx.customerevidence.structuremapper.BaseMapper;

import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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

    @POST
    public D post(@Valid D dto) {

        E entity = (E) getMapper().dtoToEntity(dto);
        getRepository().saveModel(entity);
        D result = (D) getMapper().entityToDto(entity);
        return result;

    }

    @GET
    @Path("/{id}")
    public D get(@PathParam("id") int id) {

        Optional<E> maybeEntity = getRepository().getById(id);

        if (!maybeEntity.isPresent()) {

            throw new EntityNotFound(clazz);

        }

        D dto = (D) getMapper().entityToDto(maybeEntity.get());
        return dto;

    }

    @GET
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
    public void delete(@PathParam("id") int id) {

        Optional<E> maybeEntity = getRepository().getById(id);

        if (!maybeEntity.isPresent()) {

            throw new EntityNotFound(clazz);

        }

        getRepository().deleteModel(maybeEntity.get());

    }

}
