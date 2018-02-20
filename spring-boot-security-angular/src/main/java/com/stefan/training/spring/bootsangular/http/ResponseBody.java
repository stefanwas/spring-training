package com.stefan.training.spring.bootsangular.http;

public class ResponseBody {
    private String code;
    private String message;

    public ResponseBody(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
