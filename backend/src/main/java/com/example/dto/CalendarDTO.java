package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {
    private Long id;
    private String subject;
    private Date startTime;
    private Date endTime;
}