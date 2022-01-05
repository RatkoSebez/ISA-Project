package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @Column(columnDefinition="TEXT")
    private String description;
    private String image;
    private Integer capacity;
    private Double rating;
    private String priceList;
    @Column(columnDefinition="TEXT")
    private String additionalServices;
    @OneToMany(targetEntity = ReservationBoat.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "foreign_key", referencedColumnName = "id")
    private List<ReservationBoat> reservations;

    public Boat(String name, String address, String description, String image, Integer capacity, Double rating, String priceList, String additionalServices) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.image = image;
        this.capacity = capacity;
        this.rating = rating;
        this.priceList = priceList;
        this.additionalServices = additionalServices;
    }

    public Boat(String name, List<ReservationBoat> reservations){
        this.name = name;
        this.reservations = reservations;
    }
}
