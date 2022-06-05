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
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientEmail;
    private Long cottageId;
    private Boolean shows;
    private Assessment assessment;
    private String comment;

    public Report(String clientEmail, Long cottageId, Boolean shows, Assessment assessment, String comment){
        this.clientEmail = clientEmail;
        this.cottageId = cottageId;
        this.assessment = assessment;
        this.shows = shows;
        this.comment = comment;
    };
};


