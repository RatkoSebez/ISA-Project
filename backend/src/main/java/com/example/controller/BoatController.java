package com.example.controller;

import com.example.dto.ReservationDTO;
import com.example.model.Boat;
import com.example.model.ReservationBoat;
import com.example.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/reservation")
    public List<ReservationBoat> getAllReservationsThatCanBeCancelled(){
        return boatService.getAllBoatReservationsThatCanBeCancelled();
    }

    @GetMapping(path = "/allreservations")
    public List<ReservationBoat> getAllBoatReservations(){
        return boatService.getAllBoatReservations();
    }

    @GetMapping(path = "{id}")
    public Optional<Boat> getBoat(@PathVariable Long id){
        return boatService.getBoat(id);
    }

//    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/reservation")
    public Boolean makeBoatReservation(@RequestBody ReservationDTO boatReservationDTO) {
        Boolean ans = boatService.makeBoatReservation(boatReservationDTO);
        return ans;
    }

    @DeleteMapping(path = "/reservation/{id}")
    public Boolean deleteBoatReservation(@PathVariable Long id){
        return boatService.cancelBoatReservation(id);
    }
}
