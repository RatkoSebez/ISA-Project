package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCottage {
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
    private Boolean canceled;

    public ReservationCottage(LocalDate start, LocalDate end, String clientEmail, Double price, Integer guests, String additionalServices, Long cottageId, Boolean canceled){
        this.startDate = start;
        this.endDate = end;
        this.clientEmail = clientEmail;
        this.price = price;
        this.guests = guests;
        this.additionalServices = additionalServices;
        this.cottageId = cottageId;
        this.canceled = canceled;
    }
}
