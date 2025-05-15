package com.example.photographsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.photographsystem.dtos.PhotoPostDTO;
import com.example.photographsystem.models.PhotoPost;
import com.example.photographsystem.models.User;
import com.example.photographsystem.services.PhotoPostService;
import com.example.photographsystem.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PhotoPostController {

    private final PhotoPostService cookingPostService;
    private  final UserService userService;

    @Autowired
    public PhotoPostController(PhotoPostService cookingPostService,UserService userService) {
        this.cookingPostService = cookingPostService;
        this.userService =userService;
    }

    // Get all posts
    @GetMapping
    public ResponseEntity<List<PhotoPost>> getAllPosts() {
        List<PhotoPost> posts = cookingPostService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get posts by current user
    @GetMapping("/my-posts/{userId}")
    public ResponseEntity<List<PhotoPost>> getMyPosts(@PathVariable String userId) {

        List<PhotoPost> posts = cookingPostService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PhotoPost> getPostById(@PathVariable String id) {
        Optional<PhotoPost> post = cookingPostService.getPostById(id);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

  

  
}