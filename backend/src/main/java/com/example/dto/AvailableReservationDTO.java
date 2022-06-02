package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableReservationDTO {
    private com.example.model.Entity entity;
    private Long entityId;
    private String startDate;
    private String endDate;
    private LocalDate expirationDate;
    private Double oldPrice;
    private Double newPrice;
    private Boolean fast;
}
