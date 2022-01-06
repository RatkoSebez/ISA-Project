package com.example.repository;

import com.example.model.FishingService;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingServiceRepository extends JpaRepository<FishingService, Long> {
}
