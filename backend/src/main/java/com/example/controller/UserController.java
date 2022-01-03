package com.example.controller;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.service.RegistrationService;
import com.example.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private FishingInstructorService fishingInstructorService;


    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User user = registrationService.registerUser(userDTO);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return userResponseEntity;
    }

    @GetMapping()
    public User getLoggedInUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User)principal;
    }
}
