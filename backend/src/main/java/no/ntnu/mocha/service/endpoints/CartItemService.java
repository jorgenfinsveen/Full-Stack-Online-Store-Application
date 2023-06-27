package no.ntnu.mocha.service.endpoints;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.mocha.DTO.CartItemDto;
import no.ntnu.mocha.domain.entities.CartItem;
import no.ntnu.mocha.domain.entities.Order;
import no.ntnu.mocha.domain.entities.Product;
import no.ntnu.mocha.domain.repository.CartItemRepository;
import no.ntnu.mocha.domain.repository.OrderRepository;
import no.ntnu.mocha.domain.repository.ProductRepository;

/**
 * <h1>Business Logic Service class for Cart Item</h1>
 * 
 * <p>Representing an Service class for the Cart Item and implements the
 * Cart Item Service interface with the additional methods. </p>
 * 
 * @version 21.04.2023
 * @since   21.04.2023
 */
@Service public class CartItemService {

    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private OrderRepository orderRepository;



    /**
     * Returns the cart item with the given id.
     * 
     * @param id    the id of the cart item.
     * @return {@code CartItem} with the given id, or
     *          {@code null} if nothing found.
     */
    public CartItem getCartItem(long id) {
        return cartItemRepository.findById(id).orElse(null);
    }


    /**
     * Returns all cart items associated with a given order.
     * 
     * @param id the id of the order.
     * @return cart-items with the given order id.
     */
    public Iterable<CartItem> getCart(long id) {
        return cartItemRepository.getAllByOrderId(id);
    }


    /**
     * Returns all items of an order represented through product
     * entities.
     * 
     * @param id the id of the order.
     * @return products of the given order.
     */
    public Iterable<Product> getCartProducts(long id) {
        return ((List<CartItem>) getCart(id))
            .stream()
            .map(item -> productRepository
                .findById(item
                    .getProduct()
                    .getId())
                .orElse(null))
            .collect(Collectors.toList());
    }


    /**
     * Returns the Cart Item with the given order id and product id.
     * 
     * @param oId   The order id to find cart item for.
     * @param pId   The product id to find cart item for.
     * @return {@code CartItem} found, or {@code null} if
     *          nothing found.
     */
    public CartItem getCartItemByOrderAndProduct(Order oId, Product pId) {
        return cartItemRepository.findCartItemByOrderAndProduct(oId, pId)
            .orElse(null);
    }


    /**
     * Saves the cart item into the database.
     * 
     * @param cartItem  the cart item to be saved.
     */
    public CartItem addCartItem(CartItemDto dto) {

        Optional<Order> order = orderRepository.findById(dto.getOrderId());
        Optional<Product> product = productRepository.findById(dto.getProductId());

        return (order.isPresent() && product.isPresent()) ? 
            cartItemRepository.save(
                new CartItem(
                    order.get(), 
                    product.get(), 
                    dto.getAmount()
                ) 
            ) : null;
    }


    /**
     * Updates the cart item with the given id to the 
     * database.
     * 
     * @param id        the id of the cart item to update
     * @param cartItem  the updated {@code cartItem}
     */
    public void updateCartItem(long id, CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }


    /**
     * Deletes the cart item by the given id.
     * 
     * @param id    the id of the cart item to be
     *              deleted.
     */
    public void deleteCartItem(long id) {
        cartItemRepository.deleteById(id);
    }


    /**
     * Deletes all the item in the cart item
     * with the given order id.
     * 
     * @param id    the order id of the cart items to be deleted.
     */
    public void deleteAllCartItemById(long id) {
        cartItemRepository.deleteAllCartItemByOrderId(id);
    }


    /**
     * Increments the amount-field of a given cart-item by 1.
     * 
     * @param id the id of the cart-item to increment.
     */
    public void incrementCartItem(long id) {
        cartItemRepository.incrementCartItem(id);
    }


    /**
     * Decrements the amount-field of a given cart-item by 1.
     * 
     * @param id the id of the cart-item to decrement.
     */
    public void decrementCartItem(long id) {
        cartItemRepository.decrementCartItem(id);
        Optional<CartItem> item = cartItemRepository.findById(id);
        if (item.isPresent() && item.get().getAmount() == 0) cartItemRepository.delete(item.get());
    }
}
