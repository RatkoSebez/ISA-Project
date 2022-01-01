package com.example.controller;

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
}
