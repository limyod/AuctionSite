package org.yl.auction.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yl.auction.security.UserPrincipal;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    public String showMyProfile(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal){
        model.addAttribute("user", userPrincipal.getUser());
        return "myProfile";
    }
}
