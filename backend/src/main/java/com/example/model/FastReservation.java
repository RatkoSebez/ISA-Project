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
public class FastReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private com.example.model.Entity entity;
    private Long entityId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String clientEmail;
    private Double oldPrice;
    private Double newPrice;

    public FastReservation(com.example.model.Entity entity, Long entityId, LocalDate startDate, LocalDate endDate, String clientEmail, Double oldPrice, Double newPrice) {
        this.entity = entity;
        this.entityId = entityId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientEmail = clientEmail;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }
}
