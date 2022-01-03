package com.example.controller;


import com.example.model.RegistrationRequest;
import com.example.model.User;
import com.example.service.AdministratorService;
import com.example.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public void declineRegistrationRequest(){
        //Imeplementirati
    }








}
