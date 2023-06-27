package no.ntnu.mocha.DTO;

/**
 * <h1>Cart Item DTO (Data Transfer Object)</h1>
 * 
 * <p>Representing an Data Transfer Object class for
 * Cart Item so that we can share either required data
 * or complete data from the source.</p>
 * 
 * @version 22.03.2023
 * @since   22.03.2023
 */
public class CartItemDto {
    
    private long productId;
    private long orderId;
    private int amount;

    public Long getProductId() { return productId; }
    public Long getOrderId() { return orderId; }
    public Integer getAmount() { return amount; }
}
