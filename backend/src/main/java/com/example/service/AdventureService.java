package com.example.service;

import com.example.dto.ReservationDTO;
import com.example.model.Adventure;
import com.example.model.Boat;
import com.example.model.ReservationAdventure;
import com.example.model.ReservationBoat;
import com.example.repository.AdventureRepository;
import com.example.repository.BoatRepository;
import com.example.repository.ReservationAdventureRepository;
import com.example.repository.ReservationBoatRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdventureService {
    private final AdventureRepository adventureRepository;
    private final ReservationAdventureRepository reservationAdventureRepository;

    public List<Adventure> getAllAdventures(){
        return adventureRepository.findAll();
    }

    public Optional<Adventure> getAdventure(Long id){
        return adventureRepository.findById(id);
    }

    public Boolean makeAdventureReservation(ReservationDTO boatReservationDTO){
        Adventure adventure = adventureRepository.findById(boatReservationDTO.getBoatId()).stream().findFirst().orElse(null);
        List<ReservationAdventure> reservations = adventure.getReservations();
        for(ReservationAdventure reservation : reservations){
            if(boatReservationDTO.getStartDate().isAfter(reservation.getEndDate()) || boatReservationDTO.getEndDate().isBefore(reservation.getStartDate())){}
            //ako se opseg nove rezervacije poklapa sa nekim datumom postojece rezervacije, onda ne pravim novu rezervaciju
            else return false;
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        reservations.add(new ReservationAdventure(boatReservationDTO.getStartDate(), boatReservationDTO.getEndDate(), email));
        adventure.setReservations(reservations);
        adventureRepository.save(adventure);
        return true;
    }

    public Boolean cancelAdventureReservation(Long id){
        Optional<ReservationAdventure> optional = reservationAdventureRepository.findById(id);
        ReservationAdventure reservation = optional.stream().findFirst().orElse(null);
        if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){
            return false;
        }
        reservationAdventureRepository.deleteById(id);
        return true;
    }

    public List<ReservationAdventure> getAllAdventureReservationsThatCanBeCancelled(){
        List<ReservationAdventure> reservations = reservationAdventureRepository.findAll();
        List<ReservationAdventure> reservations2 = new ArrayList<>();
        for(ReservationAdventure reservation : reservations){
            if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){}
            else reservations2.add(reservation);
        }
        return reservations2;
    }

    public List<ReservationAdventure> getAllAdventureReservations(){
        return reservationAdventureRepository.findAll();
    }
}
