package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.Duration;
import java.time.LocalDateTime;

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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime endDate;
    private LocalDate expirationDate;
    private Double oldPrice;
    private Double newPrice;
    private Boolean fast;

    public AvaliableReservations(com.example.model.Entity entity, Long entityId, LocalDateTime startDate, LocalDateTime endDate, LocalDate expirationDate, Double oldPrice, Double newPrice, Boolean fast){
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
