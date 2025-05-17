package com.example.photographsystem.controllers;

import com.example.photographsystem.dtos.ProgressUpdateDTO;
import com.example.photographsystem.services.ProgressUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressUpdateController {

    @Autowired
    private ProgressUpdateService progressUpdateService;

    // Create a new progress update
    @PostMapping
    public ResponseEntity<ProgressUpdate> createProgressUpdate(@RequestBody ProgressUpdate progressUpdate) {
        try {
            ProgressUpdate saved = progressUpdateService.addProgressUpdate(progressUpdate);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all progress updates
    @GetMapping
    public ResponseEntity<List<ProgressUpdateDTO>> getAllProgressUpdates() {
        try {
            List<ProgressUpdateDTO> updates = progressUpdateService.getAllProgressUpdates();
            return new ResponseEntity<>(updates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProgressUpdate> getProgressUpdateById(@PathVariable String id) {
        try {
            ProgressUpdate update = progressUpdateService.getProgressUpdateById(id);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update by ID
    @PutMapping("/{id}")
    public ResponseEntity<ProgressUpdate> updateProgressUpdate(
            @PathVariable String id,
            @RequestBody ProgressUpdate updatedProgress) {
        try {
            ProgressUpdate updated = progressUpdateService.updateProgressUpdate(id, updatedProgress);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressUpdate(@PathVariable String id) {
        try {
            progressUpdateService.deleteProgressUpdate(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProgressUpdateDTO>> getProgressUpdatesByUserId(@PathVariable String userId) {
        try {
            List<ProgressUpdateDTO> updates = progressUpdateService.getProgressUpdatesByUserId(userId);
            return new ResponseEntity<>(updates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}