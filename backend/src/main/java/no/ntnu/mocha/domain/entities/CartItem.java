package no.ntnu.mocha.domain.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



/**
 * Represents the Cart-Item entiry with the JPA's {@code @Entity} annotation. 
 * 
 * @version 21.03.2023
 * @since   22.04.2023
 */
@Entity
@Table(name = "cart_item")
public class CartItem {

    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private long id;

    /** The Order which the Cart Item belongs to. */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "order_id")
    private Order order;

    /** The Product which the Cart Item represents. */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    private Product product;

    /** The amount the product held by the Cart Item. */
    @Column(name = "amount")
    private int amount;



    /**
     * Empty Constructor.
     */
    public CartItem() {}


    /**
     * Creates an instance of cart item.
     * 
     * @param order     The Order of the Cart Item.
     * @param product   The Product of the Cart Item.
     * @param amount    The amount of the Cart Item.
     */
    public CartItem(Order order, Product product, int amount) {
        super();
        if (amount < 1) throw new IllegalArgumentException("The amount cannot be less than 1.");
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        if (order == null) throw new IllegalArgumentException("Order cannot be null.");

        this.setOrder(order);
        this.setProduct(product);
        this.setAmount(amount);
    }


    /**
     * Returns the id for the Cart Item.
     * 
     * @return the id for the Cart Item.
     */
    public long getId() {
        return this.id;
    }


    /**
     * Returns the amount for the Cart Item.
     * 
     * @return the amount for the Cart Item.
     */
    public int getAmount() {
        return this.amount;
    }


    /**
     * Sets the amount of the Cart Item.
     * 
     * @param amount the amount for the Cart Item.
     */
    private void setAmount(int amount) {
        this.amount = amount;
    }


    /**
     * Returns the order associated with the cart item.
     * 
     * @return order object
     */
    public Order getOrder() {
        return this.order;
    }


    /**
     * Set the Order which the Cart Item is associated with.
     * 
     * @param order the Order of the product.
     */
    private void setOrder(Order order) {
        this.order = order;
    }


    /**
     * Returns the product associated with the cart item.
     * 
     * @return product object
     */
    public Product getProduct() {
        return product;
    }


    /**
     * Set the Product which the Cart Item represents.
     * 
     * @param product the Product of the Cart Item.
     */
    private void setProduct(Product product) {
        this.product = product;
    }
}
