package com.example.photographsystem.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "likes")
public class Like {
    @Id
    private String id;
    private Date likedAt;
    private boolean deleteStatus;

    @DBRef(lazy = true)
    @JsonSerialize(using = UserSerializer.class)
    private User likedBy;

    @DBRef(lazy = true)
    private PhotoPost likedPost;

    // Default constructor
    public Like() {
    }

    // Overloaded constructor
    public Like(String id, Date likedAt, boolean deleteStatus, User likedBy, PhotoPost likedPost) {
        this.id = id;
        this.likedAt = likedAt;
        this.deleteStatus = deleteStatus;
        this.likedBy = likedBy;
        this.likedPost = likedPost;
    }

    
}
