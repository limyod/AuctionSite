package org.yl.auction.entity;

import jakarta.persistence.*;
import lombok.*;
import org.yl.auction.model.Condition;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="product_seller", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User seller;

    @OneToMany(mappedBy = "product")
    private List<Bid> bids;

    @PrePersist
    void startDate() {
        this.startDate = new Date();
    }

}
