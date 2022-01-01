package com.example.service;

import com.example.model.Boat;
import com.example.repository.BoatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoatService {
    private final BoatRepository boatRepository;

    public List<Boat> getAllBoats(){
        return boatRepository.findAll();
    }

    public Optional<Boat> getBoat(Long id){
        return boatRepository.findById(id);
    }
}
