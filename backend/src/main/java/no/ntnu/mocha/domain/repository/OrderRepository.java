package no.ntnu.mocha.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import no.ntnu.mocha.domain.entities.Order;

/**
 * Represents OrderRepository which extends
 * CrudRepsitory for CRUD-functionalites.
 * 
 * @version 21.03.2023
 * @since   21.03.2023
 * @see     Order
 */
@RepositoryRestResource
public interface OrderRepository extends CrudRepository<Order, Long> {

    /**
     * Returns all the orders by the given id.
     * 
     * @param id    the id of the orders.
     * @return  all the orders by the given id.
     */
    List<Order> findAllOrdersById(Order id);

    List<Order> findAllByUserId(long id);

    
    @Transactional
    @Modifying
    @Query(value = "update product_order o set o.order_date = ?2 where o.order_id = ?1", nativeQuery = true)
    void update(long id, LocalDate date);
}
