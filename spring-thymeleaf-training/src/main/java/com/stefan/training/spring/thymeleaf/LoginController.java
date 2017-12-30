package com.stefan.training.spring.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("username", "u");
//        mv.addObject("password", "p");
//        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
        if (isValid(username, password)) {
            return "home";
        } else {
            return "login?error";
        }
    }

    private boolean isValid(String username, String password) {
        return username.charAt(0) == password.charAt(0);
    }


}
