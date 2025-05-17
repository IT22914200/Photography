package com.example.photographsystem.services;

import com.example.photographsystem.controllers.ProgressUpdate;
import com.example.photographsystem.dtos.ProgressUpdateDTO;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.ProgressUpdateRepository;
import com.example.photographsystem.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressUpdateService {

    @Autowired
    private ProgressUpdateRepository progressUpdateRepository;

    @Autowired
    private UserRepository userRepository;

    // Add new progress update
    public ProgressUpdate addProgressUpdate(ProgressUpdate progressUpdate) {
        try {
            return progressUpdateRepository.save(progressUpdate);
        } catch (Exception e) {
            throw new RuntimeException("Error saving progress update: " + e.getMessage());
        }
    }

    // Get all progress updates with user info in DTO
    public List<ProgressUpdateDTO> getAllProgressUpdates() {
        List<ProgressUpdateDTO> dtos = new ArrayList<>();
        try {
            List<ProgressUpdate> updates = progressUpdateRepository.findAll();
            for (ProgressUpdate update : updates) {
                User user = update.getUser();
                dtos.add(new ProgressUpdateDTO(
                        update.getId(),
                        update.getTitle(),
                        update.getUpdate(),
                        update.isGoalsAchieved(),
                        update.getCreatedAt(),
                        user.getId(),
                        user.getUsername()
                ));
            }
            return dtos;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving progress updates: " + e.getMessage());
        }
    }

    // Get by ID
    public ProgressUpdate getProgressUpdateById(String id) {
        try {
            return progressUpdateRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Progress update not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving progress update: " + e.getMessage());
        }
    }

    // Update existing progress update
    public ProgressUpdate updateProgressUpdate(String id, ProgressUpdate updatedProgress) {
        try {
            ProgressUpdate existing = getProgressUpdateById(id);

            existing.setTitle(updatedProgress.getTitle());
            existing.setUpdate(updatedProgress.getUpdate());
            existing.setGoalsAchieved(updatedProgress.isGoalsAchieved());

            return progressUpdateRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error updating progress update: " + e.getMessage());
        }
    }

    // Delete by ID
    public void deleteProgressUpdate(String id) {
        try {
            progressUpdateRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting progress update: " + e.getMessage());
        }
    }

    // Get all by user ID
    public List<ProgressUpdateDTO> getProgressUpdatesByUserId(String userId) {
        List<ProgressUpdateDTO> dtos = new ArrayList<>();
        try {
            List<ProgressUpdate> updates = progressUpdateRepository.findByUser_Id(userId);
            for (ProgressUpdate update : updates) {
                User user = update.getUser();
                dtos.add(new ProgressUpdateDTO(
                        update.getId(),
                        update.getTitle(),
                        update.getUpdate(),
                        update.isGoalsAchieved(),
                        update.getCreatedAt(),
                        user.getId(),
                        user.getUsername()
                ));
            }
            return dtos;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user progress updates: " + e.getMessage());
        }
    }
}