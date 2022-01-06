package com.example.repository;

import com.example.model.DeleteAccountRequest;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteAccountRequestRepository extends JpaRepository<DeleteAccountRequest, Long> {

    @Query("SELECT dar FROM DeleteAccountRequest dar where dar.userId = :userId")
    public DeleteAccountRequest findDeleteAccountRequestById(Long userId);
}
