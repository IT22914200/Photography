package com.example.photographsystem.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "feedbacks")
public class Feedback {

    @Id
    private String id;

    @DBRef
    private User user; // Reference to the User who provided the feedback

    private String comment; // The actual feedback message

    private Date createdAt; // When the feedback was submitted

    // Default constructor
    

    // Constructor with parameters
    public Feedback(User user, String comment) {
        this.user = user;
        this.comment = comment;
        this.createdAt = new Date();
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}