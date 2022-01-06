package com.example.controller;


import com.example.dto.UserDTO;
import com.example.model.RegistrationRequest;
import com.example.model.User;
import com.example.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/administrator")
public class AdministratorController {

    @Autowired
    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping
    public List<User> getAdministrators(){
        return administratorService.getAdministrators();
    }


    @PostMapping(value = "/acceptRegistrationRequest")
    public boolean acceptRegistrationRequest(@RequestBody RegistrationRequest registrationRequest){
        return administratorService.acceptRegistrationRequest(registrationRequest);
    }

    @PostMapping(value = "/declineRegistrationRequest")
    public boolean declineRegistrationRequest(@RequestBody RegistrationRequest registrationRequest){
        return administratorService.declineRegistrationRequest(registrationRequest);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerAdministrator(@RequestBody UserDTO administrator) {
        User newAdministrator = administratorService.registerAdministrator(administrator);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(newAdministrator, HttpStatus.CREATED);
        return userResponseEntity;
    }


    @GetMapping(value = "/getUsers")
    public List<User> getUsers(){
        return administratorService.getUsers();
    }

    @DeleteMapping(path = "{userId}")
    public Boolean deleteUserById(@PathVariable("userId") Long userId){
        return administratorService.deleteUserById(userId);
    }






}
