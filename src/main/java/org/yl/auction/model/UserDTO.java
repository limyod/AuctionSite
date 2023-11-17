package org.yl.auction.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yl.auction.validation.ValidPassword;

@Data
@NoArgsConstructor(force = true)
public class UserDTO {
    private long id;
    @Email(message = "Please provide valid email")
    private String emailAddress;
    @NotEmpty(message = "Please provide a display name")
    private String displayName;
    @ValidPassword
    private String password;
    @NotEmpty(message = "Please provide a first name")
    private String firstName;
    @NotEmpty(message = "please provide a last name")
    private String lastName;
    @NotEmpty(message = "Please provide a Street")
    private String street;
    @NotEmpty(message = "Please provide a city")
    private String city;
    @NotEmpty(message = "Please provide a state")
    private String state;
    @NotEmpty(message = "Please provide a zip")
    private String zip;
}
