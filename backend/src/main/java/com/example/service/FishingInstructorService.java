package com.example.service;

import com.example.model.FishingInstructor;
import com.example.repository.FishingInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishingInstructorService {

    @Autowired
    private final FishingInstructorRepository fishingInstructorRepository;

    public FishingInstructorService(FishingInstructorRepository fishingInstructorRepository) {
        this.fishingInstructorRepository = fishingInstructorRepository;
    }

    public List<FishingInstructor> getFishingInstructors(){

        return fishingInstructorRepository.findAll();
//        return List.of(
//                new FishingInstructor(1L,
//                                      "fishingins@gmail.com",
//                                      "123",
//                                      "Marko",
//                                      "Markovic",
//                                      "Ulica 2",
//                                      "Novi Sad",
//                                      "Srbija",
//                                      "123456789")
//
//        );
    };

    public FishingInstructor save(FishingInstructor fishingInstructor) {
        return fishingInstructorRepository.save(fishingInstructor);
    }
}
