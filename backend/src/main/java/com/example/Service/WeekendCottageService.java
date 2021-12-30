package com.example.Service;

import com.example.Model.WeekendCottage;
import com.example.Repository.WeekendCottageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeekendCottageService {
    private final WeekendCottageRepository weekendCottageRepository;

    public List<WeekendCottage> getAllWeekendCottages(){
        return weekendCottageRepository.findAll();
    }

    public Optional<WeekendCottage> getWeekendCottage(Long id){
        return weekendCottageRepository.findById(id);
    }
}
