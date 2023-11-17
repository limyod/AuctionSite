package org.yl.auction.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yl.auction.entity.Product;
import org.yl.auction.model.ProductDTO;
import org.yl.auction.repository.ProductRepo;
import org.yl.auction.Exception.ProductNotFoundException;
import org.yl.auction.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    @Autowired
    public ProductServiceImpl(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    @Override
    public void saveProduct(ProductDTO productDTO){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Product product = modelMapper.map(productDTO, Product.class);
        productRepo.save(product);
    }
    @Override
    public List<ProductDTO> getAllProducts(){
        Iterable<Product> products = productRepo.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        for(Product product: products){
            ProductDTO studentDTO = modelMapper.map(product, ProductDTO.class);
            productDTOS.add(studentDTO);
        }
        return productDTOS;
    }

    @Override
    public void createProduct(ProductDTO productDTO, User user){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Product product = modelMapper.map(productDTO, Product.class);
        product.setSeller(user);
        productRepo.save(product);
    }

    @Override
    public ProductDTO getProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("there is no product of that ID");
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(optionalProduct.get(), ProductDTO.class);
    }


}
