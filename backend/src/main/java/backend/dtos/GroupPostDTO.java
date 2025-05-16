package com.example.photographsystem.dtos;

import java.util.Date;

import com.example.photographsystem.models.Group;
import com.example.photographsystem.models.User;

public class GroupPostDTO {

    public static class GroupPostRequest {
        private String title;
        private String description;
        private String mediaUrl;
        private String groupId;

        // Getters and setters
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

        public String getMediaUrl() {
            return mediaUrl;
        }

        public void setMediaUrl(String mediaUrl) {
            this.mediaUrl = mediaUrl;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }
    }

    public static class GroupPostResponse {
        private String id;
        private String title;
        private String description;
        private String mediaUrl;
        private Date createdAt;
        private UserSummaryDTO postedBy;
        private GroupSummaryDTO postedOn;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMediaUrl() {
            return mediaUrl;
        }

        public void setMediaUrl(String mediaUrl) {
            this.mediaUrl = mediaUrl;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public UserSummaryDTO getPostedBy() {
            return postedBy;
        }

        public void setPostedBy(UserSummaryDTO postedBy) {
            this.postedBy = postedBy;
        }

        public GroupSummaryDTO getPostedOn() {
            return postedOn;
        }

        public void setPostedOn(GroupSummaryDTO postedOn) {
            this.postedOn = postedOn;
        }
    }

    

        

    

       
}