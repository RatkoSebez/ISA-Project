package com.example.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Entity
@Data
public class FishingService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String description;
    private String aboutFishingInstructor;
    private String images;
    private Integer capacityOfPeople;

    @ElementCollection
    @CollectionTable(name="FreeTearms", joinColumns=@JoinColumn(name="fishingService_id"))
    @Column(name="freeTerms")
    private List<Date> freeTerms;

    private String rulesOfConduct;
    private String equipment;
    private String priceList;
    private String additionalInformation;
    private String cancellationPolicy;

    @OneToMany(targetEntity = ReservationFishingService.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "foreign_key", referencedColumnName = "id")
    private List<ReservationFishingService> reservations;

    @OneToMany(targetEntity = ReservationFishingService.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "foreign_key", referencedColumnName = "id")
    private List<ReservationFishingService> offeredReservationsList;



    public FishingService(){}

    public FishingService(Long id, String name, String address, String description, String aboutFishingInstructor, String images, Integer capacityOfPeople, List<Date> freeTerms, String rulesOfConduct, String equipment, String priceList, String additionalInformation, String cancellationPolicy) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.aboutFishingInstructor = aboutFishingInstructor;
        this.images = images;
        this.capacityOfPeople = capacityOfPeople;
        this.freeTerms = freeTerms;
        this.rulesOfConduct = rulesOfConduct;
        this.equipment = equipment;
        this.priceList = priceList;
        this.additionalInformation = additionalInformation;
        this.cancellationPolicy = cancellationPolicy;
        this.reservations = new ArrayList<ReservationFishingService>();
        this.offeredReservationsList = new ArrayList<ReservationFishingService>();
    }

    public FishingService(String name, String address, String description, String aboutFishingInstructor, String images, Integer capacityOfPeople, List<Date> freeTerms, String rulesOfConduct, String equipment, String priceList, String additionalInformation, String cancellationPolicy) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.aboutFishingInstructor = aboutFishingInstructor;
        this.images = images;
        this.capacityOfPeople = capacityOfPeople;
        this.freeTerms = freeTerms;
        this.rulesOfConduct = rulesOfConduct;
        this.equipment = equipment;
        this.priceList = priceList;
        this.additionalInformation = additionalInformation;
        this.cancellationPolicy = cancellationPolicy;
        this.reservations = new ArrayList<ReservationFishingService>();
        this.offeredReservationsList = new ArrayList<ReservationFishingService>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAboutFishingInstructor() {
        return aboutFishingInstructor;
    }

    public void setAboutFishingInstructor(String aboutFishingInstructor) {
        this.aboutFishingInstructor = aboutFishingInstructor;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getCapacityOfPeople() {
        return capacityOfPeople;
    }

    public void setCapacityOfPeople(Integer capacityOfPeople) {
        this.capacityOfPeople = capacityOfPeople;
    }

    public List<Date> getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(List<Date> freeTerms) {
        this.freeTerms = freeTerms;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public void setRulesOfConduct(String rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }
}
