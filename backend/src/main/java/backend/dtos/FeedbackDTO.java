package com.example.photographsystem.dtos;

public class FeedbackDTO {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String userId;
    private String username;
    private String comment;

    public FeedbackDTO(String id, String userId, String username, String comment) {
        this.id= id;
        this.userId = userId;
        this.username = username;
        this.comment = comment;
    }

    // Getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}