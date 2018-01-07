package com.stefan.training.spring.boot.security;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/public/{number}")
    public String publicAccount(@PathVariable String number) {
        return "PUB-" + number;
    }

    @RequestMapping("/private/{number}")
    public String privateAccount(@PathVariable String number) {
        return "PRV-" + number;
    }
}
