package com.example.model;

public class FishingService {

    private Long id;
    private String name;
    private String address;
    private String description;
    private String images;
    private String capacityOfPeople;
    private String freeTerms; // ZA SADA NEKA BUDE STRING, AL TREBA LISTA DATUMA
    private String rulesOfConduct;
    private String equipment;
    private String price;
    private String additionalInformation;
    private String cancellationPolicy;

    public FishingService(){}

    public FishingService(Long id, String name, String address, String description, String images, String capacityOfPeople, String freeTerms, String rulesOfConduct, String equipment, String price, String additionalInformation, String cancellationPolicy) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.images = images;
        this.capacityOfPeople = capacityOfPeople;
        this.freeTerms = freeTerms;
        this.rulesOfConduct = rulesOfConduct;
        this.equipment = equipment;
        this.price = price;
        this.additionalInformation = additionalInformation;
        this.cancellationPolicy = cancellationPolicy;
    }

    public FishingService(String name, String address, String description, String images, String capacityOfPeople, String freeTerms, String rulesOfConduct, String equipment, String price, String additionalInformation, String cancellationPolicy) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.images = images;
        this.capacityOfPeople = capacityOfPeople;
        this.freeTerms = freeTerms;
        this.rulesOfConduct = rulesOfConduct;
        this.equipment = equipment;
        this.price = price;
        this.additionalInformation = additionalInformation;
        this.cancellationPolicy = cancellationPolicy;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCapacityOfPeople() {
        return capacityOfPeople;
    }

    public void setCapacityOfPeople(String capacityOfPeople) {
        this.capacityOfPeople = capacityOfPeople;
    }

    public String getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(String freeTerms) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
