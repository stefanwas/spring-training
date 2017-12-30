package com.stefan.training.spring.thymeleaf;

import java.util.Date;

public class Book {
    String title;
    String author;
    Double price;
    Date date;

    public Book() {
    }

    public Book(String title, String author, double price, Date date) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.date = date;
    }

    public String random() {
        return "ABC";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
