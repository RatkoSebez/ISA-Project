package com.example.model;

import com.example.dto.FishingInstructorDTO;

public class RegistrationRequest {
    public String username;
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

    public RegistrationRequest(FishingInstructorDTO fishingInstructorDTO){
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getExplanationOfRegistration() {
        return explanationOfRegistration;
    }

    public void setExplanationOfRegistration(String explanationOfRegistration) {
        this.explanationOfRegistration = explanationOfRegistration;
    }
}
