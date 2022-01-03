package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(UserDTO userDTO){
        User newUser = null;
        boolean userExists = userRepository.findByEmail(userDTO.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("email already taken");
        }
        if(userDTO.getRole() == UserRole.ROLE_CLIENT){
            newUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(),
                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(), userDTO.getCity(), userDTO.getCountry(),
                    userDTO.getPhoneNumber(), userDTO.getRole(), false);
        }
        else{
            newUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(),
                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(), userDTO.getCity(), userDTO.getCountry(),
                    userDTO.getPhoneNumber(), userDTO.getRole(), true);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        userRepository.save(newUser);
        return newUser;
    }
}
