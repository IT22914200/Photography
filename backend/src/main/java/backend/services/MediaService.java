package com.example.photographsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.photographsystem.models.PhotoPost;
import com.example.photographsystem.models.Media;
import com.example.photographsystem.repositories.PhotoPostRepository;
import com.example.photographsystem.repositories.MediaRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final PhotoPostRepository cookingPostRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository, PhotoPostRepository cookingPostRepository) {
        this.mediaRepository = mediaRepository;
        this.cookingPostRepository = cookingPostRepository;
    }

    // Get all media
    public List<Media> getAllMedia() {
        return mediaRepository.findAllByDeleteStatusFalse();
    }

    


    

  

    

    
}