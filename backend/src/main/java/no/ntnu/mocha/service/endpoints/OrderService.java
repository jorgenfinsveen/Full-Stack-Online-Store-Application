package no.ntnu.mocha.service.endpoints;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.mocha.DTO.CartItemDto;
import no.ntnu.mocha.DTO.OrderDto;
import no.ntnu.mocha.domain.entities.CartItem;
import no.ntnu.mocha.domain.entities.Order;
import no.ntnu.mocha.domain.entities.Product;
import no.ntnu.mocha.domain.entities.User;
import no.ntnu.mocha.domain.repository.CartItemRepository;
import no.ntnu.mocha.domain.repository.OrderRepository;
import no.ntnu.mocha.domain.repository.ProductRepository;
import no.ntnu.mocha.domain.repository.UserRepository;
import no.ntnu.mocha.service.email.EmailService;

/**
 * <h1>Business Logic Service class for Order </h1>
 * 
 * <p> Representing an Service class for the Order and implements the
 * Order Service interface with the additional methods. </p>
 * 
 * @version 22.04.2023
 * @since   22.04.2023
 */
@Service public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private EmailService emailService;



    /**
     * Returns the order with the given id.
     * 
     * @param id    the id of the order.
     * @return  {@code Order} with the given id.
     */
    public Order getOrderItem(long id) {
        return this.orderRepository.findById(id).orElse(null);
    }


    /**
     * Returns a list of all orders.
     * 
     * @return {@code List<Order>} of all the orders
     *         in the database.
     */
    public Iterable<Order> getAllOrders() {
        List<Order> order = new ArrayList<>();
        orderRepository.findAll().forEach(order::add);
        return order;
    }


    /**
     * Creates a new order and adds it to the database.
     * 
     * @param dto the dto representing the order.
     * @return the new order or null if error occured.
     */
    public Order createOrder(OrderDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
        if (user.isPresent()) {
            User owner = user.get();
            Order order = new Order(owner);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    
    /**
     * Returns a list of all orders by a user.
     * 
     * @return {@code List<Order>} of all the orders
     *         made by a given user.
     */
    public Iterable<Order> getAllByUser(long id) {
        List<Order> order = new ArrayList<>();
        orderRepository.findAllByUserId(id).forEach(order::add);
        return order;
    }


    /**
     * Adds an order to the database.
     * 
     * @param {@code order} to be added.
     */
    public void addOrder(Order order) {
        this.orderRepository.save(order);
    }


    /**
     * Adds an collection of the orders into the database.
     * 
     * @param order iterable collection of the orders to be added.
     */
    public void addAllOrderItems(Iterable<Order> order) {
        this.orderRepository.saveAll(order);
    }


    /**
     * Updates the order with the given id.
     * 
     * @param id    the id of the order to be updated.
     * @param dto   the dto representing the order.
     */
    public void update(long id, OrderDto dto) {
        LocalDate localDate = dto.getOrderDate();
        this.orderRepository.update(id, localDate);
    }

    
    /**
     * Deletes the order by the given id.
     * 
     * @param id    the id of the order to be
     *              deleted.
     */
    public void deleteOrder(long id) {
        this.cartItemRepository.deleteAllCartItemByOrderId(id);
        this.orderRepository.deleteById(id);
    } 


    /**
     * Submits a new order and sends email confirmation to the customer.
     * 
     * @param id the id of the user which made the order.
     * @param dtos list of cartItem DTOs.
     * @return true if the order was successfully submitted, otherwise false.
     */
    public boolean submitOrder(long id, List<CartItemDto> dtos) {
        Optional<User> userOpt = this.userRepository.findById(id);
        if (userOpt.isPresent()) {
            Order order = new Order(userOpt.get());
            this.orderRepository.save(order);

            for (CartItemDto dto : dtos) {
                Optional<Product> product = this.productRepository.findById(dto.getProductId());
                if (product.isPresent()) {
                    CartItem item = new CartItem(order, product.get(), dto.getAmount());
                    this.cartItemRepository.save(item);
                }
            }
            emailService.sendOrderConfirmation(userOpt.get().getEmail(), order);
            return true;
        }
        return false;
    }
}
