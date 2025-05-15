package com.example.photographsystem.dtos;

public class MediaDTO {
    private String id;
    private String type;
    private String url;
    private boolean deleteStatus;
    private String relatedPost;

    // Default constructor
    public MediaDTO() {
    }

    // Overloaded constructor
    public MediaDTO(String id, String type, String url, boolean deleteStatus, String relatedPost) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.deleteStatus = deleteStatus;
        this.relatedPost = relatedPost;
    }

    
}
