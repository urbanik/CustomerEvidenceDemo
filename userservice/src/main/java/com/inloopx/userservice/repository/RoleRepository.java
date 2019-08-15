package com.inloopx.userservice.repository;

import com.inloopx.userservice.entity.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class RoleRepository extends BaseRepository<Role> {

    @PersistenceContext(unitName = "userService")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    public Optional<Role> getById(int id) {

        return Optional.ofNullable(entityManager.find(Role.class, id));

    }

}
