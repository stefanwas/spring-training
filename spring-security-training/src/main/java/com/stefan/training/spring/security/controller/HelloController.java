package com.stefan.training.spring.security.controller;


import com.stefan.training.spring.security.model.User;
import com.stefan.training.spring.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource
    UserRepository userRepository;

    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("welcome", "This is home page!");
        mv.addObject("principal", getCurrentUser());
        mv.addObject("roles", getRoles());
        mv.setViewName("/home");
        return mv;
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView("/admin");
        mv.addObject("welcomeMessage", "Welcome to Admin page!!!");
        mv.addObject("principal", getCurrentUser());
        mv.addObject("roles", getRoles());

        List<User> users = userRepository.getAllUsers();

        LOGGER.debug("Users: {}", users);

        mv.addObject("users", users);
        return mv;
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView mv = new ModelAndView("/user");
        mv.addObject("welcomeMessage", "Welcome to User page!!!");
        mv.addObject("principal", getCurrentUser());
        mv.addObject("roles", getRoles());
        return mv;
    }

    @RequestMapping("/users")
    public ModelAndView users() {
        ModelAndView mv = new ModelAndView("/users");
        mv.addObject("welcomeMessage", "Welcome to Users page!!!");
        mv.addObject("principal", getCurrentUser());
        mv.addObject("roles", getRoles());

        List<User> users = userRepository.getAllUsers();
        mv.addObject("totalUsers", users.size());

        return mv;
    }


    private String getRoles() {
        if (isLoggedIn()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));
            return roles;
        } else {
            return "";
        }
    }

    private String getCurrentUser() {
        if (isLoggedIn()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            return username;
        } else {
            return null;
        }
    }

    private boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }


}
