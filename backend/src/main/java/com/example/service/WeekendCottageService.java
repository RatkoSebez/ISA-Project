package com.example.service;

import com.example.dto.*;
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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeekendCottageService {
    private final WeekendCottageRepository weekendCottageRepository;
    private final ReservationCottageRepository reservationCottageRepository;
    private final AvaliableReservationRepository avaliableReservationsRepository;
    private final UserRepository userRepository;
    private final CompliantRepository compliantRepository;
    private final ReportRepository reportRepository;
    private final JavaMailSender javaMailSender;

    public List<WeekendCottage> getAllWeekendCottages(){
        return weekendCottageRepository.findAll();
    }

    public Optional<WeekendCottage> getWeekendCottage(Long id){
        return weekendCottageRepository.findById(id);
    }

    public Boolean createCottage(WeekendCottage cottage){
        weekendCottageRepository.save(cottage);
        return true;
    }

    public Boolean deleteCottage(Long id){
        Optional<WeekendCottage> cottage = weekendCottageRepository.findById(id);
        if(cottage.get().getReservations().size() == 0) {
            weekendCottageRepository.deleteById(id);
        }
        if(weekendCottageRepository.findById(id)==null)
            return true;
        return false;
    }

    public Boolean editCottage(EditCottageDTO cottage){
        WeekendCottage oldCottage = weekendCottageRepository.findById(Long.parseLong(cottage.getId())).stream().findFirst().orElseThrow();
        if(oldCottage == null)
            return false;
        oldCottage.setName(cottage.getName());
        oldCottage.setAddress(cottage.getAddress());
        oldCottage.setDescription(cottage.getDescription());
        oldCottage.setImage(cottage.getImage());
        oldCottage.setRulesOfConduct(cottage.getRulesOfConduct());
        oldCottage.setPriceList(cottage.getPriceList());
        oldCottage.setAdditionalServices(cottage.getAdditionalServices());
        weekendCottageRepository.save(oldCottage);
        return true;
    }

    public Boolean editReservation(EditReservationDTO reservation){
        ReservationCottage oldReservation = reservationCottageRepository.findById(reservation.getId()).stream().findFirst().orElseThrow();
        if(oldReservation != null){
            if(oldReservation.getEndDate().isBefore(LocalDate.now())){
            oldReservation.setStartDate(findDate(reservation.getStartDate()));
            oldReservation.setEndDate(findDate(reservation.getEndDate()));
            reservationCottageRepository.save(oldReservation);
            return true;}
        }return false;
    }

    public Boolean makeAvaliableReservation(AvailableReservationDTO newAvaliableReservation){
        LocalDateTime end = findDateTime(String.valueOf(newAvaliableReservation.getEndDate()));
        LocalDateTime start = findDateTime(String.valueOf(newAvaliableReservation.getStartDate()));
        AvaliableReservations newAR = new AvaliableReservations(null, newAvaliableReservation.getEntity(), newAvaliableReservation.getEntityId(), start, end, newAvaliableReservation.getExpirationDate(), newAvaliableReservation.getOldPrice(), newAvaliableReservation.getNewPrice(), newAvaliableReservation.getFast());
        if(checkNewReservation(newAR)){
            avaliableReservationsRepository.save(newAR);
            return true;
        }
        return false;
    }

    private LocalDateTime findDateTime(String start){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(start, formatter);
    }

    private LocalDate findDate(String start){
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
        return LocalDate.parse(start, formatter);
    }

    public List<AvaliableReservations> getAllFastAvailableReservations(){
        List<AvaliableReservations> available = avaliableReservationsRepository.findAllByFastIsTrue();
        if(available != null) {
            return available;
        }else{available = new ArrayList<>();}
        return available;
    }

    public List<AvaliableReservations> getAllAvailableReservations(){
        List<AvaliableReservations> available = avaliableReservationsRepository.findAllByFastIsFalse();
        if(available != null) {
            return available;
        }else{available = new ArrayList<>();}
        return available;
    }

    public List<WeekendCottage> getAllMyWeekendCottages(Long id){
        List<WeekendCottage> myCottages = new ArrayList<>();
        List<WeekendCottage> allCottages = weekendCottageRepository.findAll();
        for(WeekendCottage w : allCottages){
            if(w.getCottageOwnerId()==id)
                myCottages.add(w);
        }
        return myCottages;
    }

    public List<ReservationCottage> getAllCottageReservationsThatCanBeCancelled(){
        List<ReservationCottage> reservations = reservationCottageRepository.findAll();
        List<ReservationCottage> reservations2 = new ArrayList<>();
        if(reservations != null) {
            for (ReservationCottage reservation : reservations) {
                if (reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))) {
                } else reservations2.add(reservation);
            }
        }
        return reservations2;
    }

    public List<CalendarDTO> getAllReservationForCalendar(){
        List<ReservationCottage> reservations = reservationCottageRepository.findAll();
        CalendarDTO reservation3 = new CalendarDTO();
        List<CalendarDTO> reservations2 = new ArrayList<>();
        if(reservations != null) {
            for (ReservationCottage reservation : reservations) {
                Date dateStart = Date.from(reservation.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date dateEnd = Date.from(reservation.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
               reservation3.setId(reservation.getId());
               reservation3.setEndTime(dateEnd);
               reservation3.setStartTime(dateStart);
               reservation3.setSubject("reserved");
               reservations2.add(reservation3);
            }
        }
        return reservations2;
    }

    public List<ReservationCottage> getMyCottageReservations(Long id){
        WeekendCottage cottage = weekendCottageRepository.findById(id).stream().findFirst().orElseThrow();
        List<ReservationCottage> reservations = new ArrayList<ReservationCottage>();
        if(cottage.getReservations() != null) { reservations = cottage.getReservations();}
        return reservations;
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
        if(reservation.getStartDate().isBefore(LocalDate.now().plusDays(3))){return false;}
        reservation.setCanceled(true);
        reservationCottageRepository.save(reservation);
        return true;
    }

    public Boolean reportMes(Report repo){
        if(repo != null) {
            reportRepository.save(repo);
            return true;
        }
        return false;
    }

    private Boolean checkNewReservation(AvaliableReservations newAvaliableRes){
        List<AvaliableReservations> reservations = avaliableReservationsRepository.findAll();
        for (AvaliableReservations reservation : reservations) {
            if(reservation.getEntityId() == newAvaliableRes.getEntityId()) {
                if (newAvaliableRes.getStartDate().isAfter(reservation.getEndDate()) || newAvaliableRes.getEndDate().isBefore(reservation.getStartDate())) {
                }
                //ako se opseg nove rezervacije poklapa sa nekim datumom postojece rezervacije, onda ne pravim novu rezervaciju
                else return false;
            }
        }return true;
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
        reservations.add(new ReservationCottage(boatReservationDTO.getStartDate(), boatReservationDTO.getEndDate(), email, boatReservationDTO.getPrice(), boatReservationDTO.getGuests(), boatReservationDTO.getAdditionalServices(), weekendCottage.getId(),false));
        //avaliableReservationsRepository.deleteByEntityId(weekendCottage.getId());
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
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("dislinkacc@outlook.com");
//        message.setTo(user.getEmail());
//        message.setSubject("You just made reservation.");
//        message.setText("Reservation is made for weekend cottage number " + boatReservationDTO.getBoatId() + " and it starts on " + boatReservationDTO.getStartDate() + " and it ends on " + boatReservationDTO.getEndDate() + ".");
//        javaMailSender.send(message);
        return true;
    }

    public void makeCompliant(Compliant compliant) {
        compliantRepository.save(compliant);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dislinkacc@outlook.com");
        WeekendCottage cottage = weekendCottageRepository.findById(compliant.getIdOfEntity()).stream().findFirst().orElseThrow();
        //Boat boat = boatRepository.findById(compliant.getIdOfEntity()).stream().findFirst().orElseThrow();
        User user = userRepository.findById(cottage.getCottageOwnerId()).stream().findFirst().orElseThrow();
//        message.setTo(user.getEmail());
//        message.setSubject("Your weekend cottage received compliant.");
//        message.setText(compliant.getCompliant());
//        javaMailSender.send(message);
    }
}
