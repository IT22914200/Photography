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

   

   

    

   

   
}