package yl.Auction.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yl.Auction.Exception.UserExistException;
import yl.Auction.model.UserDTO;
import yl.Auction.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }
    @ModelAttribute(name="user")
    public UserDTO getUser(){
        return new UserDTO();
    }

    @GetMapping
    public String userForm(){
        return "userForm";
    }

    @PostMapping
    public String processUser(@Valid @ModelAttribute("user") UserDTO userDTO,
        BindingResult bindingResult,
        Model model){
            //check binding
            //try saving
            if(bindingResult.hasErrors()){
                return "userForm";
            }
            try {
                userService.saveUser(userDTO);
            } catch(UserExistException exception) {
                bindingResult.rejectValue("emailAddress", "email.exists", "Email already exists");
                return "userForm";
            } catch(Exception exception){

            }

            return "myProfile";
    }
}
