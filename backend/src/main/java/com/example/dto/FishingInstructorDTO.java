package com.example.dto;

import com.example.model.FishingInstructor;

public class FishingInstructorDTO {
    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String adress;
    private String city;
    private String country;
    private String phoneNumber;

    public FishingInstructorDTO(long id, String email, String password, String firstName, String lastName, String adress, String city, String country, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public FishingInstructorDTO(FishingInstructor fishingInstructor) {
        this(fishingInstructor.getId(), fishingInstructor.getEmail(), fishingInstructor.getPassword(), fishingInstructor.getFirstName(), fishingInstructor.getLastName(),
                fishingInstructor.getAdress(), fishingInstructor.getCity(), fishingInstructor.getCountry(), fishingInstructor.getPhoneNumber());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
}
