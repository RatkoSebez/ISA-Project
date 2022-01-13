package com.example.service;

import com.example.dto.ReservationDTO;
import com.example.model.*;
import com.example.repository.*;
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

@Service
@AllArgsConstructor
public class AdventureService {
    private final AdventureRepository adventureRepository;
    private final ReservationAdventureRepository reservationAdventureRepository;
    private final UserRepository userRepository;
    private final CompliantRepository compliantRepository;
    private final JavaMailSender javaMailSender;

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
        reservations.add(new ReservationAdventure(boatReservationDTO.getStartDate(), boatReservationDTO.getEndDate(), email, 70.0, boatReservationDTO.getGuests(), boatReservationDTO.getAdditionalServices()));
        adventure.setReservations(reservations);
        adventureRepository.save(adventure);
        //kad napravi rezervaciju, onda moze da pise zalbu na brod ili vlasnika broda
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        List<PotentialComplaint> potentialComplaints = user.getPotentialComplaints();
        potentialComplaints.add(new PotentialComplaint(adventure.getId(), Entity.ADVENTURE));
        //potentialComplaints.add(new PotentialComplaint(adventure.getInstructorId(), Entity.FISHING_INSTRUCTOR));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userRepository.save(user);
        //send email to notify client that reservation is made
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("isaprojmejl@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("You just made reservation.");
        message.setText("Reservation is made for adventure number " + boatReservationDTO.getBoatId() + " and it starts on " + boatReservationDTO.getStartDate() + " and it ends on " + boatReservationDTO.getEndDate() + ".");
        javaMailSender.send(message);
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

    public void makeCompliant(Compliant compliant) {
        compliantRepository.save(compliant);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("isaprojmejl@gmail.com");
        Adventure adventure = adventureRepository.findById(compliant.getIdOfEntity()).stream().findFirst().orElseThrow();
        //Boat boat = boatRepository.findById(compliant.getIdOfEntity()).stream().findFirst().orElseThrow();
        User user = userRepository.findById(adventure.getInstructorId()).stream().findFirst().orElseThrow();
        message.setTo(user.getEmail());
        message.setSubject("You received compliant from client.");
        message.setText(compliant.getCompliant());
        javaMailSender.send(message);
    }
}
