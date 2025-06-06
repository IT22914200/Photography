package com.example.photographsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.photographsystem.dtos.LikeStatusDTO;
import com.example.photographsystem.models.PhotoPost;
import com.example.photographsystem.models.Like;
import com.example.photographsystem.models.Notification;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.PhotoPostRepository;
import com.example.photographsystem.repositories.LikeRepository;
import com.example.photographsystem.repositories.UserRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PhotoPostRepository cookingPostRepository;
    private final PhotoPostService cookingPostService;

    private final NotificationService notificationService;

    @Autowired
    public LikeService(LikeRepository likeRepository,
                       UserRepository userRepository,
                       PhotoPostRepository cookingPostRepository,
                       PhotoPostService cookingPostService,NotificationService notificationService) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.cookingPostRepository = cookingPostRepository;
        this.cookingPostService = cookingPostService;
        this.notificationService = notificationService;
    }

    // Get all likes
    public List<Like> getAllLikes() {
        return likeRepository.findAllByDeleteStatusFalse();
    }

    // Get like by ID
    public Optional<Like> getLikeById(String id) {
        return likeRepository.findByIdAndDeleteStatusFalse(id);
    }

    // Get likes by post ID
    public List<Like> getLikesByPostId(String postId) {
        return likeRepository.findByLikedPostIdAndDeleteStatusFalse(postId);
    }

    // Get likes by user ID
    public List<Like> getLikesByUserId(String userId) {
        List<Like> likes = likeRepository.findByLikedByIdAndDeleteStatusFalse(userId);
        Collections.reverse(likes);
        return likes;
    }

    // Check if user liked a post
    public Optional<Like> hasUserLikedPost(String userId, String postId) {
        return likeRepository.findByLikedByIdAndLikedPostIdAndDeleteStatusFalse(userId, postId);
    }

    // Create like
    public Like createLike(String postId, String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<PhotoPost> postOptional = cookingPostRepository.findById(postId);

        if (userOptional.isPresent() && postOptional.isPresent()) {
            // Check if like already exists
            if (hasUserLikedPost(userId, postId).isPresent() ) {
                return null;
            }

            Like like = new Like();
            like.setLikedAt(new Date());
            like.setLikedBy(userOptional.get());
            like.setLikedPost(postOptional.get());
            like.setDeleteStatus(false);

            // Increment like count on post
            cookingPostService.incrementLikeCount(postId);
            Notification notification = new Notification();
            notification.setDeleteStatus(false);
            notification.setReceiver(like.getLikedPost().getCreatedBy());
            notification.setTitle("You have a new like");
            notification.setSubtitle(like.getLikedBy().getName() + " liked to your post");
            notificationService.createNotification(notification,like.getLikedPost().getCreatedBy().getId());

            return likeRepository.save(like);
        }
        return null;
    }

    // Unlike (soft delete)
    public boolean unlike(String userId, String postId) {
        Optional<Like> likeOptional = likeRepository.findByLikedByIdAndLikedPostIdAndDeleteStatusFalse(userId, postId);
        if (likeOptional.isPresent()) {
            Like like = likeOptional.get();
            like.setDeleteStatus(true);
            likeRepository.save(like);

            // Decrement like count on post
            cookingPostService.decrementLikeCount(postId);
            return true;
        }
        return false;
    }

    // Delete like (admin function)
    public boolean deleteLike(String id) {
        return likeRepository.findById(id).map(like -> {
            like.setDeleteStatus(true);
            likeRepository.save(like);

            // Decrement like count on post
            cookingPostService.decrementLikeCount(like.getLikedPost().getId());
            return true;
        }).orElse(false);
    }
}