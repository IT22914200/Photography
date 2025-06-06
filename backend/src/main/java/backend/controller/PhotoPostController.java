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

    // Create new post
    @PostMapping
    public ResponseEntity<PhotoPost> createPost(@RequestBody PhotoPostDTO postDto
                                                 ) {
        String userId = postDto.getCreatedBy();
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()){
            PhotoPost post = new PhotoPost();
            post.setCreatedBy(user.get());
            post.setDescription(postDto.getDescription());
            post.setTitle(postDto.getTitle());
            post.setCreatedAt(postDto.getCreatedAt());
            post.setLikeCount(postDto.getLikeCount());
            post.setDeleteStatus(false);
            PhotoPost createdPost = cookingPostService.createPost(post, userId);
            if (createdPost != null) {
                return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Update post
    @PutMapping("/{id}")
    public ResponseEntity<PhotoPost> updatePost(@PathVariable String id,
                                                  @RequestBody PhotoPostDTO postDetailsDTO) {
        Optional<User> user = userService.getUserById(postDetailsDTO.getCreatedBy());
        if(user.isPresent()){
            PhotoPost post  =new PhotoPost();
            post.setCreatedBy(user.get());
            post.setDescription(postDetailsDTO.getDescription());
            post.setTitle(postDetailsDTO.getTitle());
            post.setCreatedAt(postDetailsDTO.getCreatedAt());
            post.setLikeCount(postDetailsDTO.getLikeCount());
            post.setDeleteStatus(false);
            PhotoPost updatedPost = cookingPostService.updatePost(id, post);
            if (updatedPost != null) {
                return new ResponseEntity<>(updatedPost, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        if (cookingPostService.deletePost(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  
}