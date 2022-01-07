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
public class PotentialComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long entityId;
    private com.example.model.Entity entity;

    public PotentialComplaint(Long entityId, com.example.model.Entity entity) {
        this.entityId = entityId;
        this.entity = entity;
    }
}
