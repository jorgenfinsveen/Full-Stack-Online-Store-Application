package no.ntnu.mocha.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import no.ntnu.mocha.domain.entities.Product;

/**
 * Represents ProductRepository which extends
 * CrudRepsitory for CRUD-functionalites.
 * 
 * @version 21.03.2023
 * @since   21.03.2023
 * @see     Product
 */
@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {

    /**
     * Returns the product by name.
     * 
     * @param name the name of the product
     * @return the name of the product
     */
    Product findByName(String name);

    @Query(value = "select * from product p where p.product_id = ?1", nativeQuery = true)
    Optional<Product> findByProductId(long id);

    @Transactional
    @Modifying
    @Query(value = "update product p set p.display = ?2, p.price = ?3, p.image_id = ?4, p.pc_id = ?5, p.amount = ?6, p.description = ?7, p.name = ?8, p.purchases = ?9 where p.product_id = ?1", nativeQuery = true)
    void updateProduct(long id, boolean display, double price, long image, long category, String amount, String description, String name, int totalBought);

    @Transactional
    @Modifying
    @Query(value = "update product p set p.purchases = p.purchases + 1 where p.product_id = ?1", nativeQuery = true)
    void increment(long id);

    @Transactional
    @Modifying
    @Query(value = "update product p set p.price = ?2 where p.product_id = ?1", nativeQuery = true)
    void updatePrice(long id, double price);

    @Query(value = "select * from product p where p.display = 1 order by p.price desc", nativeQuery = true)
    List<Product> getAllOrderedByPrice();

    @Query(value = "select * from product p where p.display = 1 order by p.purchases desc", nativeQuery = true)
    List<Product> getAllOrderedByPurchases();

    @Query(value = "select * from product p where p.pc_id = ?1 and p.display = 1", nativeQuery = true)
    List<Product> getAllByCategory(long id);

    @Query(value = "select * from product p where p.display = 1", nativeQuery = true)
    List<Product> getAllToDisplay();
}
