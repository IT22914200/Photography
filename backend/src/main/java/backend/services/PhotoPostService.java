package com.example.photographsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.photographsystem.models.PhotoPost;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.PhotoPostRepository;
import com.example.photographsystem.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoPostService {

    private final PhotoPostRepository cookingPostRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhotoPostService(PhotoPostRepository cookingPostRepository, UserRepository userRepository) {
        this.cookingPostRepository = cookingPostRepository;
        this.userRepository = userRepository;
    }

    // Get all posts
    public List<PhotoPost> getAllPosts() {
        return cookingPostRepository.findAllByDeleteStatusFalse();
    }

    // Get posts by user
    public List<PhotoPost> getPostsByUser(String userId) {
        return cookingPostRepository.findByCreatedByIdAndDeleteStatusFalse(userId);
    }

    // Get post by ID
    public Optional<PhotoPost> getPostById(String id) {
        return cookingPostRepository.findByIdAndDeleteStatusFalse(id);
    }


    

    

   

   
}