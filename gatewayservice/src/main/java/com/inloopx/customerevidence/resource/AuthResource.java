package com.inloopx.customerevidence.resource;

import com.inloopx.customerevidence.entity.Customer;
import com.inloopx.customerevidence.entity.Order;
import com.inloopx.customerevidence.entity.OrderItem;
import com.inloopx.customerevidence.entity.Product;
import com.inloopx.customerevidence.exception.ErrorResponse;
import com.inloopx.customerevidence.repository.CustomerRepository;
import com.inloopx.customerevidence.repository.OrderRepository;
import com.inloopx.customerevidence.repository.ProductRepository;
import com.inloopx.userservice.dto.RoleDto;
import com.inloopx.userservice.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Path("/users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Login and register endpoints for user")
public class AuthResource {

    Random random = new Random();

    @EJB
    RestClient restClient;

    @EJB
    CustomerRepository customerRepository;

    @EJB
    ProductRepository productRepository;

    @EJB
    OrderRepository orderRepository;

    @ConfigProperty(name = "user-service-base-url")
    @Inject
    String userServiceBaseUrl;

    @POST
    @Path("/login")
    @ApiOperation(value = "Login", notes = "Return json data of user, access token, refresh token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 409, message = "Wrong input",response = ErrorResponse.class)
    })
    public Response login(@Valid UserDto userDto) {

        return restClient.callOtherModule(userServiceBaseUrl + "userservice/api/users", "login", userDto);

    }

    @POST
    @ApiOperation(value = "Registration", notes = "Return json data of created user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Wrong input",response = ErrorResponse.class)
    })
    @Path("/register")
    public Response register(@Valid UserDto userDto) {

        return restClient.callOtherModule(userServiceBaseUrl + "userservice/api/users", "register", userDto);

    }

    @POST
    @ApiOperation(value = "Generate products and customers", notes = "Return json data")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
    })
    @Path("generateProductsAndCustomers/{count}")
    public void generateProductsAndCustomers(@PathParam("count") int count) {

        generateProducts(count);
        generateCustomers(count);

    }

    @POST
    @ApiOperation(value = "Generate orders", notes = "Return json data")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
    })
    @Path("generateOrders/{count}")
    public void generateOrders(@PathParam("count") int count) {

        for (int i = 0; i < count; i++)
        {
            orderRepository.saveModel(orderFactory(count));
        }

    }

    public void generateProducts(int count){

        for (int i = 0; i < count; i++)
        {
            productRepository.saveModel(productFactory());
        }
    }

    public void generateCustomers(int count){

        for (int i = 0; i < count; i++)
        {
            customerRepository.saveModel(customerFactory());
        }
    }


    public Product productFactory(){
        double price = random.nextDouble()*100;
        return new Product(getRandomString(5), getRandomString(20), getRandomString(10), price, price*1.2);
    }

    public Customer customerFactory(){
        return new Customer(getRandomString(5), getRandomString(6), getRandomString(13), getRandomString(10));
    }

    public Order orderFactory(int range){

        List<OrderItem> orderItems = new LinkedList<>();
        for (int i = 0; i < range; i++)
        {
            orderItems.add(orderItemFactory(range));
        }

        Optional<Customer> maybeCustomer = customerRepository.getById(getRandomNumberInRange(1,range));
        return new Order(maybeCustomer.get(), orderItems);
    }

    public OrderItem orderItemFactory(int range){
        Product product  = productRepository.getById(getRandomNumberInRange(1,range)).get();
        OrderItem orderItem = new OrderItem(product);
        int amount = getRandomNumberInRange(1,10);
        orderItem.setCount(amount);
        orderItem.setPrice(amount*product.getPrice());
        orderItem.setPriceWithVat(amount*product.getPriceWithVat());
        return orderItem;
    }

    public String getRandomString(int length){

        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();

        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER;
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);

        }

        return sb.toString();

    }

    private int getRandomNumberInRange(int min, int max) {

        return random.nextInt((max - min) + 1) + min;
    }

}
