package yl.Auction.repository;

import org.springframework.data.repository.CrudRepository;
import yl.Auction.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
