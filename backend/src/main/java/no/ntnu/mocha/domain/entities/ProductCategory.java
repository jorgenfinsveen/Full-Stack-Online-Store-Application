package no.ntnu.mocha.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * <h1>Product Category</h1>
 * 
 * <p>Represents an simple entity class for Product Category
 *  with the JPA's {@code @Entity} annotation. 
 * </p>
 * 
 * @version 21.04.2023
 * @since   21.04.2023
 */
@Entity
@Table(name = "product_category")
public class ProductCategory {

    /** Unique id for the product category */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "pc_Id")
    private long id;

    /** Category name, represented as Varchar(255). */
    @Column(unique = true, name = "category_name")
    private String name;

    /** Description, represented as Varchar(255). */
    @Column(name = "category_description")
    private String description;


    /**
     * Empty constructor.
     */
    public ProductCategory(){}


    /**
     * Creates an instance of Product Category.
     * 
     * @param name          the name of product category.
     * @param description   the description of the product category.
     */
    public ProductCategory(String name, String description) {
        super();
        this.setName(name);
        this.setDescription(description);
    }


    /**
     * Returns the id of the product category.
     * 
     * @return the id of the product category
     */
    public long getId() {
        return id;
    } 

    /**
     * Returns the name of the
     * Product Category.
     * 
     * @return  the name of the product category.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name of the product category.
     * 
     * @param name  the name of the product category.
     */
    private void setName(String name) {
        this.name = name.toUpperCase();
    }


    /**
     * Returns the description of the 
     * product category.
     * 
     * @return  the description of the product
     *          category.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description of the product
     * category.
     * 
     * @param description   the description of the 
     *                      product category.
     */
    private void setDescription(String description) {
        this.description = description;
    }
}
