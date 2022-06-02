package com.example.repository;

import com.example.model.AvaliableReservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliableReservationRepository extends JpaRepository<AvaliableReservations, Long> {
    public List<AvaliableReservations> findAllByFastIsTrue();
    public List<AvaliableReservations> findAllByFastIsFalse();
}
