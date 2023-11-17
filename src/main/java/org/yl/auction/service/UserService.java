package org.yl.auction.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.yl.auction.Exception.UserExistException;
import org.yl.auction.entity.User;
import org.yl.auction.model.UserDTO;
public interface UserService extends UserDetailsService {
    void saveUser(UserDTO user) throws UserExistException;

    User findUserByEmailAddress(String email) throws UserExistException;

}
