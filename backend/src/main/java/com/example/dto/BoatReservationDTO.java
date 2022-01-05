package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoatReservationDTO {
    private LocalDate startDate;
    private Integer days;
    private Integer guests;
    private Long boatId;
}
