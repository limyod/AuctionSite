package org.yl.auction.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yl.auction.entity.Product;
import org.yl.auction.model.*;
import org.yl.auction.security.UserPrincipal;
import org.yl.auction.entity.User;
import org.yl.auction.service.BidService;
import org.yl.auction.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private BidService bidService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());

    @Autowired
    public ProductController(ProductService productService, BidService bidService){
        this.bidService = bidService;
        this.productService = productService;
    }

    @ModelAttribute("conditions")
    public Condition[] getConditions(){
        return Condition.values();
    }


    @GetMapping("/{productId}")
    public String showProductDetails(@PathVariable Long productId, Model model) {
        try {
            ProductDTO productDTO = productService.getProductById(productId);
            model.addAttribute("product", productDTO);
            List<BidDTO> bids = bidService.findAllBidsByProductId(productId);
            model.addAttribute("bids", bids);
            model.addAttribute("newBid", new BidPlacementDTO());
            return "productDetails";
        } catch (Exception exception) {
            logger.error("Error while fetching product details for productId: {}", productId, exception);
            return "error"; // You can create a custom error page
        }
    }


    @GetMapping
    public String showAllProducts(Model model){
        List<ProductDTO> productDTOList = productService.getAllProducts();
        model.addAttribute("productList", productDTOList);
        return "allProducts";
    }
    @GetMapping("/new")
    public String showProductForm(Model model){
        model.addAttribute("productForm", new ProductFormDTO());
        return "productForm";
    }
    @PostMapping("/processNewProduct")
    public String processingNewProduct(@Valid @ModelAttribute("productForm") ProductFormDTO productForm,
                                       BindingResult bindingResult,
                                       Model model, @AuthenticationPrincipal UserPrincipal userPrincipal){
        //check binding
        //try saving
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> logger.error("Binding Error: {}", error));
            return "productForm";
        }
        productService.createProduct(productForm.getName(),
                productForm.getCondition(), productForm.getDescription(),
                productForm.getAuctionDuration(), productForm.getStartingPrice(),
                userPrincipal.getUser());
        return "redirect:/products";

    }
    @PostMapping("/placeBid")
    public String placeBid(Model model, BidPlacementDTO newBid, ProductDTO product, BindingResult bindingResult, HttpServletRequest request, @AuthenticationPrincipal UserPrincipal userPrincipal){
        String referer = request.getHeader("Referer");
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> logger.error("Binding Error: {}", error));
            return "redirect:" + referer;
        }
        try {
            bidService.placeBid(newBid.getAmount(), newBid.getProductId(), userPrincipal.getUser().getId());
        } catch (Exception exception) {
            logger.error("error placing bid");

        }
        logger.info("Redirecting back to: {}", referer);
        return "redirect:" + referer;
    }


}
