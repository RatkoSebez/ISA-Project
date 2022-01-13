package com.example.repository;

import com.example.model.FastReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FastReservationRepository extends JpaRepository<FastReservation, Long> {
}
