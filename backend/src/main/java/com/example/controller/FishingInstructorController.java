package com.example.controller;


import com.example.dto.FishingServiceDTO;
import com.example.dto.UserDTO;
import com.example.model.FishingService;
import com.example.model.User;
import com.example.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/fishinginstructor")
public class FishingInstructorController {

    @Autowired
    private final FishingInstructorService fishingInstructorService;

    public FishingInstructorController(FishingInstructorService fishingInstructorService) {
        this.fishingInstructorService = fishingInstructorService;
    }


    @PostMapping(value = "/addFishingService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FishingService> addFishingService(@RequestBody FishingServiceDTO fishingServiceDTO) {
        FishingService newFishingService = fishingInstructorService.addFishingService(fishingServiceDTO);
        ResponseEntity<FishingService> FishingServiceResponseEntity = new ResponseEntity<>(newFishingService, HttpStatus.CREATED);
        return FishingServiceResponseEntity;
    }

    @DeleteMapping(path = "{fishingServiceId}")
    public Boolean deleteFishingServiceById(@PathVariable("fishingServiceId") Long fishingServiceId){
        return fishingInstructorService.deleteFishingServiceById(fishingServiceId);
    }

    @PutMapping(value = "/editFishingService")
    public ResponseEntity<FishingService> editFishingService(@RequestBody FishingServiceDTO fishingServiceDTO){
        FishingService editedFishingService = fishingInstructorService.editFishingService(fishingServiceDTO);
        ResponseEntity<FishingService> FishingServiceResponseEntity = new ResponseEntity<>(editedFishingService, HttpStatus.OK);
        return FishingServiceResponseEntity;
    }


    /*
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



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerFishingInstructor(@RequestBody UserDTO fishingInstructor) {
        User newFishingInstructor = fishingInstructorService.registerFishingInstructor(fishingInstructor);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(newFishingInstructor, HttpStatus.CREATED);
        return userResponseEntity;
    }

*/


}
