package org.yl.auction.service;

import org.yl.auction.Exception.ProductNotFoundException;
import org.yl.auction.Exception.UserExistException;
import org.yl.auction.Exception.InvalidBidException;
import org.yl.auction.model.BidDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BidService {
    void placeBid(BigDecimal price, Long productId, Long userId) throws UserExistException, ProductNotFoundException, InvalidBidException;
    List<BidDTO> findAllBidsByUserId(Long userId);
    List<BidDTO> findAllBidsByProductId(Long productId);
    Optional<BidDTO> findMostRecentBid(List<BidDTO> bidDTOs);

}
