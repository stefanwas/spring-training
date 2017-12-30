package com.stefan.training.spring.security.ano.app;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController
{
    @RequestMapping("/")
    public ModelAndView index()
    {
        String welcomeMessage = "Hello controller Welcome Message - index.jsp";
        return new ModelAndView("index", "welcomeMessage", welcomeMessage);
    }

    @RequestMapping("/hello")
    public ModelAndView hello()
    {
        String welcomeMessage = "Hello controller Welcome Message";
        return new ModelAndView("hello", "welcomeMessage", welcomeMessage);
    }
    @RequestMapping("/admin")
    public ModelAndView admin()
    {
        String welcomeMessage = "Welcome to Admin Page !!";
        return new ModelAndView("admin", "welcomeMessage", welcomeMessage);
    }

    @RequestMapping("/error")
    public String error(ModelMap model)
    {
        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet()
    {
        System.out.println("!!!!!!!!!!!!Inside login GET");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost()
    {
        System.out.println("!!!!!!!!!!!!!!Inside login POST");
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logout()
    {
        return "logout";
    }
}
