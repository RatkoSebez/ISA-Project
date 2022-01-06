package com.example.repository;

import com.example.model.ReservationBoat;
import com.example.model.ReservationCottage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationCottageRepository extends JpaRepository<ReservationCottage, Long> {
    Optional<ReservationCottage> findByClientEmail(String clientEmail);
}
