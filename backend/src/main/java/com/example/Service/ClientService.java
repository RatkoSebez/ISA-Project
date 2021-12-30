package com.example.Service;

import com.example.Model.User;
import com.example.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {
    private final UserRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registerClient(User client){
        boolean userExists = clientRepository.findByUsername(client.getUsername()).isPresent();
        if(userExists){
            throw new IllegalStateException("username already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        clientRepository.save(client);
        return "registration works";
    }
}
