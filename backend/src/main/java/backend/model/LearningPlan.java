package com.example.photographsystem.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "learning_plans")
public class LearningPlan {

    @Id
    private String planId;

    private String title;
    private String description;
    private int timeRequired; // e.g., in minutes or hours
    private String type; // e.g., daily, weekly, monthly

    @DBRef
    private User user;

    private Date createdAt;

    // Constructors

    public LearningPlan() {
        this.createdAt = new Date();
    }

    public LearningPlan(String title, String description, int timeRequired, String type, User user) {
        this.title = title;
        this.description = description;
        this.timeRequired = timeRequired;
        this.type = type;
        this.user = user;
        this.createdAt = new Date();
    }

    // Getters and setters

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}