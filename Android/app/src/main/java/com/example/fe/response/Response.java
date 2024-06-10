package com.example.fe.response;

public class Response {
    private boolean success;
    private String message, userID;

    // Constructor
    public Response (boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter untuk success
    public boolean isSuccess() {
        return success;
    }

    // Setter untuk success
    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Getter untuk message
    public String getMessage() {
        return message;
    }

    public String getUserID() {
        return userID;
    }

    // Setter untuk message
    public void setMessage(String message) {
        this.message = message;
    }
}

