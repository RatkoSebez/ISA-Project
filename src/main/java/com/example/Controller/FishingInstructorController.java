package com.example.Controller;


import com.example.DTO.FishingInstructorDTO;
import com.example.Model.FishingInstructor;
import com.example.Service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



}
