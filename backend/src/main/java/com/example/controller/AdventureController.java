package com.example.controller;

import com.example.dto.ReservationDTO;
import com.example.model.*;
import com.example.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/adventure")
public class AdventureController {
    @Autowired
    private AdventureService adventureService;

    @GetMapping()
    public List<Adventure> getAllAdventures(){
        return adventureService.getAllAdventures();
    }

    @GetMapping(path = "/reservation")
    public List<ReservationAdventure> getAllReservationsThatCanBeCancelled(){
        return adventureService.getAllAdventureReservationsThatCanBeCancelled();
    }

    @GetMapping(path = "/allreservation")
    public List<ReservationAdventure> getAllAdventureReservations(){
        return adventureService.getAllAdventureReservations();
    }

    @GetMapping(path = "{id}")
    public Optional<Adventure> getAdventure(@PathVariable Long id){
        return adventureService.getAdventure(id);
    }

    //    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/reservation")
    public Boolean makeAdventureReservation(@RequestBody ReservationDTO boatReservationDTO) {
        Boolean ans = adventureService.makeAdventureReservation(boatReservationDTO);
        return ans;
    }

    @DeleteMapping(path = "/reservation/{id}")
    public Boolean deleteAdventureReservation(@PathVariable Long id){
        return adventureService.cancelAdventureReservation(id);
    }

    @PostMapping(path = "/compliant")
    public Boolean makeCompliant(@RequestBody Compliant compliant) {
        adventureService.makeCompliant(compliant);
        return true;
    }
}
