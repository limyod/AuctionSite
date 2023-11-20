package org.yl.auction.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BidDTO {
        private long id;
        private BigDecimal amount;
        private Date date;
        private String bidderDisplayName; // Assuming you want to display the username of the bidder
        private String productName; // Assuming you want to display the name of the product
        private long productId;
        // Constructors, getters, and setters can be added as needed
}
