package org.yl.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yl.auction.security.UserPrincipal;
import org.yl.auction.service.BidService;
import org.yl.auction.service.ProductService;
import org.yl.auction.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private BidService bidService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String showMyProfile(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal){
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("bidsPlaced", bidService.findAllBidsByUserId(userPrincipal.getUser().getId()));
        model.addAttribute("productsPosted", productService.findAllProductsByUserId(userPrincipal.getUser().getId()));
        return "myProfile";
    }
}
