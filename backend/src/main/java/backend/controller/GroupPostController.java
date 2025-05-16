package com.example.photographsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.photographsystem.dtos.GroupPostDTO;
import com.example.photographsystem.services.GroupPostService;

import java.util.List;

@RestController
@RequestMapping("/api/group-posts")
public class GroupPostController {

    private final GroupPostService groupPostService;

    @Autowired
    public GroupPostController(GroupPostService groupPostService) {
        this.groupPostService = groupPostService;
    }

    

    
    

   

    

    

    
}