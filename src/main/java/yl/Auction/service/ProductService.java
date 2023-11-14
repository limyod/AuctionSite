package yl.Auction.service;

import yl.Auction.entity.Product;
import yl.Auction.model.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    void saveProduct(ProductDTO productDTO);

}
