package com.example.photographsystem.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "media")
public class Media {
    @Id
    private String id;
    private String type; // image, video, etc.
    private String url;
    private boolean deleteStatus;

    @DBRef(lazy = true)
    private PhotoPost relatedPost;

    // Default constructor
    public Media() {
    }

    // Overloaded constructor
    public Media(String id, String type, String url, boolean deleteStatus, PhotoPost relatedPost) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.deleteStatus = deleteStatus;
        this.relatedPost = relatedPost;
    }
    

}