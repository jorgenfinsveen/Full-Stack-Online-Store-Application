package no.ntnu.mocha.DTO;

import java.time.LocalDate;

public class OrderDto {
    
    private String orderDate;
    private long userId;

    public long getUserId() { return userId; }
    public LocalDate getOrderDate() { return LocalDate.parse(orderDate); }
}
