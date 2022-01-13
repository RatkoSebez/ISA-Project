package com.example.service;

import com.example.dto.ReservationDTO;
import com.example.model.*;
import com.example.repository.CompliantRepository;
import com.example.repository.ReservationCottageRepository;
import com.example.repository.UserRepository;
import com.example.repository.WeekendCottageRepository;
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
public class WeekendCottageService {
    private final WeekendCottageRepository weekendCottageRepository;
    private final ReservationCottageRepository reservationCottageRepository;
    private final UserRepository userRepository;
    private final CompliantRepository compliantRepository;
    private final JavaMailSender javaMailSender;

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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        List<ReservationCottage> result = new ArrayList<>();
        List<ReservationCottage> reservationCottages = reservationCottageRepository.findAll();
        for(ReservationCottage reservationCottage : reservationCottages){
            if(reservationCottage.getClientEmail().equals(user.getEmail())) result.add(reservationCottage);
        }
        return result;
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
        reservations.add(new ReservationCottage(boatReservationDTO.getStartDate(), boatReservationDTO.getEndDate(), email, boatReservationDTO.getPrice(), boatReservationDTO.getGuests(), boatReservationDTO.getAdditionalServices()));
        weekendCottage.setReservations(reservations);
        weekendCottageRepository.save(weekendCottage);
        //kad napravi rezervaciju, onda moze da pise zalbu na brod ili vlasnika broda
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        List<PotentialComplaint> potentialComplaints = user.getPotentialComplaints();
        potentialComplaints.add(new PotentialComplaint(weekendCottage.getId(), Entity.WEEKEND_COTTAGE));
        potentialComplaints.add(new PotentialComplaint(weekendCottage.getCottageOwnerId(), Entity.COTTAGE_OWNER));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userRepository.save(user);
        //send email to notify client that reservation is made
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("isaprojmejl@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("You just made reservation.");
        message.setText("Reservation is made for weekend cottage number " + boatReservationDTO.getBoatId() + " and it starts on " + boatReservationDTO.getStartDate() + " and it ends on " + boatReservationDTO.getEndDate() + ".");
        javaMailSender.send(message);
        return true;
    }

    public void makeCompliant(Compliant compliant) {
        compliantRepository.save(compliant);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("isaprojmejl@gmail.com");
        WeekendCottage cottage = weekendCottageRepository.findById(compliant.getIdOfEntity()).stream().findFirst().orElseThrow();
        //Boat boat = boatRepository.findById(compliant.getIdOfEntity()).stream().findFirst().orElseThrow();
        User user = userRepository.findById(cottage.getCottageOwnerId()).stream().findFirst().orElseThrow();
        message.setTo(user.getEmail());
        message.setSubject("Your weekend cottage received compliant.");
        message.setText(compliant.getCompliant());
        javaMailSender.send(message);
    }
}
