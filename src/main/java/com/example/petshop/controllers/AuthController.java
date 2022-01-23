package com.example.petshop.controllers;


import com.example.petshop.dao.RoleRepository;
import com.example.petshop.dao.UserRepository;
import com.example.petshop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;



    @ModelAttribute("user")
    public User user(){
        return new User();
    }


    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }


    @GetMapping("/register")
    public String registerPage(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult userBindingResult){

        if( userBindingResult.hasErrors()){

            return "auth/register";
        }else{


            userRepository.save(user);
            return "redirect:/";
        }

    }
}
