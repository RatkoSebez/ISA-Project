package com.example.controller;

import com.example.dto.*;
import com.example.model.*;
import com.example.service.BoatService;
import com.example.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/boat")
public class BoatController {
    @Autowired
    private BoatService boatService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping()
    public List<Boat> getAllBoats(){
        return boatService.getAllBoats();
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @GetMapping(path = "/myboats/{id}")
    public List<Boat> getAllMyBoats(@PathVariable Long id){
        return boatService.getAllMyBoats(id);
    }

    @GetMapping(path = "/reservation")
    public List<ReservationBoat> getAllReservationsThatCanBeCancelled(){
        return boatService.getAllBoatReservationsThatCanBeCancelled();
    }

    @GetMapping(path = "/allreservations")
    public List<ReservationBoat> getAllBoatReservations(){
        return boatService.getAllBoatReservations();
    }

    @GetMapping(path = "/fastreservation")
    public List<AvaliableReservations> getAllFastReservations(){
        return boatService.getAllBoatFastReservations();
    }

    @GetMapping(path = "{id}")
    public Optional<Boat> getBoat(@PathVariable Long id){
        return boatService.getBoat(id);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/reservation")
    public Boolean makeBoatReservation(@RequestBody ReservationDTO boatReservationDTO) {
        System.out.println("--------------------" + boatReservationDTO.getStartDate());
        Boolean ans = boatService.makeBoatReservation(boatReservationDTO);
        return ans;
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @DeleteMapping(path = "/reservation/{id}")
    public Boolean deleteBoatReservation(@PathVariable Long id){
        return boatService.cancelBoatReservation(id);
    }

//    @PostMapping(path = "/boatsForComplaint")
//    public List<Boat> getAllBoatsByIds(@RequestBody String[] ids){
//        ArrayList<Long> result = new ArrayList<>();
//        for(int i=0; i<ids.length; i++){
//            System.out.println(ids[i]);
//            result.add(Long.parseLong(ids[i]));
//        }
//        return boatService.getAllBoatsFromListOfIds(result);
//        //return null;
//    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @GetMapping(path = "/boatReservations/{id}")
    public List<ReservationBoat> getMyBoatReservations(@PathVariable Long id){
        return boatService.getMyBoatReservations(id);
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/editReservation")
    public Boolean editBoatReservation(@RequestBody EditReservationDTO reservation) {
        Boolean ans = boatService.editReservation(reservation);
        return ans;
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(path = "/compliant")
    public Boolean makeCompliant(@RequestBody Compliant compliant) {
        boatService.makeCompliant(compliant);
        return true;
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/addboat")
    public Boolean makeBoat(@RequestBody Boat boatReservationDTO) {
        Boolean ans = boatService.makeBoat(boatReservationDTO);
        return ans;
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @DeleteMapping(path = "/{id}")
    public Boolean deleteBoat(@PathVariable Long id){
        return boatService.deleteBoat(id);
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/editboat")
    public Boolean editBoat(@RequestBody Boat boatReservationDTO) {
        Boolean ans = boatService.editBoat(boatReservationDTO);
        return ans;
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/availability")
    public Boolean makeAvaliableReservation(@RequestBody AvailableReservationDTO newAvaliableReservation) {
        Boolean ans = reservationService.makeAvaliableReservation(newAvaliableReservation);
        return ans;
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/numResYearBoat")
    public ResponseEntity<Map<Integer, Integer>> getNumberofReservationYearlyBoat(@RequestBody String id) {
        Map<Integer, Integer> e = reservationService.numReservationYearBoat(id);
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/numResMontBoat")
    public ResponseEntity<Map<String, Integer>> getNumberofReservationMonthlyBoat(@RequestBody MonthDTO dto) {
        Map<String, Integer> e = reservationService.numReservationMontBoats(dto.getId(), dto.getYear());
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/numResSpecWeekBoat")
    public ResponseEntity<Map<String, Integer>> numResSpecWeekBoat(@RequestBody WeekDTO dto) {
        Map<String, Integer> e = reservationService.numReservationSpecWeekBoat(dto);
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/numResWeekBoat")
    public ResponseEntity<Integer> numResWeekBoat(@RequestBody WeekDTO dto) {
        Integer e = reservationService.numReseWeeklyBoat(dto);
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_BOATOWNER')")
    @PostMapping(path = "/incomeSpecWeekBoat")
    public ResponseEntity<Map<String, Double>> incomeSpecWeekCottage(@RequestBody WeekDTO dto) {
        Map<String, Double>  e = reservationService.getIncomeInSpecificRange(dto);
        if(e!=null)
            return new ResponseEntity<>(e, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
