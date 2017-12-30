package com.stefan.training.spring.security.controller;

import com.stefan.training.model.User;
import com.stefan.training.repository.UserRepository;
import com.stefan.training.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Resource
    UserRepository userRepository;

    @Resource
    PasswordService passwordService;

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView("/admin");
        mv.addObject("principal", getCurrentUser());
        mv.addObject("roles", getRoles());

        List<User> users = userRepository.getAllUsers();

        LOGGER.debug("All Users:");
        users.forEach(user -> LOGGER.debug(user.toString()));

        mv.addObject("users", users);
        return mv;
    }

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("roles") String roles) {

        String encryptedPassword = passwordService.cretePasswordHash(password);

        User newUser = new User(username, password, encryptedPassword, roles);

        userRepository.create(newUser);

        LOGGER.info("New user created: {}", newUser);

        return "forward:/admin";
    }

    @RequestMapping(path = "deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("userId") String userId) {

        int deletedUsers = userRepository.delete(userId);

        if (deletedUsers > 0) {
            LOGGER.info("User id={} deleted.", userId);
        } else {
            LOGGER.info("User id={} does not exist.", userId);

        }

        return "forward:/admin";
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
