package com.example;

import com.example.model.User;
import com.example.model.UserRole;
import com.example.model.WeekendCottage;
import com.example.repository.UserRepository;
import com.example.repository.WeekendCottageRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class IsaProjekatApplication implements ApplicationRunner {

    private UserRepository userRepository;
    private WeekendCottageRepository weekendCottageRepository;

    //@Autowired
    //public IsaProjekatApplication(UserRepository userRepository) {
    //    this.userRepository = userRepository;
    //}

    public static void main(String[] args) {
        SpringApplication.run(IsaProjekatApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new User("user", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_CLIENT));
        userRepository.save(new User("user2", "$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", UserRole.ROLE_ADMIN));
        weekendCottageRepository.save(new WeekendCottage("vikendica", "adresa", "ovo je opis vikendice", 4.5, "pravila"));
        weekendCottageRepository.save(new WeekendCottage("vikendica2", "adresa2", "ovo je opis vikendice", 4.5, "pravila"));
        weekendCottageRepository.save(new WeekendCottage("vikendica3", "adresa2", "ovo je opis vikendice", 4.5, "pravila"));
        weekendCottageRepository.save(new WeekendCottage("vikendica4", "adresa2", "ovo je opis vikendice", 4.5, "pravila"));
        weekendCottageRepository.save(new WeekendCottage("vikendica5", "adresa2", "ovo je opis vikendice", 4.5, "pravila"));
    }
}
