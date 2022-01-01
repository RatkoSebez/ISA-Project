package com.example.controller;

import com.example.model.Boat;
import com.example.model.WeekendCottage;
import com.example.service.BoatService;
import com.example.service.WeekendCottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
