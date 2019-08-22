package com.inloopx.customerevidence.exception;

import javax.ejb.ApplicationException;
import java.util.Optional;

@ApplicationException(rollback = true)
public class EntityNotFound extends RuntimeException {

    private Class clazz;

    public EntityNotFound(Class clazz) {
        this.clazz = clazz;
    }

    public EntityNotFound() {
    }

    public Optional<Class> getClazz() {
        return Optional.ofNullable(clazz);
    }
}
