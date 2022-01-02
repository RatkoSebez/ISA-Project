package com.example.repository;


import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FishingInstructorRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<Object> findByEmail(String email);
}
