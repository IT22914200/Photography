package com.example.photographsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.photographsystem.dtos.FeedbackDTO;
import com.example.photographsystem.models.Feedback;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.FeedbackRepository;
import com.example.photographsystem.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    // Add new feedback
    public Feedback addFeedback(Feedback feedback) {
        try {
            return feedbackRepository.save(feedback);
        } catch (Exception e) {
            throw new RuntimeException("Error saving feedback: " + e.getMessage());
        }
    }

    // Get all feedbacks as FeedbackDTO list
    public List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
        try {
            List<Feedback> feedbacks = feedbackRepository.findAll();
            for (Feedback feedback : feedbacks) {
                User user = feedback.getUser();
                if (user != null) {
                    feedbackDTOs.add(new FeedbackDTO(
                            feedback.getId(),
                            user.getId(),
                            user.getUsername(),
                            feedback.getComment()
                    ));
                }
            }
            return feedbackDTOs;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving feedbacks: " + e.getMessage());
        }
    }

    // Get feedback by ID
    public Feedback getFeedbackById(String id) {
        try {
            return feedbackRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Feedback not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving feedback: " + e.getMessage());
        }
    }

    // Update existing feedback
    public Feedback updateFeedback(String id, Feedback updatedFeedback) {
        try {
            Feedback existingFeedback = getFeedbackById(id);
            existingFeedback.setComment(updatedFeedback.getComment());
            return feedbackRepository.save(existingFeedback);
        } catch (Exception e) {
            throw new RuntimeException("Error updating feedback: " + e.getMessage());
        }
    }

    // Delete feedback
    public void deleteFeedback(String id) {
        try {
            feedbackRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting feedback: " + e.getMessage());
        }
    }
}