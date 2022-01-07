package com.example.repository;

import com.example.model.ReservationAdventure;
import com.example.model.ReservationBoat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationAdventureRepository extends JpaRepository<ReservationAdventure, Long> {
    Optional<ReservationAdventure> findByClientEmail(String clientEmail);
}
