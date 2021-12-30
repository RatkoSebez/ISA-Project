package com.example.Controller;

import com.example.Model.WeekendCottage;
import com.example.Service.WeekendCottageService;
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
        System.out.println("pozvan sam");
        return weekendCottageService.getAllWeekendCottages();
    }

    @GetMapping(path = "{id}")
    public Optional<WeekendCottage> getWeekendCottage(@PathVariable  Long id){
        System.out.println("pozvan sam");
        return weekendCottageService.getWeekendCottage(id);
    }
}
