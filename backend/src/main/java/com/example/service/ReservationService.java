package com.example.service;

import com.example.dto.AvailableReservationDTO;
import com.example.dto.WeekDTO;
import com.example.model.AvaliableReservations;
import com.example.model.ReservationBoat;
import com.example.model.ReservationCottage;
import com.example.repository.AvaliableReservationRepository;
import com.example.repository.ReservationBoatRepository;
import com.example.repository.ReservationCottageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationCottageRepository reservationCottageRepository;
    private final AvaliableReservationRepository avaliableReservationsRepository;
    private final ReservationBoatRepository reservationBoatRepository;

    private LocalDateTime findDate(String start){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(start, formatter);
    }

    public Map<String, Integer> numReservationMontCottage(String id, String year){
        Map<String,Integer> map=new HashMap<>();
        for(ReservationCottage r: reservationCottageRepository.findAll()){
            if(!map.containsKey(r.getStartDate().getMonth().toString())){
                Integer n = countResPerMonthCottage(r.getStartDate().getMonth().toString(), Integer.parseInt(year), Long.valueOf(id));
                map.put(r.getStartDate().getMonth().toString(), n);
            }
        }

        return map;
    }

    private Integer countResPerMonthCottage(String month, Integer year, Long id){
        Integer k = 0;
        for(ReservationCottage r: reservationCottageRepository.findAll()){
            if(id.equals(r.getCottageId()) && r.getStartDate().getMonth().toString().equals(month) && r.getStartDate().getYear() == year){
                k++;
            }
        }
        return k;
    }

    public Map<Integer, Integer> numReservationYearCottage(String id){
        Map<Integer,Integer> map=new HashMap<>();
        for(ReservationCottage r: reservationCottageRepository.findAll()){
            if(!map.containsKey(r.getStartDate().getYear())){
                Integer n = countResPerYearCottage(r.getStartDate().getYear(), Long.valueOf(id));
                map.put(r.getStartDate().getYear(), n);
            }
        }

        return map;
    }

    private Integer countResPerYearCottage(Integer year, Long id){
        Integer k = 0;
        for(ReservationCottage r: reservationCottageRepository.findAll()){
            if(id.equals(r.getCottageId()) && r.getStartDate().getYear() == year){
                k++;
            }
        }
        return k;
    }

    public Map<String, Integer> numReservationSpecWeekCottage(WeekDTO dto){
        Map<String, Integer> map = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while(start.isBefore(end) || start.isEqual(end)) {
            Integer n = countResSelecWeekCottage(start, dto.getId());
            map.put(start.toString().substring(0,10), n);
            start = start.plusDays(1);
        }

        return map;
    }

    private Integer countResSelecWeekCottage(LocalDateTime date, Long id){
        Integer n = 0;
        for(ReservationCottage r: reservationCottageRepository.findAll()){
            if(r.getStartDate().isEqual(ChronoLocalDate.from(date)) && id.equals(r.getCottageId())){
                n++;
            }
        }
        return n;
    }

    public Integer numReseWeeklyCottage(WeekDTO dto){
        Integer n = 0;
        for(ReservationCottage r: reservationCottageRepository.findAll()){
            if(r.getStartDate().isAfter(ChronoLocalDate.from(findDate(dto.getStartDate()))) && r.getStartDate().isBefore(ChronoLocalDate.from(findDate(dto.getEndDate())))){n++;}
        }
        return n;
    }

    public Map<String, Double>getIncomeInSpecificPeriod(WeekDTO dto){
        Map<String, Double> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while(start.isBefore(end) || start.isEqual(end)) {
            for (ReservationCottage r : reservationCottageRepository.findAll()) {
                    Double n = countIncome(start, dto.getId());
                    ret.put(start.toString().substring(0,10), n);
            }
            start = start.plusDays(1);
        }
        return  ret;
    }

    private Double countIncome(LocalDateTime date, Long id){
        Double income = 0.0;

        for(ReservationCottage r: reservationCottageRepository.findAll()){
            if(r.getStartDate().isEqual(ChronoLocalDate.from(date)) && id.equals(r.getCottageId())){
                income += r.getPrice();
            }
        }

        return income;
    }

    private Boolean checkNewReservation(AvaliableReservations newAvaliableRes){
        List<AvaliableReservations> reservations = avaliableReservationsRepository.findAll();
        for (AvaliableReservations reservation : reservations) {
            if(reservation.getEntityId() == newAvaliableRes.getEntityId() && reservation.getEntity() == newAvaliableRes.getEntity()) {
                if (newAvaliableRes.getStartDate().isAfter(reservation.getEndDate()) || newAvaliableRes.getEndDate().isBefore(reservation.getStartDate())) {
                }
                //ako se opseg nove rezervacije poklapa sa nekim datumom postojece rezervacije, onda ne pravim novu rezervaciju
                else return false;
            }
        }return true;
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


//Za brodove

    public Map<String, Integer> numReservationMontBoats(String id, String year){
        Map<String,Integer> map=new HashMap<>();
        for(ReservationBoat r: reservationBoatRepository.findAll()){
            if(!map.containsKey(r.getStartDate().getMonth().toString())){
                Integer n = countResPerMonthBoat(r.getStartDate().getMonth().toString(), Integer.parseInt(year), Long.valueOf(id));
                map.put(r.getStartDate().getMonth().toString(), n);
            }
        }

        return map;
    }

    private Integer countResPerMonthBoat(String month, Integer year, Long id){
        Integer k = 0;
        for(ReservationBoat r: reservationBoatRepository.findAll()){
            if(id.equals(r.getBoatId()) && r.getStartDate().getMonth().toString().equals(month) && r.getStartDate().getYear() == year){
                k++;
            }
        }
        return k;
    }

    public Map<Integer, Integer> numReservationYearBoat(String id){
        Map<Integer,Integer> map=new HashMap<>();
        for(ReservationBoat r: reservationBoatRepository.findAll()){
            if(!map.containsKey(r.getStartDate().getYear())){
                Integer n = countResPerYearBoat(r.getStartDate().getYear(), Long.valueOf(id));
                map.put(r.getStartDate().getYear(), n);
            }
        }

        return map;
    }

    private Integer countResPerYearBoat(Integer year, Long id){
        Integer k = 0;
        for(ReservationBoat r: reservationBoatRepository.findAll()){
            if(id.equals(r.getBoatId()) && r.getStartDate().getYear() == year){
                k++;
            }
        }
        return k;
    }

    public Map<String, Integer> numReservationSpecWeekBoat(WeekDTO dto){
        Map<String, Integer> map = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while(start.isBefore(end) || start.isEqual(end)) {
            Integer n = countResSelecWeekBoat(start, dto.getId());
            map.put(start.toString().substring(0,10), n);
            start = start.plusDays(1);
        }

        return map;
    }

    private Integer countResSelecWeekBoat(LocalDateTime date, Long id){
        Integer n = 0;
        for(ReservationBoat r: reservationBoatRepository.findAll()){
            if(r.getStartDate().isEqual(ChronoLocalDate.from(date)) && id.equals(r.getBoatId())){
                n++;
            }
        }
        return n;
    }

    public Integer numReseWeeklyBoat(WeekDTO dto){
        Integer n = 0;
        for(ReservationBoat r: reservationBoatRepository.findAll()){
            if(r.getStartDate().isAfter(ChronoLocalDate.from(findDate(dto.getStartDate()))) && r.getStartDate().isBefore(ChronoLocalDate.from(findDate(dto.getEndDate())))){n++;}
        }
        return n;
    }

    public Map<String, Double>getIncomeInSpecificRange(WeekDTO dto){
        Map<String, Double> ret = new HashMap<>();
        LocalDateTime start = findDate(dto.getStartDate());
        LocalDateTime end = findDate(dto.getEndDate());

        while(start.isBefore(end) || start.isEqual(end)) {
            for (ReservationBoat r : reservationBoatRepository.findAll()) {
                Double n = countInC(start, dto.getId());
                ret.put(start.toString().substring(0,10), n);
            }
            start = start.plusDays(1);
        }
        return  ret;
    }

    private Double countInC(LocalDateTime date, Long id){
        Double income = 0.0;

        for(ReservationBoat r: reservationBoatRepository.findAll()){
            if(r.getStartDate().isEqual(ChronoLocalDate.from(date)) && id.equals(r.getBoatId())){
                income += r.getPrice();
            }
        }

        return income;
    }

}