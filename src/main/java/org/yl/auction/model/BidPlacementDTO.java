package org.yl.auction.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BidPlacementDTO {
    private Long id;
    private BigDecimal amount;
    private Long productId;
    private Long userId;
    private Date date;
}
