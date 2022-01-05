package com.example.service;

import com.example.dto.BoatReservationDTO;
import com.example.model.Boat;
import com.example.model.ReservationBoat;
import com.example.model.User;
import com.example.repository.BoatRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoatService {
    private final BoatRepository boatRepository;

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
            if(boatReservationDTO.getStartDate().isAfter(reservation.getEndDate()) || boatReservationDTO.getStartDate().plusDays(boatReservationDTO.getDays()).isBefore(reservation.getStartDate())){}
            //ako se opseg nove rezervacije poklapa sa nekim datumom postojece rezervacije, onda ne pravim novu rezervaciju
            else return false;
        }
//        List<ReservationBoat> reservations = boat.getReservations();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        reservations.add(new ReservationBoat(boatReservationDTO.getStartDate(), boatReservationDTO.getStartDate().plusDays(boatReservationDTO.getDays()), email));
        boat.setReservations(reservations);
        boatRepository.save(boat);
        return true;
    }
}
