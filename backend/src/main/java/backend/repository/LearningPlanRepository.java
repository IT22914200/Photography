package com.example.photographsystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.photographsystem.models.LearningPlan;

import java.util.List;

@Repository
public interface LearningPlanRepository extends MongoRepository<LearningPlan, String> {
    List<LearningPlan> findAll();
    List<LearningPlan> findByUser_Id(String userId);
}