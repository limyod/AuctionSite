package yl.Auction.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yl.Auction.entity.Product;
import yl.Auction.model.ProductDTO;
import yl.Auction.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(ProductDTO productDTO){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
    }
    @Override
    public List<ProductDTO> getAllProducts(){
        Iterable<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        for(Product product: products){
            ProductDTO studentDTO = modelMapper.map(product, ProductDTO.class);
            productDTOs.add(studentDTO);
        }
        return productDTOs;
    }




}
