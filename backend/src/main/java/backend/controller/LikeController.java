package com.example.photographsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.photographsystem.dtos.LikeDTO;
import com.example.photographsystem.dtos.LikeStatusDTO;
import com.example.photographsystem.models.Like;
import com.example.photographsystem.services.LikeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // Get all likes
    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes() {
        List<Like> likes = likeService.getAllLikes();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Get like by ID
    @GetMapping("/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable String id) {
        Optional<Like> like = likeService.getLikeById(id);
        return like.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get likes by post ID
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Like>> getLikesByPostId(@PathVariable String postId) {
        List<Like> likes = likeService.getLikesByPostId(postId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Get likes by current user
    @GetMapping("/my-likes")
    public ResponseEntity<List<Like>> getMyLikes(@AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        List<Like> likes = likeService.getLikesByUserId(userId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Check if current user liked a post
    @GetMapping("/post/{postId}/{userId}/status")
    public ResponseEntity<LikeStatusDTO> getLikeStatus(@PathVariable String postId,
                                                 @PathVariable String userId) {

        Optional<Like> like = likeService.hasUserLikedPost(userId, postId);
        LikeStatusDTO likeStatusDTO = new LikeStatusDTO();
        if(like.isPresent()){
            likeStatusDTO.setLiked(true);
            likeStatusDTO.setLikeId(like.get().getId());
        }
        return new ResponseEntity<>(likeStatusDTO, HttpStatus.OK);
    }

    

   

    
}