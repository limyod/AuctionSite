package yl.Auction.repository;

import org.springframework.data.repository.CrudRepository;
import yl.Auction.entity.User;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findUserByEmailAddress(String email);
}
