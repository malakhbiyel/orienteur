package com.example.orienteur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String getHelloWorld(){
        return "Bienvenu : Hello world";
    }

    @GetMapping("/home")
    public String getHomePage(){
        return "home";
    }
}
