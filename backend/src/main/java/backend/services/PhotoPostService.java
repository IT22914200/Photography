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

    // Create post
    public PhotoPost createPost(PhotoPost post, String userId) {
        try{
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                post.setCreatedBy(userOptional.get());
                post.setCreatedAt(new Date());
                post.setDeleteStatus(false);
                post.setLikeCount(0);
                return cookingPostRepository.save(post);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    // Update post
    public PhotoPost updatePost(String id, PhotoPost postDetails) {
        return cookingPostRepository.findById(id).map(post -> {
            post.setTitle(postDetails.getTitle());
            post.setDescription(postDetails.getDescription());
            return cookingPostRepository.save(post);
        }).orElse(null);
    }

    // Delete post (soft delete)
    public boolean deletePost(String id) {
        return cookingPostRepository.findById(id).map(post -> {
            post.setDeleteStatus(true);
            cookingPostRepository.save(post);
            return true;
        }).orElse(false);
    }

   

   
}