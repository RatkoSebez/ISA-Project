package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.RegistrationRequest;
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

//    public User registerFishingInstructor(UserDTO fishingInstructor){
//        boolean userExists = fishingInstructorRepository.findByEmail(fishingInstructor.getEmail()).isPresent();
//        if(userExists){
//            throw new IllegalStateException("email already taken");
//        }
//        User newFishingInstructor = new User(fishingInstructor.getUsername(), fishingInstructor.getPassword(), fishingInstructor.getEmail(),
//                fishingInstructor.getFirstName(), fishingInstructor.getLastName(), fishingInstructor.getAddress(), fishingInstructor.getCity(), fishingInstructor.getCountry(),
//                fishingInstructor.getPhoneNumber(), fishingInstructor.getRole(), true);
//
//        String encodedPassword = bCryptPasswordEncoder.encode(newFishingInstructor.getPassword());
//        newFishingInstructor.setPassword(encodedPassword);
//        fishingInstructorRepository.save(newFishingInstructor);
//        sendRegistrationRequest(fishingInstructor);
//        return newFishingInstructor;
//    }


//    private void sendRegistrationRequest(UserDTO fishingInstructor){
//        RegistrationRequest newRegistrationRequest = new RegistrationRequest(fishingInstructor);
//
//        List<User> listAllUsers = fishingInstructorRepository.findAll();
//        for (int i = 0; i < listAllUsers.size(); i++) {
//            if(listAllUsers.get(i).getRole().equals(UserRole.ROLE_ADMIN)){
//                listAllUsers.get(i).getListRegistrationRequests().add(newRegistrationRequest);
//                fishingInstructorRepository.save(listAllUsers.get(i));
//                break;
//            }
//        }
//    }


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
