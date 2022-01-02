package com.example.controller;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.service.ClientService;
import com.example.service.FishingInstructorService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private FishingInstructorService fishingInstructorService;


    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        if(userDTO.getRole() == UserRole.ROLE_CLIENT){
            user = clientService.registerClient(userDTO);
        }
        if(userDTO.getRole() == UserRole.ROLE_FISHINGI){
             user = fishingInstructorService.registerFishingInstructor(userDTO);
        }

        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return userResponseEntity;
    }

    @GetMapping()
    public User getLoggedInUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User)principal;
    }
}
