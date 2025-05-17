package com.example.photographsystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.photographsystem.models.LearningProgress;
import com.example.photographsystem.models.User;

import java.util.List;

public interface LearningProgressRepository extends MongoRepository<LearningProgress, String> {
    List<LearningProgress> findByUser(User user);
    List<LearningProgress> findByUserAndIsArchived(User user, boolean isArchived);
}