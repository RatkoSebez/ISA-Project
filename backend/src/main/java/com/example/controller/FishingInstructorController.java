package com.example.controller;


import com.example.dto.AvailabilityFishingInstructorDTO;
import com.example.dto.DeleteAccountRequestDTO;
import com.example.dto.FishingServiceDTO;
import com.example.dto.UserDTO;
import com.example.model.AvailabilityFishingInstructor;
import com.example.model.DeleteAccountRequest;
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

    @PutMapping(value = "/editFishingInstructor")
    public ResponseEntity<User> editFishingInstructor(@RequestBody UserDTO userDTO){
        User editedFishingInstructor = fishingInstructorService.editFishingInstructor(userDTO);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(editedFishingInstructor, HttpStatus.OK);
        return userResponseEntity;
    }

    @PostMapping(value = "/sendDeleteAccountRequest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteAccountRequest> sendDeleteAccountRequest(@RequestBody DeleteAccountRequestDTO deleteAccountRequestDTO) {
        DeleteAccountRequest newDeleteAccountRequest = fishingInstructorService.sendDeleteAccountRequest(deleteAccountRequestDTO);
        ResponseEntity<DeleteAccountRequest> DeleteAccReqResponseEntity = new ResponseEntity<>(newDeleteAccountRequest, HttpStatus.CREATED);
        return DeleteAccReqResponseEntity;
    }


    @PostMapping(value = "/defineAvailability", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvailabilityFishingInstructor> defineAvailability(@RequestBody AvailabilityFishingInstructorDTO availabilityFishingInstructorDTO) {
        AvailabilityFishingInstructor newAvailability = fishingInstructorService.defineAvailability(availabilityFishingInstructorDTO);
        ResponseEntity<AvailabilityFishingInstructor> defAvailabilityReqResponseEntity = new ResponseEntity<>(newAvailability, HttpStatus.CREATED);
        return defAvailabilityReqResponseEntity;
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
