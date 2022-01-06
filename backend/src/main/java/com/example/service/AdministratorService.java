package com.example.service;


import com.example.dto.UserDTO;
import com.example.model.RegistrationRequest;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.RegistrationRequestRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdministratorService {

    @Autowired
    private final UserRepository administratorRepository;
    @Autowired
    private final RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AdministratorService(UserRepository administratorRepository, RegistrationRequestRepository registrationRequestRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.administratorRepository = administratorRepository;
        this.registrationRequestRepository = registrationRequestRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getAdministrators() {
        List<User> listAllUsers = administratorRepository.findAll();
        List<User> administrators = new ArrayList<User>();

        for (int i = 0; i < listAllUsers.size(); i++) {
            if(listAllUsers.get(i).getRole().equals(UserRole.ROLE_ADMIN)){
                administrators.add(listAllUsers.get(i));
            }
        }
        return administrators;
    }

    public boolean acceptRegistrationRequest(RegistrationRequest registrationRequest) {
        User acceptedUser = administratorRepository.findUserByEmail(registrationRequest.getEmail());
        acceptedUser.setLocked(false);
        administratorRepository.save(acceptedUser);
        registrationRequestRepository.deleteById(registrationRequest.getId());
        return true;
    }

    public boolean declineRegistrationRequest(RegistrationRequest registrationRequest) {
        registrationRequestRepository.deleteById(registrationRequest.getId());
        return true;
    }

    public User registerAdministrator(UserDTO administratorDTO) {

        boolean userExists = administratorRepository.findByEmail(administratorDTO.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("email already taken");
        }
        User newAdministrator = new User(administratorDTO.getUsername(), administratorDTO.getPassword(), administratorDTO.getEmail(),
                administratorDTO.getFirstName(), administratorDTO.getLastName(), administratorDTO.getAddress(), administratorDTO.getCity(), administratorDTO.getCountry(),
                administratorDTO.getPhoneNumber(), administratorDTO.getRole(), false);

        String encodedPassword = bCryptPasswordEncoder.encode(newAdministrator.getPassword());
        newAdministrator.setPassword(encodedPassword);
        administratorRepository.save(newAdministrator);
        return newAdministrator;
    }

    public List<User> getUsers() {
        return administratorRepository.findAll();
    }

    public Boolean deleteUserById(Long userId) {
        List<User> listOfUsers = administratorRepository.findAll();

        for (int i = 0; i < listOfUsers.size(); i++) {
            if(listOfUsers.get(i).getId().equals(userId)){
                administratorRepository.deleteById(userId);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }


}
