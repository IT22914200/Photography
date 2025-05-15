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
    public Feedback() {
        this.createdAt = new Date(); // Set default timestamp
    }

    // Constructor with parameters
    public Feedback(User user, String comment) {
        this.user = user;
        this.comment = comment;
        this.createdAt = new Date();
    }

    // Getters and setters

    
}