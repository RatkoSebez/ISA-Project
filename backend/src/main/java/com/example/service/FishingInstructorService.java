package com.example.service;

import com.example.dto.*;
import com.example.model.*;
import com.example.repository.DeleteAccountRequestRepository;
import com.example.repository.FishingInstructorRepository;
import com.example.repository.FishingServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FishingInstructorService {

    @Autowired
    private final FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    private final FishingServiceRepository fishingServiceRepository;
    @Autowired
    private final DeleteAccountRequestRepository deleteAccountRequestRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public FishingInstructorService(FishingInstructorRepository fishingInstructorRepository, FishingServiceRepository fishingServiceRepository, DeleteAccountRequestRepository deleteAccountRequestRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.fishingInstructorRepository = fishingInstructorRepository;
        this.fishingServiceRepository = fishingServiceRepository;
        this.deleteAccountRequestRepository = deleteAccountRequestRepository;
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

    public User editFishingInstructor(UserDTO userDTO) {
        User fishingInstructor = fishingInstructorRepository.findUserByEmail(userDTO.getEmail());
        updateFishingInstructorFields(userDTO, fishingInstructor);
        fishingInstructorRepository.save(fishingInstructor);
        return fishingInstructor;
    }

    private void updateFishingInstructorFields(UserDTO userDTO, User fishingInstructor) {
        if( userDTO.getUsername().trim() != "" ) {
            fishingInstructor.setUsername(userDTO.getUsername());
        }
        if( userDTO.getPassword().trim() != "" ) {
            String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
            fishingInstructor.setPassword(encodedPassword);
        }
        if( userDTO.getFirstName().trim() != "" ) {
            fishingInstructor.setFirstName(userDTO.getFirstName());
        }
        if( userDTO.getLastName().trim() != "" ) {
            fishingInstructor.setLastName(userDTO.getLastName());
        }
        if( userDTO.getLastName().trim() != "" ) {
            fishingInstructor.setAddress(userDTO.getAddress());
        }
        if( userDTO.getCity().trim() != "" ) {
            fishingInstructor.setCity(userDTO.getCity());
        }
        if( userDTO.getCountry().trim() != "" ) {
            fishingInstructor.setCountry(userDTO.getCountry());
        }
        if( userDTO.getPhoneNumber().trim() != "" ) {
            fishingInstructor.setPhoneNumber(userDTO.getPhoneNumber());
        }
    }

    public DeleteAccountRequest sendDeleteAccountRequest(DeleteAccountRequestDTO deleteAccountRequestDTO) {
        DeleteAccountRequest newDeleteAccountRequest = new DeleteAccountRequest(deleteAccountRequestDTO.getReason(), deleteAccountRequestDTO.getUserId());
        deleteAccountRequestRepository.save(newDeleteAccountRequest);
        return newDeleteAccountRequest;
    }

    public AvailabilityFishingInstructor defineAvailability(AvailabilityFishingInstructorDTO availabilityFishingInstructorDTO) {
        User fishingInstructor = fishingInstructorRepository.findById(availabilityFishingInstructorDTO.getIdFishInstr()).stream().findFirst().orElse(null);

        AvailabilityFishingInstructor afi = new AvailabilityFishingInstructor(availabilityFishingInstructorDTO.getStartDate(),
                                                                              availabilityFishingInstructorDTO.getEndDate());

        fishingInstructor.getAvailabilityFishingInstructorList().add(afi);
        fishingInstructorRepository.save(fishingInstructor);

        return afi;
    }



//    private Date dateAndTimeOfReservation;
//    private String place;
//    private String duration;
//    private Integer capacityOfPeople;
//    private String additionalServices;
//    private Integer price;
    //fishingServiceId
    public ReservationFishingService createOfferReservation(ActionOfferReservationDTO actionOfferReservationDTO) {
        FishingService fishingService = fishingServiceRepository.findById(actionOfferReservationDTO.getFishingServiceId()).stream().findFirst().orElse(null);

        ReservationFishingService newOfferReservation = new ReservationFishingService(actionOfferReservationDTO.getDateAndTimeOfReservation(),
                actionOfferReservationDTO.getPlace(), actionOfferReservationDTO.getDuration(), actionOfferReservationDTO.getCapacityOfPeople(),
                actionOfferReservationDTO.getAdditionalServices(), actionOfferReservationDTO.getPrice());

        fishingService.getOfferedReservationsList().add(newOfferReservation);
        fishingServiceRepository.save(fishingService);


        return newOfferReservation;

    }
}
