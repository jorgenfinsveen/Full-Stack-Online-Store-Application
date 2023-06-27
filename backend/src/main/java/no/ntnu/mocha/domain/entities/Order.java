package no.ntnu.mocha.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents an order.
 * 
 * @version 21.03.2023
 * @since   22.04.2023
 */
@Entity
@Table(name = "product_order")
public class Order {
    
    /** Unique Id for Order. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "order_id")
    private Long id;

    /** User entity of the Order. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** Given date for the current order */
    @Column(name = "order_date")
    private LocalDate date;


    /**
     * Empty constructor.
     */
    public Order() {}


    /**
     * Creates an instance of order item.
     * 
     * @param user The User which made the order.
     */
    public Order(User user) {
        super();
        this.setUser(user);
        this.setDate(LocalDate.now());
    }


    /**
     * Return this order items id.
     * 
     * @return this order items id.
     */
    public long getId() {
        return id;
    }


    /**
     * Returns the User of the order.
     * 
     * @return User of the order.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the User of the order.
     * @param user User which made the order.
     */
    private void setUser(User user) {
        this.user = user;
    }


    /**
     * Return when this order item was created.
     * 
     * @return when this order item was created.
     */
    public LocalDate getDate() {
        return date;
    }


    /**
     * Sets the date of the order.
     * 
     * @param date the date of the order.
     */
    private void setDate(LocalDate date) {
        this.date = date;
    }
}
