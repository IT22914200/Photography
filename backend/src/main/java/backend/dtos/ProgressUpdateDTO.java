package com.example.photographsystem.dtos;

import java.util.Date;

public class ProgressUpdateDTO {

    private String id;
    private String title;
    private String update;
    private boolean goalsAchieved;
    private Date createdAt;

    private String userId;
    private String username;

    public ProgressUpdateDTO(String id, String title, String update, boolean goalsAchieved,
                             Date createdAt, String userId, String username) {
        this.id = id;
        this.title = title;
        this.update = update;
        this.goalsAchieved = goalsAchieved;
        this.createdAt = createdAt;
        this.userId = userId;
        this.username = username;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public boolean isGoalsAchieved() {
        return goalsAchieved;
    }

    public void setGoalsAchieved(boolean goalsAchieved) {
        this.goalsAchieved = goalsAchieved;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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
}