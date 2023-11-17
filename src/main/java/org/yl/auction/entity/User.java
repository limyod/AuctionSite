package org.yl.auction.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String emailAddress;

    private String displayName;

    private String password;

    private String firstName;

    private String lastName;

    private String street;

    private String city;

    private String state;

    private String zip;

    @OneToMany(mappedBy = "seller")
    private List<Product> postedProducts;

    @OneToMany(mappedBy = "bidder")
    private List<Bid> bids;
}