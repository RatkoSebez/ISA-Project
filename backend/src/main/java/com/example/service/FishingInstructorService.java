package com.example.service;

import com.example.dto.FishingServiceDTO;
import com.example.model.FishingService;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.FishingInstructorRepository;
import com.example.repository.FishingServiceRepository;
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
    private final FishingServiceRepository fishingServiceRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public FishingInstructorService(FishingInstructorRepository fishingInstructorRepository, FishingServiceRepository fishingServiceRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.fishingInstructorRepository = fishingInstructorRepository;
        this.fishingServiceRepository = fishingServiceRepository;
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

    public FishingService addFishingService(FishingServiceDTO fishingServiceDTO) {

        User fishingInstructor = fishingInstructorRepository.findById(fishingServiceDTO.getFishingInstructorId()).stream().findFirst().orElse(null);

        FishingService newFishingService = new FishingService(fishingServiceDTO.getName(), fishingServiceDTO.getAddress(), fishingServiceDTO.getDescription(), fishingServiceDTO.getAboutFishingInstructor(),
                fishingServiceDTO.getImages(), fishingServiceDTO.getCapacityOfPeople(), fishingServiceDTO.getFreeTerms(), fishingServiceDTO.getRulesOfConduct(), fishingServiceDTO.getEquipment(),
                fishingServiceDTO.getPriceList(), fishingServiceDTO.getAdditionalInformation(), fishingServiceDTO.getCancellationPolicy());

        fishingInstructor.getFishingServiceList().add(newFishingService);
        fishingInstructorRepository.save(fishingInstructor);
        return newFishingService;
    }

    public Boolean deleteFishingServiceById(Long fishingServiceId) {
        List<FishingService> fishingServiceList = fishingServiceRepository.findAll();
        for (int i = 0; i < fishingServiceList.size(); i++) {
            if(fishingServiceList.get(i).getId().equals(fishingServiceId)) {
                fishingServiceRepository.deleteById(fishingServiceId);
                return true;
            }
        }
        return false;
    }

    public FishingService editFishingService(FishingServiceDTO fishingServiceDTO) {
        FishingService fishingService = fishingServiceRepository.findById(fishingServiceDTO.getId()).stream().findFirst().orElse(null);
        updateFishingServiceFields(fishingServiceDTO, fishingService);
        fishingServiceRepository.save(fishingService);
        return fishingService;
    }

    private void updateFishingServiceFields(FishingServiceDTO fishingServiceDTO, FishingService fishingService) {
        if( fishingServiceDTO.getName().trim() != "" ) {
            fishingService.setName(fishingServiceDTO.getName());
        }
        if( fishingServiceDTO.getAddress().trim() != "" ) {
            fishingService.setAddress(fishingServiceDTO.getAddress());
        }
        if( fishingServiceDTO.getDescription().trim() != "" ) {
            fishingService.setDescription(fishingServiceDTO.getDescription());
        }
        if( fishingServiceDTO.getAboutFishingInstructor().trim() != "" ) {
            fishingService.setAboutFishingInstructor(fishingServiceDTO.getAboutFishingInstructor());
        }
        if( fishingServiceDTO.getImages().trim() != "" ) {
            fishingService.setImages(fishingServiceDTO.getImages());
        }
        if( fishingServiceDTO.getCapacityOfPeople() >= 0 ) {
            fishingService.setCapacityOfPeople(fishingServiceDTO.getCapacityOfPeople());
        }
        if( fishingServiceDTO.getFreeTerms() != null) {
            fishingService.setFreeTerms(fishingServiceDTO.getFreeTerms());
        }
        if( fishingServiceDTO.getRulesOfConduct().trim() != "" ) {
            fishingService.setRulesOfConduct(fishingServiceDTO.getRulesOfConduct());
        }
        if( fishingServiceDTO.getEquipment().trim() != "" ) {
            fishingService.setEquipment(fishingServiceDTO.getEquipment());
        }
        if( fishingServiceDTO.getPriceList().trim() != "" ) {
            fishingService.setPriceList(fishingServiceDTO.getPriceList());
        }
        if( fishingServiceDTO.getAdditionalInformation().trim() != "" ) {
            fishingService.setAdditionalInformation(fishingServiceDTO.getAdditionalInformation());
        }
        if( fishingServiceDTO.getCancellationPolicy().trim() != "" ) {
            fishingService.setCancellationPolicy(fishingServiceDTO.getCancellationPolicy());
        }
    }

}
