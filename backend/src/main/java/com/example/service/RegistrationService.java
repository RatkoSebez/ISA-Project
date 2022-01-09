package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.*;
import com.example.repository.RegistrationRequestRepository;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RegistrationRequestRepository requestRepository;
    private final JavaMailSender javaMailSender;

    public User registerUser(UserDTO userDTO){
        User newUser = null;
        boolean userExists = userRepository.findByEmail(userDTO.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("email already taken");
        }
        if(userDTO.getRole() == UserRole.ROLE_ADMIN){
            newUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(),
                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(), userDTO.getCity(), userDTO.getCountry(),
                    userDTO.getPhoneNumber(), userDTO.getRole(), false);
        }
        else{
            RegistrationRequest newRR = new RegistrationRequest(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(),
                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(), userDTO.getCity(), userDTO.getCountry(),
                    userDTO.getPhoneNumber(), userDTO.getRole(), true, userDTO.getExplanationOfRegistration());
            newUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(),
                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(), userDTO.getCity(), userDTO.getCountry(),
                    userDTO.getPhoneNumber(), userDTO.getRole(), true, new ArrayList< FishingService >());
            requestRepository.save(newRR);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        if(newUser.getRole() == UserRole.ROLE_CLIENT) sendConfirmationEmail(newUser);
        newUser.setLoyaltyCategory(LoyaltyCategory.REGULAR);
        newUser.setLoyaltyBenefits("Since you are in regular loyalty mode, you do not have any extra discounts.");
        newUser.setLoyaltyPoints(10L);
        userRepository.save(newUser);
        return newUser;
    }

    private void sendConfirmationEmail(User user){
        user.setEnabled(false);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("isaprojmejl@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Please confirm your email address.");
        String uniqueID = UUID.randomUUID().toString();
        user.setVerificationCode(uniqueID);
        userRepository.save(user);
        message.setText("http://localhost:8080/api/user/confirmEmail?code=" + uniqueID);
        javaMailSender.send(message);
        System.out.println("mejl radi");
    }
}
