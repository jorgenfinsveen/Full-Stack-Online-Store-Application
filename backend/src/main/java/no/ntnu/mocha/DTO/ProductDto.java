package no.ntnu.mocha.DTO;

/**
 * <h1>Product DTO (Data Transfer Object)</h1>
 * 
 * <p>Representing an Data Transfer Object class for
 * Product so that we can share either required data
 * or complete data from the source.</p>
 * 
 * @version 22.03.2023
 * @since   22.03.2023
 */
public class ProductDto {

    private long imageId;
    private String name;
    private String amount;
    private String category;
    private String description;
    private double price;
    private int totalBought;
    private boolean display;

    
    /**
     * Returns the image id associated with the product.
     * 
     * @return the image id associated with the product.
     */
    public long getImageId() {
        return imageId;
    }

    /**
     * Returns the name of the product.
     * 
     * @return the name of the product.
     */
    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * Returns the price of the product.
     * 
     * @return the price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the size of the product.
     * 
     * @return the size of the product.
     */
    public String getAmount() {
        return amount.substring(0, 1).toUpperCase() + amount.substring(1);
    }

    /**
     * Returns the description of the product.
     * 
     * @return the description of the product.
     */
    public String getDescription() {
        return description.substring(0, 1).toUpperCase() + description.substring(1);
    }

    /**
     * Returns the category of the product.
     * 
     * @return  the category of the product.
     */
    public String getCategory() {
        return category.toUpperCase();
    }


    public boolean getDisplay() { return display; }
    public int getTotalBought() { return totalBought; }
}
