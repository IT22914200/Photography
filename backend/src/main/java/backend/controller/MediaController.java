package com.example.photographsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.photographsystem.dtos.MediaDTO;
import com.example.photographsystem.models.PhotoPost;
import com.example.photographsystem.models.Media;
import com.example.photographsystem.services.PhotoPostService;
import com.example.photographsystem.services.MediaService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;
    private final PhotoPostService postService;

    @Autowired
    public MediaController(MediaService mediaService,PhotoPostService postService) {
        this.mediaService = mediaService;
        this.postService =postService;
    }

    // Get all media
    @GetMapping
    public ResponseEntity<List<Media>> getAllMedia() {
        List<Media> mediaList = mediaService.getAllMedia();
        return new ResponseEntity<>(mediaList, HttpStatus.OK);
    }

    // Get media by ID
    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaById(@PathVariable String id) {
        Optional<Media> media = mediaService.getMediaById(id);
        return media.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get media by post ID
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Media>> getMediaByPostId(@PathVariable String postId) {
        List<Media> mediaList = mediaService.getMediaByPostId(postId);
        return new ResponseEntity<>(mediaList, HttpStatus.OK);
    }

   

    

   

   
}