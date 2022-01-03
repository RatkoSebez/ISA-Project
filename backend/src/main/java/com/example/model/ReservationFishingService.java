package com.example.model;

import java.util.Date;

public class ReservationFishingService {

    private Long id;
    private Date dateAndTimeOfReservation;
    private String place;
    private String duration;
    private String capacityOfPeople;
    private String additionalServices;
    private String price;

    public ReservationFishingService(){}

    public ReservationFishingService(Long id, Date dateAndTimeOfReservation, String place, String duration, String capacityOfPeople, String additionalServices, String price) {
        this.id = id;
        this.dateAndTimeOfReservation = dateAndTimeOfReservation;
        this.place = place;
        this.duration = duration;
        this.capacityOfPeople = capacityOfPeople;
        this.additionalServices = additionalServices;
        this.price = price;
    }

    public ReservationFishingService(Date dateAndTimeOfReservation, String place, String duration, String capacityOfPeople, String additionalServices, String price) {
        this.dateAndTimeOfReservation = dateAndTimeOfReservation;
        this.place = place;
        this.duration = duration;
        this.capacityOfPeople = capacityOfPeople;
        this.additionalServices = additionalServices;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateAndTimeOfReservation() {
        return dateAndTimeOfReservation;
    }

    public void setDateAndTimeOfReservation(Date dateAndTimeOfReservation) {
        this.dateAndTimeOfReservation = dateAndTimeOfReservation;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCapacityOfPeople() {
        return capacityOfPeople;
    }

    public void setCapacityOfPeople(String capacityOfPeople) {
        this.capacityOfPeople = capacityOfPeople;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
