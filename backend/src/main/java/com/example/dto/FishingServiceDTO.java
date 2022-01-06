package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishingServiceDTO {

    private Long id;
    private String name;
    private String address;
    private String description;
    private String aboutFishingInstructor;
    private String images;
    private Integer capacityOfPeople;
    private List<Date> freeTerms;
    private String rulesOfConduct;
    private String equipment;
    private String priceList;
    private String additionalInformation;
    private String cancellationPolicy;
    private Long fishingInstructorId;

    public FishingServiceDTO(String name, String address, String description, String aboutFishingInstructor, String images, Integer capacityOfPeople, List<Date> freeTerms, String rulesOfConduct, String equipment, String priceList, String additionalInformation, String cancellationPolicy, Long fishingInstructorId) {
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
        this.fishingInstructorId = fishingInstructorId;
    }

    public FishingServiceDTO(Long id, String name, String address, String description, String aboutFishingInstructor, String images, Integer capacityOfPeople, List<Date> freeTerms, String rulesOfConduct, String equipment, String priceList, String additionalInformation, String cancellationPolicy) {
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
    }
}
