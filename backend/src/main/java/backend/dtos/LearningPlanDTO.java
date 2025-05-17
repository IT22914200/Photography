package com.example.photographsystem.dtos;

import java.util.Date;

public class LearningPlanDTO {

    private String planId;
    private String title;
    private String description;
    private int timeRequired;
    private String type;
    private Date createdAt;

    private String userId;
    private String username;

    public LearningPlanDTO(String planId, String title, String description, int timeRequired,
                           String type, Date createdAt, String userId, String username) {
        this.planId = planId;
        this.title = title;
        this.description = description;
        this.timeRequired = timeRequired;
        this.type = type;
        this.createdAt = createdAt;
        this.userId = userId;
        this.username = username;
    }

    // Getters and Setters

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(int timeRequired) {
        this.timeRequired = timeRequired;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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