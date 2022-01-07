package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Compliant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String compliant;
    private Long idOfEntity;
    private com.example.model.Entity entity;

    public Compliant(String compliant, Long idOfEntity, com.example.model.Entity entity) {
        this.compliant = compliant;
        this.idOfEntity = idOfEntity;
        this.entity = entity;
    }
}
