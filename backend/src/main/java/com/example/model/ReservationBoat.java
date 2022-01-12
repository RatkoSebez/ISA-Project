package com.example.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationBoat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String clientEmail;
    private Double price;

    public ReservationBoat(LocalDate start, LocalDate end, String clientEmail, Double price){
        this.startDate = start;
        this.endDate = end;
        this.clientEmail = clientEmail;
        this.price = price;
    }
}
