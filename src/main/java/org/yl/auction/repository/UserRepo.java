package org.yl.auction.repository;

import org.springframework.data.repository.CrudRepository;
import org.yl.auction.entity.User;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findUserByEmailAddress(String email);
}
