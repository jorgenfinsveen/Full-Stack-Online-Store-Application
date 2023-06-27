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
 * Represents a product and it's details.
 * 
 * @since   06.02.2023
 * @version 22.04.2023
 */
@Entity
@Table(name = "product")
public class Product {
    
    /** Product ID (primary key) represented as Long in the database. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "product_id")
    private long id;

    /** Image associated with the product. */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "image_id")
    private Image image;

    /** Product name. */
    @Column(name = "name")
    private String name;

    /** Product price. */
    @Column(name = "price")
    private double price;

    /** Size of the product measured in a given unit. */
    @Column(name = "amount")
    private String size;

    /** Description text for the product, represented as Text. */
    @Column(name = "description")
    private String description;

    /** Indicating wether to display the product on the website or not, represented as Boolean. */
    @Column(name = "display")
    private boolean display;

    /** Category of the product. */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pc_id")
    private ProductCategory productCategory;

    /** Number of purchases of the given product */
    @Column(name = "purchases")
    private int totalBought = 0;


    /**
     * Empty constructor.
     */
    public Product() {}


    /**
     * Creates an instance of Product.
     * 
     * @param name          the name of the product (e.g., "CofeeDockery")
     * @param price         the price of the product (measured in kr)
     * @param size          the size of the product (e.g., 25.9)
     * @param description   the description of the product (e.g., "This is a dark mocha cofee")
     * @param display       the display of the product (boolean true/false)
     * @param image         the image of the product.
     * @param category      the category of the product.
     */
    public Product(String name, double price, String size, String description, boolean display, Image image, ProductCategory category) {
        super();
        this.setName(name);
        this.setPrice(price);
        this.setSize(size);
        this.setDescription(description);
        this.setDisplay(display);
        this.setImage(image);
        this.setCategory(category);
        this.setTotalBought(0);
    }


    /**
     * Returns the id of the product.
     * 
     * @return the id of the product
     */
    public long getId() {
        return id;
    }


    /**
     * Returns the name of the product.
     * 
     * @return the name of the product
     */
    public String getName() {
        return name;
    }


    /**
     * Set the name of the product.
     * 
     * @param name the name of the product
     */
    private void setName(String name) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }


    /**
     * Returns the price of the product.
     * 
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }


    /**
     * Set the price of the product.
     * 
     * @param price the price of the product
     */
    private void setPrice(double price) {
        this.price = price;
    }


    /**
     * Returns the size of the product.
     * 
     * @return the size of the product
     */
    public String getSize() {
        return size;
    }


    /**
     * Set the size of the product.
     * 
     * @param size the size of the product
     */
    private void setSize(String size) {
        this.size = size;
    }


    /**
     * Returns the description of the product.
     * 
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }


    /**
     * Set the description of the product.
     * 
     * @param description the description of the product
     */
    private void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns the display of the product.
     * 
     * @return the display of the product
     */
    public boolean getDisplay() {
        return display;
    }


    /**
     * Set the display of the product.
     * 
     * @param display the display of the product
     */
    private void setDisplay(boolean display) {
        this.display = display;
    }


    /**
     * Returns the product category of the given
     * product.
     * 
     * @return  the product category of the given product.
     */
    public ProductCategory getCategory() {
        return productCategory;
    }


    /**
     * Sets the product category of the given product.
     * 
     * @param productCategory   the product category of the
     *                          given product.
     */
    private void setCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }


    /**
     * Get the number of times the product has been bought.
     * 
     * @param number of purchases.
     */
    public int getTotalBought() {
        return totalBought;
    }


    /**
     * Set the number of purchases for the product.
     * 
     * @param totalBought the number of purchases.
     */
    private void setTotalBought(int totalBought) {
        this.totalBought = totalBought;
    }


    /**
     * Get the Image to display with the product.
     * 
     * @return image the image of the product.
     */
    public Image getImage() {
        return image;
    }


    /**
     * Set the Image to display with the product.
     * 
     * @param image the image of the product.
     */
    private void setImage(Image image) {
        this.image = image;
    }
}
