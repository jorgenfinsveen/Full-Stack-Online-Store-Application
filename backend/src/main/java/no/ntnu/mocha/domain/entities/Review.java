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
 * Represents a customer review submitted on the website.
 * 
 * @since   06.02.2023
 * @version 22.04.2023
 */
@Entity
@Table(name = "review")
public class Review {
    
    /** Review ID (primary key) represented as Long in the database. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "review_id")
    private long id;

    /** User which made the review. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** The product which was reviewed.. */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /** Content of the review. */
    @Column(name = "review_comment")
    private String comment;

    /** Star rating of the review. */
    @Column(name = "stars")
    private int stars;

    /** Date of the review. */
    @Column(name = "review_date")
    private LocalDate date;


    
    /**
     * Emtpy Constructor.
     */
    public Review() {}


    /**
     * Creates an instance of Review.
     * 
     * @param user      the user which made the review. 
     * @param product   the product which is reviewed.
     * @param comment   the content of the review.
     * @param stars     the star rating of the review.
     */
    public Review(User user, Product product, String comment, int stars) {
        super();
        this.setUser(user);
        this.setProduct(product);
        this.setComment(comment);
        this.setStars(stars);
        this.setDate(LocalDate.now()); 
    }


    /**
     * Get the id of the Review.
     * 
     * @return id of the Review.
     */
    public long getId() {
        return id;
    }


    /**
     * Get the author of the review.
     * 
     * @return user of the review.
     */
    public User getUser() {
        return user;
    }


    /**
     * Set the user of the review.
     * 
     * @param user the user of the review.
     */
    private void setUser(User user) {
        this.user = user;
    }


    /**
     * Get the product which is reviewed.
     * 
     * @return product of the review.
     */
    public Product getProduct() {
        return product;
    }


    /**
     * Set the product which is reviewed.
     * 
     * @param product the product of the review.
     */
    private void setProduct(Product product) {
        this.product = product;
    }


    /**
     * Get the comment of the Review.
     * 
     * @return comment of the Review.
     */
    public String getComment() {
        return comment;
    }


    /**
     * Set the comment of the Review.
     * 
     * @param comment the comment of the Review.
     */
    private void setComment(String comment) {
        this.comment = comment;
    }


    /**
     * Get the stars of the Review.
     * 
     * @return stars of the Review.
     */
    public int getStars() {
        return stars;
    }


    /**
     * Set the stars of the Review.
     * 
     * @param stars the stars of the Review.
     */
    private void setStars(int stars) {
        this.stars = stars;
        if (stars < 0) this.stars = 0;
        if (stars > 5) this.stars = 5;
    }


    /**
     * Get the date of the Review.
     * 
     * @return date of the Review.
     */
    public LocalDate getDate() {
        return date;
    }

    
    /**
     * Set the date of the Review.
     * 
     * @param date the date of the Review.
     */
    private void setDate(LocalDate date) {
        this.date = date;
    }
}