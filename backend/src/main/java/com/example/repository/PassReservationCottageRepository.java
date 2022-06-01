package com.example.repository;

import com.example.model.PassReservationCottage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassReservationCottageRepository extends JpaRepository<PassReservationCottage, Long> {
    Optional<PassReservationCottage> findByClientEmail(String clientEmail);
}
