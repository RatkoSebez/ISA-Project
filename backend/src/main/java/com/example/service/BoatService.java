package com.example.service;

import com.example.dto.AvailableReservationDTO;
import com.example.dto.EditReservationDTO;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final AvaliableReservationRepository avaliableReservationsRepository;

    public List<Boat> getAllBoats(){
        return boatRepository.findAll();
    }

    public List<Boat> getAllMyBoats(Long id){
        List<Boat> myBoats = new ArrayList<>();
        List<Boat> allBoats = boatRepository.findAll();
        for(Boat b : allBoats){
            if(b.getBoatOwnerId() == id)
                myBoats.add(b);
        }
        return myBoats;
    }

    public Optional<Boat> getBoat(Long id){
        return boatRepository.findById(id);
    }

    public Boolean makeBoatReservation(ReservationDTO boatReservationDTO){
        //System.out.println("---------------" + boatReservationDTO.getAdditionalServices());
        Boat boat = boatRepository.findById(boatReservationDTO.getBoatId()).stream().findFirst().orElse(null);
        List<ReservationBoat> reservations = boat.getReservations();
        for(ReservationBoat reservation : reservations){
            if(boatReservationDTO.getStartDate().isAfter(reservation.getEndDate()) || boatReservationDTO.getEndDate().isBefore(reservation.getStartDate())){}
            //ako se opseg nove rezervacije poklapa sa nekim datumom postojece rezervacije, onda ne pravim novu rezervaciju
            else return false;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        ReservationBoat reservation = new ReservationBoat(boatReservationDTO.getStartDate(), boatReservationDTO.getEndDate(), user.getEmail(), boatReservationDTO.getPrice(), boatReservationDTO.getGuests(), boatReservationDTO.getAdditionalServices(), boatReservationDTO.getBoatId());
        reservations.add(reservation);
        boat.setReservations(reservations);
        boatRepository.save(boat);
        //kad napravi rezervaciju, onda moze da pise zalbu na brod ili vlasnika broda
        List<PotentialComplaint> potentialComplaints = user.getPotentialComplaints();
        if(potentialComplaints == null) potentialComplaints = new ArrayList<PotentialComplaint>();
        potentialComplaints.add(new PotentialComplaint(boat.getId(), Entity.BOAT));
        potentialComplaints.add(new PotentialComplaint(boat.getBoatOwnerId(), Entity.BOAT_OWNER));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userRepository.save(user);
        //send email to notify client that reservation is made
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dislinkacc@outlook.com");
        message.setTo(user.getEmail());
        message.setSubject("You just made reservation.");
        message.setText("Reservation is made for boat number " + boatReservationDTO.getBoatId() + " and it starts on " + boatReservationDTO.getStartDate() + " and it ends on " + boatReservationDTO.getEndDate() + ".");
        javaMailSender.send(message);
        return true;
    }

    public Boolean cancelBoatReservation(Long id){
        Optional<ReservationBoat> optional = reservationBoatRepository.findById(id);
        ReservationBoat reservation = optional.stream().findFirst().orElse(null);
        if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){
            return false;
        }
        reservation.setCanceled(true);
        reservationBoatRepository.save(reservation);
        return true;
    }

    public Boolean editReservation(EditReservationDTO reservation){
        ReservationBoat oldReservation = reservationBoatRepository.findById(reservation.getId()).stream().findFirst().orElseThrow();
        if(oldReservation != null){
            if(oldReservation.getEndDate().isAfter(LocalDate.now()) && oldReservation.getCanceled() == false){
                oldReservation.setStartDate(findDate(reservation.getStartDate()));
                oldReservation.setEndDate(findDate(reservation.getEndDate()));

                reservationBoatRepository.save(oldReservation);
                return true;}
        }return false;
    }

    private LocalDate findDate(String start){
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
        return LocalDate.parse(start, formatter);
    }

    public List<ReservationBoat> getAllBoatReservationsThatCanBeCancelled(){
        List<ReservationBoat> reservations = reservationBoatRepository.findAll();
        List<ReservationBoat> reservations2 = new ArrayList<>();
        if(reservations!=null) {
            for (ReservationBoat reservation : reservations) {
                if (reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))) {
                } else reservations2.add(reservation);
            }
        }
        return reservations2;
    }

    public List<ReservationBoat> getAllBoatReservations(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        List<ReservationBoat> result = new ArrayList<>();
        List<ReservationBoat> reservationBoats = reservationBoatRepository.findAll();
        for(ReservationBoat reservationBoat : reservationBoats){
            if(reservationBoat.getClientEmail().equals(user.getEmail())) result.add(reservationBoat);
        }
        return result;
    }

//    public List<Boat> getAllBoatsFromListOfIds(List<Long> ids){
//        List<Boat> boats = new ArrayList<>();
//        for(Long id : ids){
//            boats.add(boatRepository.getById(id));
//        }
//        return boats;
//    }

    public List<ReservationBoat> getMyBoatReservations(Long id){
        Boat boat = boatRepository.findById(id).stream().findFirst().orElseThrow();
        List<ReservationBoat> reservations = new ArrayList<>();
        if(boat.getReservations() != null) { reservations = boat.getReservations();}
        return reservations;
    }

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

    public Boolean makeBoat(Boat boat){
        if(boat != null) {
            boatRepository.save(boat);
            return true;
        }
        else{return false;}
    }

    public Boolean editBoat(Boat boat){
        Boat oldBoat = boatRepository.findById(boat.getId()).stream().findFirst().orElseThrow();
        if(boat == null) {return false;}
        oldBoat.setName(boat.getName());
        oldBoat.setAddress(boat.getAddress());
        oldBoat.setDescription(boat.getDescription());
        oldBoat.setImage(boat.getImage());
        oldBoat.setPricePerDay(boat.getPricePerDay());
        oldBoat.setAdditionalServices(boat.getAdditionalServices());
        oldBoat.setType(boat.getType());
        oldBoat.setLength(boat.getLength());
        oldBoat.setEngineNumber(boat.getEngineNumber());
        oldBoat.setEnginePower(boat.getEnginePower());
        oldBoat.setMaxSpeed(boat.getMaxSpeed());
        oldBoat.setNavigation(boat.getNavigation());
        oldBoat.setCancellationConditions(boat.getCancellationConditions());
        oldBoat.setFishingEqu(boat.getFishingEqu());
        boatRepository.save(oldBoat);
        return true;
    }

    public Boolean deleteBoat(Long id){
        Optional<Boat> boat = boatRepository.findById(id);
        if(boat.get().getReservations()==null || boat.get().getReservations().size() == 0) {
            boatRepository.deleteById(id);
            return true;
        }
        else{return false;}
    }
}
