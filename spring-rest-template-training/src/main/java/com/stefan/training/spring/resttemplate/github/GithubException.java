package com.stefan.training.spring.resttemplate.github;

import org.springframework.http.HttpStatus;

public class GithubException extends RuntimeException {
    private HttpStatus httpStatus;

    public GithubException(HttpStatus httpResponseCode, String message) {
        super(message);
        this.httpStatus = httpResponseCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
