package org.yl.auction.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private BigDecimal amount;
    private Date date;

    @ManyToOne
    @JoinTable(name = "bid_bidder", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="bid_id"))
    private User bidder;

    @ManyToOne
    @JoinTable(name = "bid_product", joinColumns=@JoinColumn(name="product_id"), inverseJoinColumns=@JoinColumn(name="bid_id"))
    private Product product;

    @PrePersist
    void startDate() {
        this.date = new Date();
    }

}
