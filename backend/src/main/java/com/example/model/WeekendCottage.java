package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class WeekendCottage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @Column(columnDefinition="TEXT")
    private String description;
    private Double rating;
    private String image;
    private String priceList;
    @OneToMany(targetEntity = ReservationCottage.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "foreign_key", referencedColumnName = "id")
    private List<ReservationCottage> reservations;
    @Column(columnDefinition="TEXT")
    private String rulesOfConduct;
    private Long cottageOwnerId;
    private String additionalServices;

    public WeekendCottage(String name, String address, String description, Double rating, String rulesOfConduct, String image, Long cottageOwnerId, String priceList, String additionalServices){
        this.name = name;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.rulesOfConduct = rulesOfConduct;
        this.image = image;
        this.cottageOwnerId = cottageOwnerId;
        this.priceList = priceList;
        this.additionalServices = additionalServices;
    }

    public WeekendCottage(String name, String address, String description, Double rating, String rulesOfConduct, String image, Long cottageOwnerId, List<ReservationCottage> reservations, String priceList, String additionalServices){
        this.name = name;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.rulesOfConduct = rulesOfConduct;
        this.image = image;
        this.reservations = reservations;
        this.cottageOwnerId = cottageOwnerId;
        this.priceList = priceList;
        this.additionalServices = additionalServices;
    }
}
