package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Entity entity;
    private Long idOfEntity;

    public UserSubscription(Entity entity, Long idOfEntity){
        this.entity = entity;
        this.idOfEntity = idOfEntity;
    }
}
