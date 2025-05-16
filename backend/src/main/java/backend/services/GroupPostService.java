package com.example.photographsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.photographsystem.dtos.GroupPostDTO;
import com.example.photographsystem.exceptions.ResourceNotFoundException;
import com.example.photographsystem.models.Group;
import com.example.photographsystem.models.GroupPost;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.GroupPostRepository;
import com.example.photographsystem.repositories.GroupRepository;
import com.example.photographsystem.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupPostService {

    private final GroupPostRepository groupPostRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupPostService(GroupPostRepository groupPostRepository,
                            UserRepository userRepository,
                            GroupRepository groupRepository) {
        this.groupPostRepository = groupPostRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    /**
     * Get all group posts (active only)
     */
    public List<GroupPostDTO.GroupPostResponse> getAllGroupPosts() {
        List<GroupPost> posts = groupPostRepository.findByDeleteStatusFalseOrderByCreatedAtDesc();
        return mapPostsToResponseDTOs(posts);
    }

    

    

    
     
       
       

   
        

        
       

    
        

       

    
}