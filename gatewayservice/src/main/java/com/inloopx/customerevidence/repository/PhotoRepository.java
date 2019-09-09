package com.inloopx.customerevidence.repository;

import com.inloopx.customerevidence.entity.Photo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PhotoRepository extends BaseRepository<Photo>{

    @PersistenceContext(unitName = "customerEvidence")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Photo updateModel(Photo dbEntity, Photo requestEntity) {
        return null;
    }

}
