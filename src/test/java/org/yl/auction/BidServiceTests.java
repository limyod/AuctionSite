package org.yl.auction;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.yl.auction.entity.Bid;
import org.yl.auction.entity.Product;
import org.yl.auction.entity.User;
import org.yl.auction.model.BidDTO;
import org.yl.auction.repository.BidRepo;
import org.yl.auction.repository.ProductRepo;
import org.yl.auction.repository.UserRepo;
import org.yl.auction.service.BidService;
import org.yl.auction.service.ProductService;
import org.yl.auction.service.UserService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class BidServiceTests {
	//test for Bid Service
	@Autowired
	private BidService bidService;

	@Autowired
	private BidRepo bidRepository;

	@Autowired

	private ProductRepo productRepo;
	@Autowired

	private UserRepo userRepo;
	@Autowired

	private ProductService productService;
	@BeforeEach
	void setUp(){

	}

	@AfterEach
	void resetEach(){
	}

	@Test
	void testFindAllBidsByUserId() {
		User user1 = new User();
		user1.setEmailAddress("test@test.com");
		userRepo.save(user1);
		User user2 = userRepo.findUserByEmailAddress("test@test.com").get();
		Product product1 = new Product();
		productRepo.save(product1);
		Bid bid = new Bid();
		bid.setAmount(new BigDecimal("100.00"));
		bid.setBidder(user1);
		bidRepository.save(bid);
		List<BidDTO> bids = bidService.findAllBidsByUserId(user2.getId());
        assertEquals(1, bids.size());
	}


}
