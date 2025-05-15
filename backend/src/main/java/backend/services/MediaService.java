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

    // Get media by ID
    public Optional<Media> getMediaById(String id) {
        return mediaRepository.findByIdAndDeleteStatusFalse(id);
    }

    // Get media by post ID
    public List<Media> getMediaByPostId(String postId) {
        return mediaRepository.findByRelatedPostIdAndDeleteStatusFalse(postId);
    }

    // Create media (basic version without file upload)
    public Media createMedia(Media media, String postId) {
        Optional<PhotoPost> post = cookingPostRepository.findById(postId);
        if (post.isPresent()) {
            media.setRelatedPost(post.get());
            media.setDeleteStatus(false);
            return mediaRepository.save(media);
        }
        return null;
    }

    // Create media with file upload (simplified version)
    public Media createMediaWithFile(String type, MultipartFile file, String postId) throws IOException {
        Optional<PhotoPost> post = cookingPostRepository.findById(postId);
        if (post.isPresent()) {
            // In a real implementation, you would save the file to storage (S3, local filesystem, etc.)
            // and store the URL/path in the database
            String fileUrl = "https://your-storage.com/media/" + file.getOriginalFilename();

            Media media = new Media();
            media.setType(type);
            media.setUrl(fileUrl);
            media.setRelatedPost(post.get());
            media.setDeleteStatus(false);

            return mediaRepository.save(media);
        }
        return null;
    }

    

    
}