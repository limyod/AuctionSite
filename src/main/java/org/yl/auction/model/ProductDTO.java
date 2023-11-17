package org.yl.auction.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//For when the user creates a new Product using forms
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

    private UserDTO seller;

    private List<BidDTO> bids;


}


