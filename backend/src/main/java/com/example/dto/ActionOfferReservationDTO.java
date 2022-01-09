package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionOfferReservationDTO {


    private Date dateAndTimeOfReservation;
    private String place;
    private String duration;
    private Integer capacityOfPeople;
    private String additionalServices;
    private Integer price;

    private Long fishingServiceId;
}
