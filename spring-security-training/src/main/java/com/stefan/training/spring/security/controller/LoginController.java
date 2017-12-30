package com.stefan.training.spring.security.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value="error", required=false) String error) {

        LOGGER.info("!!! Inside login - GET !!!");

        ModelAndView mv = new ModelAndView("/security/login");
        if (error != null) {
            mv.addObject("error", "true");
        }
        return mv;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "/security/logout";
    }

    @RequestMapping(path = "/access-denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "/security/access-denied";
    }



//    @RequestMapping("/error")
//    public String error(ModelMap model) {
//        model.addAttribute("username", "x");
//        model.addAttribute("password", "y");
//        model.addAttribute("error", "true");
//        System.out.println("!!!!!!!!!!!!!!Inside error");
//        return "login";
//
//    }

}
