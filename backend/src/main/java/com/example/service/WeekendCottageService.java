package com.example.service;

import com.example.dto.ReservationDTO;
import com.example.model.ReservationBoat;
import com.example.model.ReservationCottage;
import com.example.model.WeekendCottage;
import com.example.repository.ReservationCottageRepository;
import com.example.repository.WeekendCottageRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeekendCottageService {
    private final WeekendCottageRepository weekendCottageRepository;
    private final ReservationCottageRepository reservationCottageRepository;

    public List<WeekendCottage> getAllWeekendCottages(){
        return weekendCottageRepository.findAll();
    }

    public Optional<WeekendCottage> getWeekendCottage(Long id){
        return weekendCottageRepository.findById(id);
    }

    public List<ReservationCottage> getAllCottageReservationsThatCanBeCancelled(){
        List<ReservationCottage> reservations = reservationCottageRepository.findAll();
        List<ReservationCottage> reservations2 = new ArrayList<>();
        for(ReservationCottage reservation : reservations){
            if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){}
            else reservations2.add(reservation);
        }
        return reservations2;
    }

    public List<ReservationCottage> getAllCottageReservations(){
        return reservationCottageRepository.findAll();
    }

    public Boolean cancelCottageReservation(Long id){
        Optional<ReservationCottage> optional = reservationCottageRepository.findById(id);
        ReservationCottage reservation = optional.stream().findFirst().orElse(null);
        if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){
            return false;
        }
        reservationCottageRepository.deleteById(id);
        return true;
    }

    public Boolean makeCottageReservation(ReservationDTO boatReservationDTO){
        WeekendCottage weekendCottage = weekendCottageRepository.findById(boatReservationDTO.getBoatId()).stream().findFirst().orElse(null);
        List<ReservationCottage> reservations = weekendCottage.getReservations();
        for(ReservationCottage reservation : reservations){
            if(boatReservationDTO.getStartDate().isAfter(reservation.getEndDate()) || boatReservationDTO.getEndDate().isBefore(reservation.getStartDate())){}
            //ako se opseg nove rezervacije poklapa sa nekim datumom postojece rezervacije, onda ne pravim novu rezervaciju
            else return false;
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        reservations.add(new ReservationCottage(boatReservationDTO.getStartDate(), boatReservationDTO.getEndDate(), email));
        weekendCottage.setReservations(reservations);
        weekendCottageRepository.save(weekendCottage);
        return true;
    }
}
