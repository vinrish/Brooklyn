package com.vinrish.brooklyn.model;


public class ApiResponse {

    private boolean result;
    private String message;
    private User user;

    public ApiResponse(boolean result, String message, User user) {
        this.result = result;
        this.message = message;
        this.user = user;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

