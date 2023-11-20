package org.yl.auction.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.yl.auction.entity.Bid;
import org.yl.auction.entity.Product;
import org.yl.auction.model.Condition;
import org.yl.auction.model.ProductDTO;
import org.yl.auction.model.ProductFormDTO;
import org.yl.auction.repository.ProductRepo;
import org.yl.auction.Exception.ProductNotFoundException;
import org.yl.auction.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void saveProduct(ProductDTO productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Product product = modelMapper.map(productDTO, Product.class);
        productRepo.save(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        Iterable<Product> products = productRepo.findAll();
        return StreamSupport.stream(products.spliterator(), false)
                .map(this::toProductDto).collect(Collectors.toList());
    }

    @Override
    public void createProduct(String name, Condition condition, String description, int auctionDuration,
                              BigDecimal startingPrice, User user) {
        Product product = new Product();
        product.setName(name);
        product.setCondition(condition);
        product.setDescription(description);
        product.setAuctionDuration(auctionDuration);
        product.setStartingPrice(startingPrice);
        product.setSeller(user);
        productRepo.save(product);
    }

    @Override
    public ProductDTO getProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("there is no product of that ID");
        }
        return toProductDto(optionalProduct.get());
    }

    @Override
    public void deleteProductById(Long productId) throws ProductNotFoundException {
        if (!productRepo.existsById(productId)) {
            throw new ProductNotFoundException("the productid does not exist");
        }
        productRepo.deleteById(productId);
    }

    @Override
    public List<ProductDTO> findAllProductsByUserId(Long userId) {
        Iterable<Product> products = productRepo.findAllProductsBySellerId(userId);
        return StreamSupport.stream(products.spliterator(), false)
                .map(this::toProductDto).collect(Collectors.toList());
    }

    private ProductDTO toProductDto(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setCondition(product.getCondition());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setStartDate(product.getStartDate());
        //calculating end date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(product.getStartDate());
        calendar.add(Calendar.DAY_OF_MONTH, product.getAuctionDuration());
        productDTO.setEndDate(calendar.getTime());
        productDTO.setSellerName(product.getSeller().getDisplayName());
        productDTO.setStartingPrice(product.getStartingPrice());
        List<Bid> bids = product.getBids();
        if(bids == null || bids.isEmpty()){
            productDTO.setCurrentPrice(null);// lets just say there are no current bids
        } else {
            BigDecimal max = bids.stream().map(Bid::getAmount)
                    .max(BigDecimal::compareTo).orElseThrow();
            productDTO.setCurrentPrice(max);
        }
        return productDTO;
    }



}
