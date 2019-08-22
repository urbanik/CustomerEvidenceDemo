package com.inloopx.customerevidence.repository;

import com.inloopx.customerevidence.entity.Customer;
import com.inloopx.customerevidence.entity.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerRepository extends BaseRepository<Customer> {

    @PersistenceContext(unitName = "customerEvidence")
    EntityManager entityManager;

    @EJB
    private OrderRepository orderRepository;

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

    @Override
    public void deleteModel(Customer entityToDelete) {

        for (Order order : orderRepository.getAll()) {
            if(order.getCustomer().getId() == entityToDelete.getId()){
                throw new UnsupportedOperationException();
            }
        }
        deleteModel(entityToDelete);
    }
}
