package yl.Auction.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import yl.Auction.model.Condition;
import yl.Auction.model.ProductDTO;
import yl.Auction.security.UserPrincipal;
import yl.Auction.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @ModelAttribute(name="product")
    public ProductDTO product(){
        return new ProductDTO();
    }

    @ModelAttribute("conditions")
    public Condition[] getConditions(){
        return Condition.values();
    }

    @ModelAttribute("product")
    public ProductDTO getProduct(){
        return new ProductDTO();
    }

    @GetMapping
    public String showAllProducts(Model model){
        List<ProductDTO> productDTOList = productService.getAllProducts();
        model.addAttribute("productList", productDTOList);
        return "allProducts";
    }
    @GetMapping("/new")
    public String showProductForm(Model model){
        return "productForm";
    }
    @PostMapping("/process-new-product")
    public String processingNewProduct(@Valid @ModelAttribute("product") ProductDTO productDTO,
                                       BindingResult bindingResult,
                                       Model model, @AuthenticationPrincipal UserPrincipal userPrincipal){
        //check binding
        //try saving
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> logger.error("Binding Error: {}", error));
            return "productForm";
        }
        productService.createProduct(productDTO, userPrincipal.getUser());
        return "redirect:/products";

    }
//    @PostMapping("/process-new-product")
//    public String processingNewProduct(@Valid @ModelAttribute("product") ProductDTO productDTO, @AuthenticationPrincipal UserPrincipal userPrincipal,
//                                       BindingResult bindingResult,
//                                       Model model){
//        //check binding
//        //try saving
//        if(bindingResult.hasErrors()){
//            bindingResult.getAllErrors().forEach(error -> logger.error("Binding Error: {}", error));
//            return "productForm";
//        }
//        productService.createProduct(productDTO, userPrincipal.getUser());
//        return "redirect:/products";
//    }


}
