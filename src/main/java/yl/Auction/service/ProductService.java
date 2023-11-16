package yl.Auction.service;

import yl.Auction.Exception.ProductNotFoundException;
import yl.Auction.entity.Product;
import yl.Auction.entity.User;
import yl.Auction.model.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    void saveProduct(ProductDTO productDTO);
    void createProduct(ProductDTO productDTO, User user);

    ProductDTO getProductById(Long productId) throws ProductNotFoundException;
}
