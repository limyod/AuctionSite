package yl.Auction.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import yl.Auction.Exception.UserExistException;
import yl.Auction.entity.User;
import yl.Auction.model.UserDTO;
public interface UserService extends UserDetailsService {
    void saveUser(UserDTO user) throws UserExistException;

    User findUserByEmailAddress(String email) throws UserExistException;

}
