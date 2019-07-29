package com.inloopx.customerevidence.repository;

import com.inloopx.customerevidence.entity.Customer;
import com.inloopx.customerevidence.entity.Product;
import com.inloopx.customerevidence.exception.EntityNotFound;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductRepository extends BaseRepository<Product> {

    @PersistenceContext(unitName = "customerEvidence")
    EntityManager entityManager;

    public ProductRepository() {
        super(Product.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Product updateModel(Product dbEntity, Product requestedEntity) {

        dbEntity.setName(requestedEntity.getName());
        dbEntity.setDescription(requestedEntity.getDescription());
        dbEntity.setCode(requestedEntity.getCode());
        dbEntity.setPrice(requestedEntity.getPrice());
        dbEntity.setPriceWithVat(requestedEntity.getPriceWithVat());

        update(dbEntity);

        return dbEntity;

    }
}
