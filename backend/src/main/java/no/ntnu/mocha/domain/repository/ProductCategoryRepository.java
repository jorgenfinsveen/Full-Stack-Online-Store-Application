package no.ntnu.mocha.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.ntnu.mocha.domain.entities.ProductCategory;

/**
 * Represents ProductCategoryRepository which extends
 * CrudRepsitory for CRUD-functionalites.
 * 
 * @version 21.04.2023
 * @since   21.04.2023
 * @ProductCategory
 */
@RepositoryRestResource
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
    
    /**
     * Finds the product category by the given name.
     * 
     * @param name  the name of the product category.
     * @return  the product category by the given name.
     */
    Optional<ProductCategory> findByName(String name);
}
