package com.example.service;

import com.example.dto.BoatReservationDTO;
import com.example.model.Boat;
import com.example.model.ReservationBoat;
import com.example.model.User;
import com.example.repository.BoatRepository;
import com.example.repository.ReservationBoatRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoatService {
    private final BoatRepository boatRepository;
    private final ReservationBoatRepository reservationBoatRepository;

    public List<Boat> getAllBoats(){
        return boatRepository.findAll();
    }

    public Optional<Boat> getBoat(Long id){
        return boatRepository.findById(id);
    }

    public Boolean makeReservation(BoatReservationDTO boatReservationDTO){
        Boat boat = boatRepository.findById(boatReservationDTO.getBoatId()).stream().findFirst().orElse(null);
//        System.out.println(boatReservationDTO.getStartDate());
//        System.out.println(boatReservationDTO.getBoatId());
//        System.out.println(boatReservationDTO.getDays());
//        System.out.println(boatReservationDTO.getGuests());
        List<ReservationBoat> reservations = boat.getReservations();
        for(ReservationBoat reservation : reservations){
            if(boatReservationDTO.getStartDate().isAfter(reservation.getEndDate()) || boatReservationDTO.getEndDate().isBefore(reservation.getStartDate())){}
            //ako se opseg nove rezervacije poklapa sa nekim datumom postojece rezervacije, onda ne pravim novu rezervaciju
            else return false;
        }
//        List<ReservationBoat> reservations = boat.getReservations();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        reservations.add(new ReservationBoat(boatReservationDTO.getStartDate(), boatReservationDTO.getEndDate(), email));
        boat.setReservations(reservations);
        boatRepository.save(boat);
        return true;
    }

    public Boolean cancelReservation(Long id){
        Optional<ReservationBoat> optional = reservationBoatRepository.findById(id);
        ReservationBoat reservation = optional.stream().findFirst().orElse(null);
        if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){
            return false;
        }
        reservationBoatRepository.deleteById(id);
        return true;
    }

    public List<ReservationBoat> getAllReservationsThatCanBeCancelled(){
        List<ReservationBoat> reservations = reservationBoatRepository.findAll();
        List<ReservationBoat> reservations2 = new ArrayList<>();
        for(ReservationBoat reservation : reservations){
            if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){}
            else reservations2.add(reservation);
        }
        return reservations2;
    }

    public List<ReservationBoat> getAllBoatReservations(){
        return reservationBoatRepository.findAll();
    }
}
