package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliableReservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private com.example.model.Entity entity;
    private Long entityId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate expirationDate;
    private Double oldPrice;
    private Double newPrice;
    private Boolean fast;

    public AvaliableReservations(com.example.model.Entity entity, Long entityId, LocalDate startDate, LocalDate endDate, LocalDate expirationDate, Double oldPrice, Double newPrice, Boolean fast){
        this.entity = entity;
        this.entityId = entityId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expirationDate = expirationDate;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        this.fast = fast;
    }
}
