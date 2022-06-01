package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassReservationCottage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String clientEmail;
    private Double price;
    private Integer guests;
    private String additionalServices;
    private Long cottageId;

    public PassReservationCottage(LocalDate start, LocalDate end, String clientEmail, Double price, Integer guests, String additionalServices, Long cottageId){
        this.startDate = start;
        this.endDate = end;
        this.clientEmail = clientEmail;
        this.price = price;
        this.guests = guests;
        this.additionalServices = additionalServices;
        this.cottageId = cottageId;
    }
}
