package com.example.controller;

import com.example.dto.EditCottageDTO;
import com.example.dto.ReservationDTO;
import com.example.model.Compliant;
import com.example.model.ReservationBoat;
import com.example.model.ReservationCottage;
import com.example.model.WeekendCottage;
import com.example.service.WeekendCottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @PostMapping(path = "/editCottage")
    public Boolean editCottage(@RequestBody EditCottageDTO cottage){
        return weekendCottageService.editCottage(cottage);
    }

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @DeleteMapping(path = "/cottage/{id}")
    public Boolean deleteCottage(@PathVariable Long id){
        return weekendCottageService.deleteCottage(id);
    }

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @GetMapping(path = "/mycottages/{id}")
    public List<WeekendCottage> getAllMyWeekendCottages(@PathVariable Long id){
        return weekendCottageService.getAllMyWeekendCottages(id);
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

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @DeleteMapping(path = "/reservation/{id}")
    public Boolean deleteCottageReservation(@PathVariable Long id){
        return weekendCottageService.cancelCottageReservation(id);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/reservation")
    public Boolean makeCottageReservation(@RequestBody ReservationDTO boatReservationDTO) {
        Boolean ans = weekendCottageService.makeCottageReservation(boatReservationDTO);
        return ans;
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/compliant")
    public Boolean makeCompliant(@RequestBody Compliant compliant) {
        weekendCottageService.makeCompliant(compliant);
        return true;
    }

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @PostMapping(path = "/addCottage")
    public Boolean makeCottage(@RequestBody WeekendCottage cottage) {
        weekendCottageService.createCottage(cottage);
        return true;
    }
}
