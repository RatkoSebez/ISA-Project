package com.example.controller;

import com.example.dto.ReservationDTO;
import com.example.model.ReservationBoat;
import com.example.model.ReservationCottage;
import com.example.model.WeekendCottage;
import com.example.service.WeekendCottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/weekendCottage")
public class WeekendCottageController {
    @Autowired
    private WeekendCottageService weekendCottageService;

    @GetMapping()
    public List<WeekendCottage> getAllWeekendCottages(){
        return weekendCottageService.getAllWeekendCottages();
    }

    @GetMapping(path = "{id}")
    public Optional<WeekendCottage> getWeekendCottage(@PathVariable Long id){
        return weekendCottageService.getWeekendCottage(id);
    }

    @GetMapping(path = "/reservation")
    public List<ReservationCottage> getAllReservationsThatCanBeCancelled(){
        return weekendCottageService.getAllCottageReservationsThatCanBeCancelled();
    }

    @GetMapping(path = "/allreservations")
    public List<ReservationCottage> getAllBoatReservations(){
        return weekendCottageService.getAllCottageReservations();
    }

    @DeleteMapping(path = "/reservation/{id}")
    public Boolean deleteCottageReservation(@PathVariable Long id){
        return weekendCottageService.cancelCottageReservation(id);
    }

    @PostMapping(path = "/reservation")
    public Boolean makeCottageReservation(@RequestBody ReservationDTO boatReservationDTO) {
        Boolean ans = weekendCottageService.makeCottageReservation(boatReservationDTO);
        return ans;
    }
}
