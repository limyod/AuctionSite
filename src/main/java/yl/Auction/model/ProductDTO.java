package yl.Auction.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import yl.Auction.entity.Product;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDTO {
    private long id;

    @NotNull(message = "Condition must be chosen")
    private Condition condition;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    private Date startDate;

    @Positive(message = "Auction duration must be a positive number")
    private int auctionDuration;

    @NotNull(message = "price cannot be null")
    @DecimalMin(value = "0.01", message = "Starting price must be greater than or equal to 0.01")
    private BigDecimal startingPrice;
}


