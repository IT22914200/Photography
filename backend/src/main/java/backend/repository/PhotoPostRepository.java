package com.example.photographsystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.photographsystem.models.PhotoPost;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoPostRepository extends MongoRepository<PhotoPost, String> {
    List<PhotoPost> findAllByDeleteStatusFalse();
    List<PhotoPost> findByCreatedByIdAndDeleteStatusFalse(String userId);
    Optional<PhotoPost> findByIdAndDeleteStatusFalse(String id);
}