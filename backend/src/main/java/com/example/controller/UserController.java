package com.example.controller;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.service.ClientService;
import com.example.service.RegistrationService;
import com.example.service.FishingInstructorService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ClientService clientService;
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
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        //System.out.println("user: " + name);
        //if(name.equals("")) return new User();
        //return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(principal instanceof String) return null;
        //System.out.println(principal.getClass());
        return (User)principal;
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<User> editUser(@RequestBody User data) {
        User user = clientService.editUser(data);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return userResponseEntity;
    }
}
