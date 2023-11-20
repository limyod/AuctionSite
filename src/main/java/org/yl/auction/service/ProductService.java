package org.yl.auction.service;

import org.yl.auction.model.Condition;
import org.yl.auction.model.ProductDTO;
import org.yl.auction.Exception.ProductNotFoundException;
import org.yl.auction.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    void saveProduct(ProductDTO productDTO);
    void createProduct(String name, Condition condition, String description, int auctionDuration, BigDecimal startingPrice, User user);

    ProductDTO getProductById(Long productId) throws ProductNotFoundException;

    void deleteProductById(Long productId) throws ProductNotFoundException;

    List<ProductDTO> findAllProductsByUserId(Long userId);
}
