package com.example;

import com.example.model.*;
import com.example.repository.*;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Addition;
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
    private FastReservationRepository fastReservationRepository;

    public static void main(String[] args) {
        SpringApplication.run(IsaProjekatApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //userRepository.save(new User("user@gmail.com", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_CLIENT));
        userRepository.save(new User("$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", "isaprojmejl2@gmail.com", "ime", "prezime", "adresa", "grad", "drzava", "1325648655", UserRole.ROLE_CLIENT, LoyaltyCategory.REGULAR, 100L, "Since you are in regular loyalty mode, you do not have any extra discounts."));
        userRepository.save(new User("user@gmail.com", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_FISHINGI));
        userRepository.save(new User("test@gmail.com", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_BOATOWNER));
        userRepository.save(new User("test@gmail.com", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_WEEKENDCOTTOWNER));
        String additionalServices = "usluga1, usluga2, usluga3";
        List<Adventure> adventures = new ArrayList<>();
        adventures.add(new Adventure("avantura1",   "adresa1", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L, 4.0, additionalServices));
        adventures.add(new Adventure("avantura2", "adresa2", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L, 5.0, additionalServices));
        adventures.add(new Adventure("avantura3", "adresa3", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L, 4.25, additionalServices));
        adventures.add(new Adventure("avantura4", "adresa4", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L, 3.3, additionalServices));
        adventures.add(new Adventure("avantura5", "adresa5", "opis", "biografija instruktora pecanja", "https://cdn.pixabay.com/photo/2018/01/05/02/47/fishing-3062034_960_720.jpg", 5, "cenovnik", 2L, 4.6, additionalServices));
        userRepository.save(new User("$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", "fishin@gmail.com", "name", "prezime", "adresa", "grad", "drzava", "1235", UserRole.ROLE_FISHINGI, adventures));
        // userRepository.save(new User("user2", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_ADMIN));
        userRepository.save(new User("usernameADMINNNn", "123", "administrator@gmail.com", "ImeAdmin", "PrezimeAdmin", "AdresaAdmin", "NS", "Serb", "12412412", UserRole.ROLE_ADMIN, false));
        userRepository.save(new User("123", "$2a$10$K/udAutzf0mwWsNiZgsCme2i9KJ/15sqeqDZL7TWR/Lkc7Ii.BSye", "123", "VlasnikVikendice", "Cottage", "AdresaAdmin", "NS", "Serb", "12412412", UserRole.ROLE_WEEKENDCOTTOWNER, false));

        //FishingInstructor
        List<FishingService>  fishingServiceList = new ArrayList();
        fishingServiceList.add( new FishingService("fishingServ", "addd", "des", "about", "img", 4, null, "ee", "equp", "pricelis", "info", "cancel"));
        userRepository.save(new User("uuuuu", "eeeee", "kkk", "ttt", "eee", "tyyyy", "assfa", "asfsf", "1assfasf", UserRole.ROLE_FISHINGI, false, fishingServiceList));
        //
        List<ReservationBoat> reservationsBoat = new ArrayList<>();
        reservationsBoat.add(new ReservationBoat(LocalDate.of(2022, 1, 7), LocalDate.of(2022, 1, 9), "user@gmail.com", 60.0, 5, "usluga1"));
        reservationsBoat.add(new ReservationBoat(LocalDate.of(2022, 1, 10), LocalDate.of(2022, 1, 11), "user@gmail.com", 55.0, 3, "usluga1"));
        List<ReservationCottage> reservationsCottage = new ArrayList<>();
        reservationsCottage.add(new ReservationCottage(LocalDate.of(2022, 1, 7), LocalDate.of(2022, 1, 9), "user@gmail.com", 20.0, 3, "usluga1"));
        reservationsCottage.add(new ReservationCottage(LocalDate.of(2022, 1, 10), LocalDate.of(2022, 1, 11), "user@gmail.com", 50.0, 3, "usluga1"));
        weekendCottageRepository.save(new WeekendCottage("vikendica", "adresa6", "ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ovo je opis vikendice ", 4.5, "pravila", "siki.jpg", 4L, "cenovnik", additionalServices));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.55, "pravila", "liki.jpg", 4L, reservationsCottage, "cenovnik", additionalServices));
        weekendCottageRepository.save(new WeekendCottage("vikendica3", "adresa4", "ovo je opis vikendice", 3.5, "pravila", "siki.jpg", 4L, "cenovnik", additionalServices));
        weekendCottageRepository.save(new WeekendCottage("vikendica4", "adresa3", "ovo je opis vikendice", 4.0, "pravila", "liki.jpg", 4L, "cenovnik", additionalServices));
        weekendCottageRepository.save(new WeekendCottage("vikendica5", "adresa7", "ovo je opis vikendice", 3.2, "pravila", "vikendica.jpg", 4L, "cenovnik", additionalServices));
        boatRepository.save(new Boat("brod1", "adresa2", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.8, "cenovnik", additionalServices, 3L, reservationsBoat));
        boatRepository.save(new Boat("brod2", "adresa8", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.1, "cenovnik", additionalServices, 3L));
        boatRepository.save(new Boat("brod3", "adresa3", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", additionalServices, 3L));
        boatRepository.save(new Boat("brod4", "adresa1", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 3.2, "cenovnik", additionalServices, 3L));
        boatRepository.save(new Boat("brod5", "adresa6", "opis", "https://images.pexels.com/photos/6585322/pexels-photo-6585322.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 5, 4.2, "cenovnik", additionalServices, 3L));
        fastReservationRepository.save(new FastReservation(Entity.BOAT, 25L, LocalDate.of(2022, 1, 25), LocalDate.of(2022, 1, 29), "isaprojmejl2@gmail.com", 50.0, 40.0));
        fastReservationRepository.save(new FastReservation(Entity.WEEKEND_COTTAGE, 19L, LocalDate.of(2022, 1, 25), LocalDate.of(2022, 1, 29), "isaprojmejl2@gmail.com", 50.0, 40.0));
        fastReservationRepository.save(new FastReservation(Entity.ADVENTURE, 8L, LocalDate.of(2022, 1, 25), LocalDate.of(2022, 1, 29), "isaprojmejl2@gmail.com", 50.0, 40.0));//        List<ReservationBoat> reservations = new ArrayList<>();
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
