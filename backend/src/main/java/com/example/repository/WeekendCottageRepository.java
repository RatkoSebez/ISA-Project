package com.example.repository;

import com.example.model.WeekendCottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekendCottageRepository extends JpaRepository<WeekendCottage, Long> {
}
