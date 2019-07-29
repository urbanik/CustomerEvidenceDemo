package com.inloopx.customerevidence.repository;

import com.inloopx.customerevidence.entity.Customer;
import com.inloopx.customerevidence.entity.Order;
import com.inloopx.customerevidence.entity.OrderItem;
import com.inloopx.customerevidence.entity.Product;
import com.inloopx.customerevidence.exception.EntityNotFound;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Optional;
import java.util.TimeZone;

@Stateless
public class OrderRepository extends BaseRepository<Order> {

    @EJB
    CustomerRepository customerRepository;

    @EJB
    ProductRepository productRepository;

    @PersistenceContext(unitName = "customerEvidence")
    EntityManager entityManager;

    public OrderRepository() {
        super(Order.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+2"));


    @Override
    public Order saveModel(Order order) {

        Order orderToSave = checkProductAndOrderItemsOfOrder(order);

        double totalOrderPrice = 0.0;
        double totalOrderPriceWithVat = 0.0;

        for (OrderItem orderItem : orderToSave.getOrderItems()) {

            orderItem.setOrder(orderToSave);
            Optional<Product> maybeProduct = productRepository.getById(orderItem.getProduct().getId());

            if (!maybeProduct.isPresent()) {

                throw new EntityNotFound(Product.class);

            }
            orderItem.setProduct(maybeProduct.get());
            orderItem.setPrice(orderItem.getCount()*maybeProduct.get().getPrice());
            orderItem.setPriceWithVat(orderItem.getCount()*maybeProduct.get().getPriceWithVat());
            totalOrderPrice += orderItem.getPrice();
            totalOrderPriceWithVat += orderItem.getPriceWithVat();

        }
        orderToSave.setCreated(new java.sql.Timestamp(calendar.getTime().getTime()));
        orderToSave.setPrice(totalOrderPrice);
        orderToSave.setPriceWithVat(totalOrderPriceWithVat);
        return save(orderToSave);
    }

    @Override
    public Order updateModel(Order dbEntity, Order requestEntity) {

        Order requestOrder = checkProductAndOrderItemsOfOrder(requestEntity);

        double totalUpdatedOrderPrice = 0.0;
        double totalUpdatedOrderPriceWithVat = 0.0;

        for (OrderItem orderItem : requestOrder.getOrderItems()) {

            orderItem.setOrder(requestOrder);
            Optional<Product> maybeProduct = productRepository.getById(orderItem.getProduct().getId());

            if (!maybeProduct.isPresent()) {

                throw new EntityNotFound(Product.class);

            }
            orderItem.setProduct(maybeProduct.get());
            orderItem.setPrice(orderItem.getCount()*maybeProduct.get().getPrice());
            orderItem.setPriceWithVat(orderItem.getCount()*maybeProduct.get().getPriceWithVat());
            totalUpdatedOrderPrice += orderItem.getPrice();
            totalUpdatedOrderPriceWithVat += orderItem.getPriceWithVat();

        }
        dbEntity.setUpdated(new java.sql.Timestamp(calendar.getTime().getTime()));
        dbEntity.setPrice(totalUpdatedOrderPrice);
        dbEntity.setPriceWithVat(totalUpdatedOrderPriceWithVat);
        return update(dbEntity);

    }

    public Order checkProductAndOrderItemsOfOrder(Order order){
        Optional<Customer> maybeCustomer = customerRepository.getById(order.getCustomer().getId());

        if (!maybeCustomer.isPresent()) {

            throw new EntityNotFound(Customer.class);

        }

        order.setCustomer(maybeCustomer.get());

        if(order.getOrderItems() == null){

            throw new EntityNotFound(Order.class);

        }

        return order;
    }

}
