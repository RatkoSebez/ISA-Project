package com.example.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class ReservationFishingService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateAndTimeOfReservation;
    private String place;
    private String duration;
    private Integer capacityOfPeople;
    private String additionalServices;
    private Integer price;

    public ReservationFishingService(){}

    public ReservationFishingService(Long id, Date dateAndTimeOfReservation, String place, String duration, Integer capacityOfPeople, String additionalServices, Integer price) {
        this.id = id;
        this.dateAndTimeOfReservation = dateAndTimeOfReservation;
        this.place = place;
        this.duration = duration;
        this.capacityOfPeople = capacityOfPeople;
        this.additionalServices = additionalServices;
        this.price = price;
    }

    public ReservationFishingService(Date dateAndTimeOfReservation, String place, String duration, Integer capacityOfPeople, String additionalServices, Integer price) {
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

    public Integer getCapacityOfPeople() {
        return capacityOfPeople;
    }

    public void setCapacityOfPeople(Integer capacityOfPeople) {
        this.capacityOfPeople = capacityOfPeople;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
