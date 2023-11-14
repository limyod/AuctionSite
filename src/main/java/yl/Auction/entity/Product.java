package yl.Auction.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import yl.Auction.model.Condition;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(name="Product_Condition")
    @Enumerated(EnumType.STRING)
    private Condition condition;
    private String name;
    private String description;
    private Date startDate;
    private int auctionDuration;
    private BigDecimal startingPrice;

    @PrePersist
    void startDate() {
        this.startDate = new Date();
    }

}
