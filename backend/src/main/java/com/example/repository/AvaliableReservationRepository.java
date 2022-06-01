package com.example.repository;

import com.example.model.AvaliableReservations;
import com.example.model.FastReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliableReservationRepository extends JpaRepository<AvaliableReservations, Long> {
}
