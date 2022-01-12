package com.example.service;

import com.example.dto.ReservationDTO;
import com.example.model.*;
import com.example.repository.BoatRepository;
import com.example.repository.CompliantRepository;
import com.example.repository.ReservationBoatRepository;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BoatService {
    private final BoatRepository boatRepository;
    private final ReservationBoatRepository reservationBoatRepository;
    private final UserRepository userRepository;
    private final CompliantRepository compliantRepository;
    private final JavaMailSender javaMailSender;

    public List<Boat> getAllBoats(){
        return boatRepository.findAll();
    }

    public Optional<Boat> getBoat(Long id){
        return boatRepository.findById(id);
    }

    public Boolean makeBoatReservation(ReservationDTO boatReservationDTO){
        Boat boat = boatRepository.findById(boatReservationDTO.getBoatId()).stream().findFirst().orElse(null);
        List<ReservationBoat> reservations = boat.getReservations();
        for(ReservationBoat reservation : reservations){
            if(boatReservationDTO.getStartDate().isAfter(reservation.getEndDate()) || boatReservationDTO.getEndDate().isBefore(reservation.getStartDate())){}
            //ako se opseg nove rezervacije poklapa sa nekim datumom postojece rezervacije, onda ne pravim novu rezervaciju
            else return false;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        ReservationBoat reservation = new ReservationBoat(boatReservationDTO.getStartDate(), boatReservationDTO.getEndDate(), user.getEmail(), 60.0);
        reservations.add(reservation);
        boat.setReservations(reservations);
        boatRepository.save(boat);
        //kad napravi rezervaciju, onda moze da pise zalbu na brod ili vlasnika broda
        List<PotentialComplaint> potentialComplaints = user.getPotentialComplaints();
        potentialComplaints.add(new PotentialComplaint(boat.getId(), Entity.BOAT));
        potentialComplaints.add(new PotentialComplaint(boat.getBoatOwnerId(), Entity.BOAT_OWNER));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userRepository.save(user);
        return true;
    }

    public Boolean cancelBoatReservation(Long id){
        Optional<ReservationBoat> optional = reservationBoatRepository.findById(id);
        ReservationBoat reservation = optional.stream().findFirst().orElse(null);
        if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){
            return false;
        }
        reservationBoatRepository.deleteById(id);
        return true;
    }

    public List<ReservationBoat> getAllBoatReservationsThatCanBeCancelled(){
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

//    public List<Boat> getAllBoatsFromListOfIds(List<Long> ids){
//        List<Boat> boats = new ArrayList<>();
//        for(Long id : ids){
//            boats.add(boatRepository.getById(id));
//        }
//        return boats;
//    }

    public void makeCompliant(Compliant compliant){
        compliantRepository.save(compliant);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("isaprojmejl@gmail.com");
        Boat boat = boatRepository.findById(compliant.getIdOfEntity()).stream().findFirst().orElseThrow();
        User user = userRepository.findById(boat.getBoatOwnerId()).stream().findFirst().orElseThrow();
        message.setTo(user.getEmail());
        message.setSubject("Your boat received compliant.");
        message.setText(compliant.getCompliant());
        javaMailSender.send(message);
    }
}
