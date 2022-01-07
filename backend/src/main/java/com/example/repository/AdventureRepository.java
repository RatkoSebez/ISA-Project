package com.example.repository;

import com.example.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
}
