package com.example.assignmentsubmission.filter;

import com.example.assignmentsubmission.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtFilter {
    @Autowired
    private UserRepository userRepository;


}
