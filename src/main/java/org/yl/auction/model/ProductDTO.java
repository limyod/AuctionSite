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

    private Condition condition;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    private BigDecimal startingPrice;

    private String sellerName;

    private BigDecimal currentPrice;
}


