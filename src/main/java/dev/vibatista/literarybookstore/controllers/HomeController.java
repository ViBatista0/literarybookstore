package dev.vibatista.literarybookstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Seja bem vindo ao Literary Bookstore!";
    }

}
