package com.example.controller;


import com.example.model.User;
import com.example.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/fishinginstructor")
public class FishingInstructorController {

    @Autowired
    private final FishingInstructorService fishingInstructorService;

    public FishingInstructorController(FishingInstructorService fishingInstructorService) {
        this.fishingInstructorService = fishingInstructorService;
    }
    

    @DeleteMapping(path = "{fishingInstructorId}")
    public Boolean deleteFishingInstructorById(@PathVariable("fishingInstructorId") Long fishingInstructorId){
        return fishingInstructorService.deleteFishingInstructorById(fishingInstructorId);
    }

    @DeleteMapping(path = "{fishingInstructorUsername}")
    public Boolean deleteFishingInstructorByUsername(@PathVariable("fishingInstructorUsername") String fishingInstructorUsername){
        return fishingInstructorService.deleteFishingInstructorByUsername(fishingInstructorUsername);
    }


    @GetMapping
    public List<User> getFishingInstructors(){
        return fishingInstructorService.getFishingInstructors();
    }

    @PostMapping()
    public ResponseEntity<User> registerFishingInstructor(@RequestBody User user) {
        fishingInstructorService.registerFishingInstructor(user);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return userResponseEntity;
    }

















/*

    @GetMapping
    public List<FishingInstructor> getFishingInstructors(){
        return  fishingInstructorService.getFishingInstructors();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<FishingInstructorDTO> createFishingInstructor(@RequestBody FishingInstructorDTO fishingInstructorDTO) {

        FishingInstructor fishingInstructor = new FishingInstructor();
        fishingInstructor.setEmail(fishingInstructorDTO.getEmail());
        fishingInstructor.setPassword(fishingInstructorDTO.getPassword());
        fishingInstructor.setFirstName(fishingInstructorDTO.getFirstName());
        fishingInstructor.setLastName(fishingInstructorDTO.getLastName());
        fishingInstructor.setAdress(fishingInstructorDTO.getAdress());
        fishingInstructor.setCity(fishingInstructorDTO.getCity());
        fishingInstructor.setCountry(fishingInstructorDTO.getCountry());
        fishingInstructor.setPhoneNumber(fishingInstructorDTO.getPhoneNumber());

        fishingInstructor = fishingInstructorService.save(fishingInstructor);
        return new ResponseEntity<>(new FishingInstructorDTO(fishingInstructor), HttpStatus.CREATED);
    }
*/


}
