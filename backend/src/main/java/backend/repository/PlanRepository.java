package com.example.photographsystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.photographsystem.models.Plan;

import java.util.List;

@Repository
public interface PlanRepository extends MongoRepository<Plan, String> {
    // Find all plans by user id
    List<Plan> findByUserId(String userId);

    // Find all public plans
    List<Plan> findByIsPublicTrue();

    // Find public plans by user id
    List<Plan> findByUserIdAndIsPublicTrue(String userId);

    // Find plans by title containing keyword
    List<Plan> findByTitleContainingIgnoreCase(String keyword);
}