package yl.Auction.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yl.Auction.entity.Product;
import yl.Auction.entity.User;
import yl.Auction.model.ProductDTO;
import yl.Auction.repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;

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
        List<ProductDTO> productDTOs = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        for(Product product: products){
            ProductDTO studentDTO = modelMapper.map(product, ProductDTO.class);
            productDTOs.add(studentDTO);
        }
        return productDTOs;
    }

    @Override
    public void createProduct(ProductDTO productDTO, User user){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Product product = modelMapper.map(productDTO, Product.class);
        product.setSeller(user);
        productRepo.save(product);
    }




}
