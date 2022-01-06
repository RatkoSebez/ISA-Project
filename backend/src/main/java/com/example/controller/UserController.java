package com.example.controller;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.UserRepository;
import com.example.service.*;
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
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User user = registrationService.registerUser(userDTO);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return userResponseEntity;
    }

    @GetMapping()
    public User getLoggedInUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof String) return null;
        return (User)principal;
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<User> editUser(@RequestBody User data) {
        User user = clientService.editUser(data);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return userResponseEntity;
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteUser(@PathVariable Long id){
        return clientService.deleteUser(id);
    }

    @GetMapping(path = "confirmEmail")
    public Boolean confirmEmail(@RequestParam("code") String code){
        User user = userService.getUserForConfirmation(code);
        if(user == null) return false;
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }
}
