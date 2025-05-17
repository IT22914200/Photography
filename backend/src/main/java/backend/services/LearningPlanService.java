package com.example.photographsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.photographsystem.dtos.LearningPlanDTO;
import com.example.photographsystem.models.LearningPlan;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.LearningPlanRepository;
import com.example.photographsystem.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LearningPlanService {

    @Autowired
    private LearningPlanRepository learningPlanRepository;

    @Autowired
    private UserRepository userRepository;

    public LearningPlan addLearningPlan(LearningPlan learningPlan) {
        try {
            return learningPlanRepository.save(learningPlan);
        } catch (Exception e) {
            throw new RuntimeException("Error saving learning plan: " + e.getMessage());
        }
    }

    public List<LearningPlanDTO> getAllLearningPlans() {
        List<LearningPlanDTO> dtos = new ArrayList<>();
        try {
            List<LearningPlan> plans = learningPlanRepository.findAll();
            for (LearningPlan plan : plans) {
                User user = plan.getUser();
                dtos.add(new LearningPlanDTO(
                        plan.getPlanId(),
                        plan.getTitle(),
                        plan.getDescription(),
                        plan.getTimeRequired(),
                        plan.getType(),
                        plan.getCreatedAt(),
                        user.getId(),
                        user.getUsername()
                ));
            }
            return dtos;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving learning plans: " + e.getMessage());
        }
    }

    public LearningPlan getLearningPlanById(String planId) {
        try {
            return learningPlanRepository.findById(planId)
                    .orElseThrow(() -> new RuntimeException("Learning plan not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving learning plan: " + e.getMessage());
        }
    }

    public LearningPlan updateLearningPlan(String planId, LearningPlan updatedPlan) {
        try {
            LearningPlan existing = getLearningPlanById(planId);

            existing.setTitle(updatedPlan.getTitle());
            existing.setDescription(updatedPlan.getDescription());
            existing.setTimeRequired(updatedPlan.getTimeRequired());
            existing.setType(updatedPlan.getType());

            return learningPlanRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error updating learning plan: " + e.getMessage());
        }
    }

    public void deleteLearningPlan(String planId) {
        try {
            learningPlanRepository.deleteById(planId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting learning plan: " + e.getMessage());
        }
    }

    public List<LearningPlanDTO> getPlansByUserId(String userId) {
        List<LearningPlanDTO> dtos = new ArrayList<>();
        try {
            List<LearningPlan> plans = learningPlanRepository.findByUser_Id(userId);
            for (LearningPlan plan : plans) {
                User user = plan.getUser();
                dtos.add(new LearningPlanDTO(
                        plan.getPlanId(),
                        plan.getTitle(),
                        plan.getDescription(),
                        plan.getTimeRequired(),
                        plan.getType(),
                        plan.getCreatedAt(),
                        user.getId(),
                        user.getUsername()
                ));
            }
            return dtos;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user's learning plans: " + e.getMessage());
        }
    }
}