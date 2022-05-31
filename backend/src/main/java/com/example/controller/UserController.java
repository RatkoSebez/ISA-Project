package com.example.controller;

import com.example.dto.UserDTO;
import com.example.model.DeleteAccountRequest;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.model.UserSubscription;
import com.example.repository.UserRepository;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

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
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) throws MessagingException {
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

    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<User> editUser(@RequestBody User data) {
        User user = clientService.editUser(data);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return userResponseEntity;
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/deleteClientRequest")
    public Boolean deleteUser(@RequestBody DeleteAccountRequest deleteAccountRequest){
        return clientService.deleteUser(deleteAccountRequest);
    }

    @GetMapping(path = "confirmEmail")
    public Boolean confirmEmail(@RequestParam("code") String code){
        User user = userService.getUserForConfirmation(code);
        if(user == null) return false;
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/subscribe")
    public Boolean subscribe(@RequestBody UserSubscription data) {
        return userService.subscribe(data);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(path = "subscriptions")
    public List<UserSubscription> getAllSubscriptions(){
        return userService.getAllSubscriptions();
    }

    @DeleteMapping(path = "subscriptions/{id}")
    public Boolean deleteSubscription(@PathVariable Long id){
        return userService.deleteSubscription(id);
    }
}
