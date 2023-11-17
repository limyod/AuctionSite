package org.yl.auction.service;

import org.yl.auction.model.ProductDTO;
import org.yl.auction.Exception.ProductNotFoundException;
import org.yl.auction.entity.User;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    void saveProduct(ProductDTO productDTO);
    void createProduct(ProductDTO productDTO, User user);

    ProductDTO getProductById(Long productId) throws ProductNotFoundException;
}
