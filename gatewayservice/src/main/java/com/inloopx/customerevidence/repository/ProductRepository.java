package com.inloopx.customerevidence.repository;

import com.inloopx.customerevidence.entity.Order;
import com.inloopx.customerevidence.entity.OrderItem;
import com.inloopx.customerevidence.entity.Product;
import com.inloopx.customerevidence.exception.ViolatedBusinessRule;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductRepository extends BaseRepository<Product> {

    @PersistenceContext(unitName = "customerEvidence")
    EntityManager entityManager;

    @EJB
    private OrderRepository orderRepository;

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

    @Override
    public void deleteModel(Product entityToDelete) {

        for (Order order : orderRepository.getAll()) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (orderItem.getProduct().getId() == entityToDelete.getId()){
                    throw new ViolatedBusinessRule("Can not delete entity, because it is part of another entity!");
                }
            }

        }
        deleteModel(entityToDelete);
    }

}
