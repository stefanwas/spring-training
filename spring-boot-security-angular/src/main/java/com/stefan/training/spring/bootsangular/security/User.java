package com.stefan.training.spring.bootsangular.security;

public class User {

    private String name;
    private String password;
    private Authority[] authorities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authority[] authorities) {
        this.authorities = authorities;
    }
}
