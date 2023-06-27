package no.ntnu.mocha.DTO;

import java.time.LocalDate;

public class ReviewDto {
    
    private long userId;
    private long productId;
    private int stars;
    private String comment;
    private String date;

    public long getUserId() { return userId; }
    public long getProductId() { return productId; }
    public String getComment() { return comment; }
    public int getStars() { return stars; }
    public LocalDate getDate() { return LocalDate.parse(date); }
}
