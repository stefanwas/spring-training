package com.sefan.training.spring.web;

public class Message {

    private Long id;
    private String header;
    private String content;
    private String creationTime;

    public Message(Long id, String header, String content, String creationTime) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
