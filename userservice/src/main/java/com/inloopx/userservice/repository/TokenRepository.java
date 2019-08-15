package com.inloopx.userservice.repository;

import com.inloopx.userservice.entity.Token;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TokenRepository extends BaseRepository<Token> {

    @PersistenceContext(unitName = "userService")
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Token updateModel(Token dbEntity, Token requestEntity) {
        dbEntity.setAccessToken(requestEntity.getAccessToken());
        return update(dbEntity);
    }
}
