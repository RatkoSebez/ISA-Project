package com.example.controller;

import com.example.model.Boat;
import com.example.model.FastReservation;
import com.example.service.BoatService;
import com.example.service.FastReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/fastReservation")
public class FastReservationController {
    @Autowired
    private FastReservationService fastReservationService;

    @GetMapping(path = "/boat")
    public List<FastReservation> getFastReservationsBoat(){
        return fastReservationService.getFastReservationsBoat();
    }

    @GetMapping(path = "/cottage")
    public List<FastReservation> getFastReservationsCottage(){
        return fastReservationService.getFastReservationsCottage();
    }

    @GetMapping(path = "/adventure")
    public List<FastReservation> getFastReservationsAdventure(){
        return fastReservationService.getFastReservationsAdventure();
    }
}
