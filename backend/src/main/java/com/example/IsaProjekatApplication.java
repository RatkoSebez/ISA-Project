package com.example;

import com.example.model.*;
import com.example.repository.BoatRepository;
import com.example.repository.ReservationBoatRepository;
import com.example.repository.UserRepository;
import com.example.repository.WeekendCottageRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
@AllArgsConstructor
public class IsaProjekatApplication implements ApplicationRunner {

    private UserRepository userRepository;
    private WeekendCottageRepository weekendCottageRepository;
    private BoatRepository boatRepository;
    private ReservationBoatRepository reservationBoatRepository;

    public static void main(String[] args) {
        SpringApplication.run(IsaProjekatApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.save(new User("user@gmail.com", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_CLIENT));
       // userRepository.save(new User("user2", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_ADMIN));
        userRepository.save(new User("usernameADMINNNn", "123", "administrator@gmail.com", "ImeAdmin", "PrezimeAdmin", "AdresaAdmin", "NS", "Serb", "12412412", UserRole.ROLE_ADMIN, false));

        //FishingInstructor
        List<FishingService>  fishingServiceList = new ArrayList();
        fishingServiceList.add( new FishingService("fishingServ", "addd", "des", "about", "img", 4, null, "ee", "equp", "pricelis", "info", "cancel"));
        userRepository.save(new User("uuuuu", "eeeee", "kkk", "ttt", "eee", "tyyyy", "assfa", "asfsf", "1assfasf", UserRole.ROLE_FISHINGI, false, fishingServiceList));
        //
        weekendCottageRepository.save(new WeekendCottage("vikendica", "adresa", "ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg"));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg"));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg"));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg"));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg"));
        List<ReservationBoat> reservations = new ArrayList<>();
        reservations.add(new ReservationBoat(LocalDate.of(2022, 1, 7), LocalDate.of(2022, 1, 9), "user@gmail.com"));
        reservations.add(new ReservationBoat(LocalDate.of(2022, 1, 10), LocalDate.of(2022, 1, 11), "user@gmail.com"));
        boatRepository.save(new Boat("brod1", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi", reservations));
        boatRepository.save(new Boat("brod2", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi"));
        boatRepository.save(new Boat("brod3", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi"));
        boatRepository.save(new Boat("brod4", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi"));
        boatRepository.save(new Boat("brod5", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi"));
//        List<ReservationBoat> reservations = new ArrayList<>();
//        reservations.add(new ReservationBoat(LocalDate.of(2000, 2, 3), LocalDate.of(1999, 6, 21), "user@gmail.com"));
//        Boat brod = new Boat("brod", reservations);
//        boatRepository.save(brod);
//        List<ReservationBoat> reservations2 = brod.getReservations();
//        for(ReservationBoat reservation : reservations2){
//            System.out.println("-------------------" + reservation.getId());
//            System.out.println("-------------------" + reservation.getStartDate());
//            System.out.println("-------------------" + reservation.getEndDate());
//        }
    }
}
