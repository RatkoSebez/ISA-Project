package com.example.repository;

import com.example.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingInstructorRepository extends JpaRepository<FishingInstructor, Long> {

}
