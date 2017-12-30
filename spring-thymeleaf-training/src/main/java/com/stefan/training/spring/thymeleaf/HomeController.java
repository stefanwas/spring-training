package com.stefan.training.spring.thymeleaf;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
public class HomeController {

    List<Book> books = new ArrayList<>();

    public HomeController() {
        Book b1 = new Book("Standard", "William Sheakspire", 12.50, null);
        books.add(b1);
    }

    @RequestMapping(value = "/")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        model.addObject("welcome", "This is welcome page!ABC");
        model.setViewName("/home");
        return model;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView books() {
        ModelAndView model = new ModelAndView();
        model.addObject("books", books);
        model.setViewName("/books");
        return model;
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public ModelAndView addBook() {
        ModelAndView model = new ModelAndView();
        Book b = new Book("T", "A", 1.0, new Date());
        model.addObject("newBook", b);
        model.setViewName("/addbook");
        return model;
    }

    @RequestMapping(value = "/addNewbook", method = RequestMethod.POST)
    public ModelAndView addNewBook(@ModelAttribute("newBook") Book book) {
        if (book != null) {
            this.books.add(book);
            System.out.println("Book added");
        } else {
            System.out.println("Book == NULL");
        }
//        ModelAndView model = new ModelAndView();
//        model.setViewName("/books");
        return new ModelAndView("redirect:/books");
    }


    @ModelAttribute("global")
    public String welcome() {
        return "Wojtek 123";
    }

    private List<Book> getBooks() {
        Book b1 = new Book("Romeo and Julia", "William Sheakspire", 12.50, null);
        Book b2 = new Book("Wojna i pokoj", "Tolstoj", 100.00, null);
        Book b3 = new Book("Faraon", "Prus", 8.50, null);

        return Arrays.asList(b1, b2, b3);
    }

}
