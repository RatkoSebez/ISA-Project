package com.example.controller;

import com.example.dto.*;
import com.example.model.*;
import com.example.service.ReservationService;
import com.example.service.WeekendCottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/weekendCottage")
public class WeekendCottageController {
    @Autowired
    private WeekendCottageService weekendCottageService;

    @Autowired
    private ReservationService reservationService;

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

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @GetMapping(path = "/cottagesReservation/{id}")
    public List<ReservationCottage> getMyCottageReservations(@PathVariable Long id){
        return weekendCottageService.getMyCottageReservations(id);
    }

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @PostMapping(path = "/availability")
    public Boolean makeAvaliableReservation(@RequestBody AvailableReservationDTO newAvaliableReservation) {
        Boolean ans = weekendCottageService.makeAvaliableReservation(newAvaliableReservation);
        return ans;
    }

    @GetMapping(path = "/availableFastReservations")
    public List<AvaliableReservations> availableFastReservations() {
        return weekendCottageService.getAllFastAvailableReservations();
    }

    @GetMapping(path = "/availableReservations")
    public List<AvaliableReservations> availableReservations() {
        return weekendCottageService.getAllAvailableReservations();
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

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @PostMapping(path = "/numResMontCottage")
    public ResponseEntity<Map<String, Integer>> getNumberofReservationMonthlyCottage(@RequestBody MonthDTO dto) {
        Map<String, Integer> e = reservationService.numReservationMontCottage(dto.getId(), dto.getYear());
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @PostMapping(path = "/numResYearCottage")
    public ResponseEntity<Map<Integer, Integer>> getNumberofReservationYearlyCottage(@RequestBody String id) {
        Map<Integer, Integer> e = reservationService.numReservationYearCottage(id);
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @PostMapping(path = "/numResSpecWeekCottage")
    public ResponseEntity<Map<String, Integer>> numResSpecWeekCottage(@RequestBody WeekDTO dto) {
        Map<String, Integer> e = reservationService.numReservationSpecWeekCottage(dto);
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @PostMapping(path = "/numResWeekCottage")
    public ResponseEntity<Integer> numResWeekCottage(@RequestBody WeekDTO dto) {
        Integer e = reservationService.numReseWeeklyCottage(dto);
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_WEEKENDCOTTOWNER')")
    @PostMapping(path = "/incomeSpecWeekCottage")
    public ResponseEntity<Map<String, Double>> incomeSpecWeekCottage(@RequestBody WeekDTO dto) {
        Map<String, Double>  e = reservationService.getIncomeInSpecificPeriod(dto);
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
