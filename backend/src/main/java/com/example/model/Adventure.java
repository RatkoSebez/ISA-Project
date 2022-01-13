package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Adventure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(columnDefinition="TEXT")
    private String instructorBiography;
    private String image;
    private Double rating;
    private Integer capacity;
    private String priceList;
    @OneToMany(targetEntity = ReservationAdventure.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "foreign_key", referencedColumnName = "id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ReservationAdventure> reservations;
    private Long instructorId;
    private String additionalServices;

    public Adventure(String name, String address, String description, String instructorBiography, String image, Integer capacity, String priceList, Long instructorId, Double rating, String additionalServices) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.instructorBiography = instructorBiography;
        this.image = image;
        this.capacity = capacity;
        this.priceList = priceList;
        this.instructorId = instructorId;
        this.rating = rating;
        this.additionalServices = additionalServices;
    }
}
