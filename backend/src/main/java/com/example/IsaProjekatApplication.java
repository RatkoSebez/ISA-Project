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
        userRepository.save(new User("instructor@gmail.com", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_FISHINGI));
        userRepository.save(new User("isaprojmejl2@gmail.com", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_BOATOWNER));
        userRepository.save(new User("cottageOwner@gmail.com", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_WEEKENDCOTTOWNER));
        List<Adventure> adventures = new ArrayList<>();
        adventures.add(new Adventure("avantura1",   "adresa", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L));
        adventures.add(new Adventure("avantura2", "adresa", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L));
        adventures.add(new Adventure("avantura3", "adresa", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L));
        adventures.add(new Adventure("avantura4", "adresa", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L));
        adventures.add(new Adventure("avantura5", "adresa", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L));
        userRepository.save(new User("$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", "fishin@gmail.com", "name", "prezime", "adresa", "grad", "drzava", "1235", UserRole.ROLE_FISHINGI, adventures));
        // userRepository.save(new User("user2", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_ADMIN));
        userRepository.save(new User("usernameADMINNNn", "123", "administrator@gmail.com", "ImeAdmin", "PrezimeAdmin", "AdresaAdmin", "NS", "Serb", "12412412", UserRole.ROLE_ADMIN, false));

        //FishingInstructor
        List<FishingService>  fishingServiceList = new ArrayList();
        fishingServiceList.add( new FishingService("fishingServ", "addd", "des", "about", "img", 4, null, "ee", "equp", "pricelis", "info", "cancel"));
        userRepository.save(new User("uuuuu", "eeeee", "kkk", "ttt", "eee", "tyyyy", "assfa", "asfsf", "1assfasf", UserRole.ROLE_FISHINGI, false, fishingServiceList));
        //
        List<ReservationBoat> reservationsBoat = new ArrayList<>();
        reservationsBoat.add(new ReservationBoat(LocalDate.of(2022, 1, 7), LocalDate.of(2022, 1, 9), "user@gmail.com"));
        reservationsBoat.add(new ReservationBoat(LocalDate.of(2022, 1, 10), LocalDate.of(2022, 1, 11), "user@gmail.com"));
        List<ReservationCottage> reservationsCottage = new ArrayList<>();
        reservationsCottage.add(new ReservationCottage(LocalDate.of(2022, 1, 7), LocalDate.of(2022, 1, 9), "user@gmail.com"));
        reservationsCottage.add(new ReservationCottage(LocalDate.of(2022, 1, 10), LocalDate.of(2022, 1, 11), "user@gmail.com"));
        weekendCottageRepository.save(new WeekendCottage("vikendica", "adresa", "ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg", 4L));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg", 4L, reservationsCottage));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg", 4L));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg", 4L));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila", "https://cdn.pixabay.com/photo/2016/12/06/14/33/log-cabin-1886620_960_720.jpg", 4L));
        boatRepository.save(new Boat("brod1", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi", 3L, reservationsBoat));
        boatRepository.save(new Boat("brod2", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi", 3L));
        boatRepository.save(new Boat("brod3", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi", 3L));
        boatRepository.save(new Boat("brod4", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi", 3L));
        boatRepository.save(new Boat("brod5", "adresa", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", "dodatni servisi", 3L));
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
