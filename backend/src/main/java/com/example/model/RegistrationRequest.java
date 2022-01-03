package com.example.model;

import com.example.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 //   private Long userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private UserRole role;
    private Boolean locked;
    private String explanationOfRegistration;

    public RegistrationRequest(){}

    public RegistrationRequest(UserDTO fishingInstructorDTO){
        this(fishingInstructorDTO.getUsername(), fishingInstructorDTO.getPassword(), fishingInstructorDTO.getEmail(), fishingInstructorDTO.getFirstName(), fishingInstructorDTO.getLastName(),
                fishingInstructorDTO.getAddress(), fishingInstructorDTO.getCity(), fishingInstructorDTO.getCountry(), fishingInstructorDTO.getPhoneNumber(), fishingInstructorDTO.getRole(),
                fishingInstructorDTO.getLocked(), fishingInstructorDTO.getExplanationOfRegistration());
    }


    public RegistrationRequest(String username, String password, String email, String firstName, String lastName, String address, String city, String country, String phoneNumber, UserRole role, Boolean locked, String explanationOfRegistration) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.locked = locked;
        this.explanationOfRegistration = explanationOfRegistration;
    }

    public RegistrationRequest(Long id, String username, String password, String email, String firstName, String lastName, String address, String city, String country, String phoneNumber, UserRole role, Boolean locked, String explanationOfRegistration) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.locked = locked;
        this.explanationOfRegistration = explanationOfRegistration;
    }

}
