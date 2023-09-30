package com.dannicleo.cielo.desafio1.exception;

import java.util.List;

public class ErrorResponse {
    private String title;
    private List<String> messages;

    public ErrorResponse(String title, List<String> messages) {
        this.title = title;
        this.messages = messages;
    }

    // getters e setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}