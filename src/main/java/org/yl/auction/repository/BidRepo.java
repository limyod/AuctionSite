package org.yl.auction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yl.auction.entity.Bid;

import java.util.List;

@Repository
public interface BidRepo extends CrudRepository<Bid, Long> {
    public List<Bid> findAllBidsByProductId(Long productId);
    public List<Bid> findAllBidsByBidderId(Long UserId);


}
