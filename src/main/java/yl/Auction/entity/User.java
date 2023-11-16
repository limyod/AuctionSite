package yl.Auction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import yl.Auction.validation.ValidPassword;

import java.util.Set;

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
    private Set<Product> postedProducts;
}