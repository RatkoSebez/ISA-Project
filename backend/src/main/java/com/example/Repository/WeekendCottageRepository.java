package com.example.Repository;

import com.example.Model.WeekendCottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WeekendCottageRepository extends JpaRepository<WeekendCottage, Long> {
}
