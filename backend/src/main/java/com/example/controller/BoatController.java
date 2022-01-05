package com.example.controller;

import com.example.dto.BoatReservationDTO;
import com.example.dto.UserDTO;
import com.example.model.Boat;
import com.example.model.User;
import com.example.model.WeekendCottage;
import com.example.service.BoatService;
import com.example.service.WeekendCottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/boat")
public class BoatController {
    @Autowired
    private BoatService boatService;

    @GetMapping()
    public List<Boat> getAllBoats(){
        return boatService.getAllBoats();
    }

    @GetMapping(path = "{id}")
    public Optional<Boat> getBoat(@PathVariable Long id){
        return boatService.getBoat(id);
    }

//    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/reservation")
    public Boolean registerUser(@RequestBody BoatReservationDTO boatReservationDTO) {
        Boolean ans = boatService.makeReservation(boatReservationDTO);
        return ans;
    }
}
