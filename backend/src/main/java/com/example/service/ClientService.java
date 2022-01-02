package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerClient(UserDTO clientDTO){
        boolean userExists = userRepository.findByEmail(clientDTO.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("email already taken");
        }
        User newClient = new User(clientDTO.getUsername(), clientDTO.getPassword(), clientDTO.getEmail(),
                clientDTO.getFirstName(), clientDTO.getLastName(), clientDTO.getAddress(), clientDTO.getCity(), clientDTO.getCountry(),
                clientDTO.getPhoneNumber(), clientDTO.getRole(), false);

        String encodedPassword = bCryptPasswordEncoder.encode(newClient.getPassword());
        newClient.setPassword(encodedPassword);
        userRepository.save(newClient);
        return newClient;
    }
}
