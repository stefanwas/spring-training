package com.stefan.training.spring.bootsangular;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ApplicationController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }


}
