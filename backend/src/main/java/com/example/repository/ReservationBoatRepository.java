package com.example.repository;

import com.example.model.ReservationBoat;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationBoatRepository extends JpaRepository<ReservationBoat, Long> {
    Optional<ReservationBoat> findByClientEmail(String clientEmail);
}
