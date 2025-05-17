package com.example.photographsystem.repositories;

import com.example.photographsystem.controllers.ProgressUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressUpdateRepository extends MongoRepository<ProgressUpdate, String> {
    List<ProgressUpdate> findAll();
    List<ProgressUpdate> findByUser_Id(String userId);
}