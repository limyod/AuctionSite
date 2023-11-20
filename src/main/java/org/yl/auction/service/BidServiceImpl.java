package org.yl.auction.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yl.auction.Exception.InvalidBidException;
import org.yl.auction.Exception.ProductNotFoundException;
import org.yl.auction.Exception.UserExistException;
import org.yl.auction.entity.Bid;
import org.yl.auction.entity.Product;
import org.yl.auction.repository.BidRepo;
import org.yl.auction.repository.ProductRepo;
import org.yl.auction.repository.UserRepo;
import org.yl.auction.entity.User;
import org.yl.auction.model.BidDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService{

    private final BidRepo bidRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    @Autowired
    public BidServiceImpl(BidRepo bidRepo, ProductRepo productRepo, UserRepo userRepo){
        this.bidRepo = bidRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }
    @Override
    public void placeBid(BigDecimal price, Long productId, Long userId) throws UserExistException, ProductNotFoundException, InvalidBidException {
        Optional<User> optionalUser = userRepo.findById(userId);
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalUser.isEmpty()){
            throw new UserExistException("User does not exist");
        }
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("product does not exist");
        }
        List<BidDTO> bidDTOs = findAllBidsByProductId(productId);
        Optional<BidDTO> mostRecentBidDTOOptional = findMostRecentBid(bidDTOs);
        if(mostRecentBidDTOOptional.isEmpty() || mostRecentBidDTOOptional.get().getAmount().compareTo(price) < 0){
            Bid bid = new Bid();
            bid.setBidder(optionalUser.get());
            bid.setAmount(price);
            bid.setProduct(optionalProduct.get());
            bidRepo.save(bid);
        } else {
            throw new InvalidBidException("price is lower than the high price");
        }
    }



    @Override
    public List<BidDTO> findAllBidsByUserId(Long userId) {

        return bidRepo.findAllBidsByBidderId(userId)
                .stream()
                .map(this::convertToBidDTO)
                .toList();

    }

    @Override
    public List<BidDTO> findAllBidsByProductId(Long productId) {
        return bidRepo.findAllBidsByProductId(productId)
                .stream()
                .map(this::convertToBidDTO)
                .toList();
    }

    @Override
    public Optional<BidDTO> findMostRecentBid(List<BidDTO> bidDTOs) {
        return bidDTOs.stream()
                .max(Comparator.comparing(BidDTO::getDate));
    }

    private BidDTO convertToBidDTO(Bid bid){
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setProductId(bid.getProduct().getId());
        bidDTO.setAmount(bid.getAmount());
        bidDTO.setDate(bid.getDate());
        bidDTO.setBidderDisplayName(bid.getBidder().getDisplayName());
        bidDTO.setProductName(bid.getProduct().getName());
        return bidDTO;
    }
}
