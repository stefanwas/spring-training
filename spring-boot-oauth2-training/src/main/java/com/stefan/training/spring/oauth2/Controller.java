package com.stefan.training.spring.oauth2;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Controller {

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
