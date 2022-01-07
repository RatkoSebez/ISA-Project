package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Integer capacity;
    private String priceList;
    @OneToMany(targetEntity = ReservationAdventure.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "foreign_key", referencedColumnName = "id")
    private List<ReservationAdventure> reservations;

    public Adventure(String name, String address, String description, String instructorBiography, String image, Integer capacity, String priceList) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.instructorBiography = instructorBiography;
        this.image = image;
        this.capacity = capacity;
        this.priceList = priceList;
    }
}
