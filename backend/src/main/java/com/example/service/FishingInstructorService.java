package com.example.service;

import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.FishingInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishingInstructorService {

    @Autowired
    private final FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public FishingInstructorService(FishingInstructorRepository fishingInstructorRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.fishingInstructorRepository = fishingInstructorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public List<User> getFishingInstructors(){
        List<User> listAllUsers = fishingInstructorRepository.findAll();
        List<User> fishingInstructors = new ArrayList<User>();

        for (int i = 0; i < listAllUsers.size(); i++) {
             if(listAllUsers.get(i).getRole().equals(UserRole.ROLE_FISHINGI)){
                fishingInstructors.add(listAllUsers.get(i));
             }
        }
        return fishingInstructors;
    };

    public User registerFishingInstructor(User fishingInstructor){
        boolean userExists = fishingInstructorRepository.findByUsername(fishingInstructor.getUsername()).isPresent();
        if(userExists){
            throw new IllegalStateException("username already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(fishingInstructor.getPassword());
        fishingInstructor.setPassword(encodedPassword);
        fishingInstructorRepository.save(fishingInstructor);
        return fishingInstructor;
    }


    public Boolean deleteFishingInstructorById(Long fishingInstructorId) {
        List<User> listFishingInstructors = getFishingInstructors();

        for (int i = 0; i < listFishingInstructors.size(); i++) {
            if(listFishingInstructors.get(i).getId().equals(fishingInstructorId)){
                fishingInstructorRepository.deleteById(fishingInstructorId);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public Boolean deleteFishingInstructorByUsername(String fishingInstructorUsername) {
        List<User> listFishingInstructors = getFishingInstructors();

        for (int i = 0; i < listFishingInstructors.size(); i++) {
            if(listFishingInstructors.get(i).getUsername().equals(fishingInstructorUsername)){
                fishingInstructorRepository.deleteById(listFishingInstructors.get(i).getId());
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
