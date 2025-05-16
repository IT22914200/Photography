package com.example.photographsystem.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "group_posts")
public class GroupPost {
    @Id
    private String id;
    private String title;
    private String description;
    private String mediaUrl;
    private boolean deleteStatus;
    private Date createdAt;

    @DBRef(lazy = true)
    private User postedBy;

    @DBRef(lazy = true)
    private Group postedOn;

    // Default constructor
    public GroupPost() {
        this.createdAt = new Date();
    }

    // Overloaded constructor
    public GroupPost(String id, String title, String description, String mediaUrl,
                     boolean deleteStatus, User postedBy, Group postedOn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mediaUrl = mediaUrl;
        this.deleteStatus = deleteStatus;
        this.postedBy = postedBy;
        this.postedOn = postedOn;
        this.createdAt = new Date();
    }

}