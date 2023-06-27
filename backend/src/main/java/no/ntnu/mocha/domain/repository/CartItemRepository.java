package no.ntnu.mocha.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import no.ntnu.mocha.domain.entities.CartItem;
import no.ntnu.mocha.domain.entities.Order;
import no.ntnu.mocha.domain.entities.Product;

/**
 * Represents CartItemRepository which extends
 * CrudRepsitory for CRUD-functionalites.
 * 
 * @version 21.03.2023
 * @since   21.03.2023
 * @see     CartItem
 */
@RepositoryRestResource
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    /**
     * Finds a {@link CartItem} object in the cart
     * by matching the order id and product id.
     * 
     * @param order   the order of the cart item
     * @param product   the product of the product   
     * @return  an {@code Optional} containing the cart item
     *          or an empty {@code Optional} if no match was found.
     */
    Optional<CartItem> findCartItemByOrderAndProduct(Order order, Product product);

    Optional<Product> findByProductId(long id);

    /**
     * Deletes all {@link CartItem} with the given order id.
     * 
     * @param id    the order id of the Cart Item
     */
    @Transactional
    @Modifying
    @Query(value = "delete from cart_item where order_id = ?1", nativeQuery = true)
    void deleteAllCartItemByOrderId(long id);

    @Query(value = "select * from cart_item c where c.order_id = ?1", nativeQuery = true)
    List<CartItem> getAllByOrderId(long id);

    @Transactional
    @Modifying
    @Query(value = "update cart_item c set c.amount = c.amount + 1 where c.cart_item_id = ?1", nativeQuery = true)
    void incrementCartItem(long id);

    @Transactional
    @Modifying
    @Query(value = "update cart_item c set c.amount = c.amount + 1 where c.cart_item_id = ?1", nativeQuery = true)
    void decrementCartItem(long id);
}
