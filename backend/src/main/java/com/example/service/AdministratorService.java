package com.example.service;


import com.example.dto.DeleteAccountRequestDTO;
import com.example.dto.UserDTO;
import com.example.model.DeleteAccountRequest;
import com.example.model.RegistrationRequest;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.DeleteAccountRequestRepository;
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
    private final DeleteAccountRequestRepository deleteAccountRequestRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AdministratorService(UserRepository administratorRepository, RegistrationRequestRepository registrationRequestRepository, DeleteAccountRequestRepository deleteAccountRequestRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.administratorRepository = administratorRepository;
        this.registrationRequestRepository = registrationRequestRepository;
        this.deleteAccountRequestRepository = deleteAccountRequestRepository;
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

    public User editAdministrator(UserDTO userDTO) {
        User administrator = administratorRepository.findUserByEmail(userDTO.getEmail());
        updateAdministratorFields(userDTO, administrator);
        administratorRepository.save(administrator);
        return administrator;
    }

    private void updateAdministratorFields(UserDTO userDTO, User administrator) {
        if( userDTO.getUsername().trim() != "" ) {
            administrator.setUsername(userDTO.getUsername());
        }
        if( userDTO.getPassword().trim() != "" ) {
            String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
            administrator.setPassword(encodedPassword);
        }
        if( userDTO.getFirstName().trim() != "" ) {
            administrator.setFirstName(userDTO.getFirstName());
        }
        if( userDTO.getLastName().trim() != "" ) {
            administrator.setLastName(userDTO.getLastName());
        }
        if( userDTO.getLastName().trim() != "" ) {
            administrator.setAddress(userDTO.getAddress());
        }
        if( userDTO.getCity().trim() != "" ) {
            administrator.setCity(userDTO.getCity());
        }
        if( userDTO.getCountry().trim() != "" ) {
            administrator.setCountry(userDTO.getCountry());
        }
        if( userDTO.getPhoneNumber().trim() != "" ) {
            administrator.setPhoneNumber(userDTO.getPhoneNumber());
        }
    }

    public List<DeleteAccountRequest> getDeleteAccountRequests() {
        return deleteAccountRequestRepository.findAll();
    }

    public boolean acceptDeleteAccountRequest(DeleteAccountRequestDTO deleteAccountRequestDTO) {
        administratorRepository.deleteById(deleteAccountRequestDTO.getUserId());
        DeleteAccountRequest deleteAccountRequest = deleteAccountRequestRepository.findDeleteAccountRequestById(deleteAccountRequestDTO.getUserId());
        deleteAccountRequestRepository.deleteById(deleteAccountRequest.getId());
        return true;
    }

    public boolean declineDeleteAccountRequest(DeleteAccountRequestDTO deleteAccountRequestDTO) {
        DeleteAccountRequest deleteAccountRequest = deleteAccountRequestRepository.findDeleteAccountRequestById(deleteAccountRequestDTO.getUserId());
        deleteAccountRequestRepository.deleteById(deleteAccountRequest.getId());
        return true;
    }
}
