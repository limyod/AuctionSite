package yl.Auction.repository;

import org.springframework.data.repository.CrudRepository;
import yl.Auction.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
