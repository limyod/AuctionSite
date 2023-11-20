package org.yl.auction.repository;

import org.springframework.data.repository.CrudRepository;
import org.yl.auction.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
    Iterable<Product> findAllProductsBySellerId(Long sellerId);
}
