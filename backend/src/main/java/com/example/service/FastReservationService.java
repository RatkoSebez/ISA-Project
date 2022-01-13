package com.example.service;

import com.example.model.Entity;
import com.example.model.FastReservation;
import com.example.repository.FastReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FastReservationService {
    private final FastReservationRepository fastReservationRepository;

    public List<FastReservation> getFastReservationsBoat(){
        List<FastReservation> result = new ArrayList<>();
        List<FastReservation> fastReservations = fastReservationRepository.findAll();
        for(FastReservation fastReservation : fastReservations){
            if(fastReservation.getEntity() == Entity.BOAT) result.add(fastReservation);
        }
        return result;
    }

    public List<FastReservation> getFastReservationsCottage(){
        List<FastReservation> result = new ArrayList<>();
        List<FastReservation> fastReservations = fastReservationRepository.findAll();
        for(FastReservation fastReservation : fastReservations){
            if(fastReservation.getEntity() == Entity.WEEKEND_COTTAGE) result.add(fastReservation);
        }
        return result;
    }

    public List<FastReservation> getFastReservationsAdventure(){
        List<FastReservation> result = new ArrayList<>();
        List<FastReservation> fastReservations = fastReservationRepository.findAll();
        for(FastReservation fastReservation : fastReservations){
            if(fastReservation.getEntity() == Entity.ADVENTURE) result.add(fastReservation);
        }
        return result;
    }
}
