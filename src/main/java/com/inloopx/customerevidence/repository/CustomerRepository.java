package com.inloopx.customerevidence.repository;

import com.inloopx.customerevidence.entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerRepository extends BaseRepository<Customer> {

    @PersistenceContext(unitName = "customerEvidence")
    EntityManager entityManager;

    public CustomerRepository() {
        super(Customer.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Customer updateModel(Customer dbEntity, Customer requestEntity) {
        dbEntity.setName(requestEntity.getName());
        dbEntity.setSurname(requestEntity.getSurname());
        dbEntity.setPhone(requestEntity.getPhone());
        dbEntity.setEmail(requestEntity.getEmail());
        dbEntity.setPhoto(requestEntity.getPhoto());

        update(dbEntity);

        return  dbEntity;
    }

}
