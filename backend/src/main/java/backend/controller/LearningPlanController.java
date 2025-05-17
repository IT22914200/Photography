package com.example.photographsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.photographsystem.dtos.LearningPlanDTO;
import com.example.photographsystem.models.LearningPlan;
import com.example.photographsystem.services.LearningPlanService;

import java.util.List;

@RestController
@RequestMapping("/api/learning-plan")
public class LearningPlanController {

    @Autowired
    private LearningPlanService learningPlanService;

    @PostMapping
    public ResponseEntity<LearningPlan> createLearningPlan(@RequestBody LearningPlan learningPlan) {
        try {
            LearningPlan saved = learningPlanService.addLearningPlan(learningPlan);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<LearningPlanDTO>> getAllLearningPlans() {
        try {
            List<LearningPlanDTO> dtos = learningPlanService.getAllLearningPlans();
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{planId}")
    public ResponseEntity<LearningPlan> getLearningPlanById(@PathVariable String planId) {
        try {
            LearningPlan plan = learningPlanService.getLearningPlanById(planId);
            return new ResponseEntity<>(plan, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{planId}")
    public ResponseEntity<LearningPlan> updateLearningPlan(
            @PathVariable String planId,
            @RequestBody LearningPlan updatedPlan) {
        try {
            LearningPlan updated = learningPlanService.updateLearningPlan(planId, updatedPlan);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deleteLearningPlan(@PathVariable String planId) {
        try {
            learningPlanService.deleteLearningPlan(planId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningPlanDTO>> getPlansByUserId(@PathVariable String userId) {
        try {
            List<LearningPlanDTO> dtos = learningPlanService.getPlansByUserId(userId);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}