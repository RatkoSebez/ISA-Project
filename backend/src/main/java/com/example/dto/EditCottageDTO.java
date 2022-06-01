package com.example.dto;

import com.example.model.ReservationCottage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditCottageDTO {
    private String name;
    private String id;
    private String address;
    private String description;
    private String rulesOfConduct;
    private String image;
    private Integer priceList;
    private String additionalServices;
    private List<ReservationCottage> reservations;
    private Double rating;
    private String cottageOwnerId;
}
