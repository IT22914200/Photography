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

    

    

    
              

   

    

    

    
}