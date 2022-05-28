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
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

    public User registerUser(UserDTO userDTO) throws MessagingException {
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
        sendConfirmationEmail(newUser);
        newUser.setLoyaltyCategory(LoyaltyCategory.REGULAR);
        newUser.setLoyaltyBenefits("Since you are in regular loyalty mode, you do not have any extra discounts.");
        newUser.setLoyaltyPoints(10L);
        userRepository.save(newUser);
        return newUser;
    }

    private void sendConfirmationEmail(User user) throws MessagingException {
        user.setEnabled(false);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        SimpleMailMessage message = new SimpleMailMessage();
        String uniqueID = UUID.randomUUID().toString();
        user.setVerificationCode(uniqueID);
        userRepository.save(user);
        String htmlMsg = "<div style=\"background: linear-gradient(#6f87d6 , #48c6ef); height: 320px; width: 500px; font-family: Montserrat, sans-serif; text-align: center; align-items: center; color: white; margin: 10px; padding: 4px; -webkit-box-shadow: 0px 7px 12px -6px rgba(0,0,0,0.62); box-shadow: 0px 7px 12px -6px rgba(0,0,0,0.62); border-radius: 10px;\">\n" +
                "\t\t\t<h1>Welcome to Fishing</h1>\n" +
                "\t\t\t<h3 style=\"font-weight: normal;\">Verify <b>" + user.getFirstName() + "</b> account</h3>\n" +
                "\n" +
                "\t\t\t<a href=\" http://localhost:8080/api/user/confirmEmail?code="+ uniqueID + "\" style=\"box-shadow: -2px 7px 4px 0px #004cff18;\n" +
                "\t\t\tbackground-color:#ffffff;\n" +
                "\t\t\tborder-radius:10px;\n" +
                "\t\t\tborder-width: 0;\n" +
                "\t\t\tdisplay:inline-block;\n" +
                "\t\t\tcursor:pointer;\n" +
                "\t\t\tcolor:#48c5ef;\n" +
                "\t\t\tfont-family:Arial;\n" +
                "\t\t\tfont-size:20px;\n" +
                "\t\t\tfont-weight:bold;\n" +
                "\t\t\tpadding:14px 50px;\n" +
                "\t\t\ttext-decoration:none;\n" +
                "\t\t\ttext-shadow:0px 2px 10px #48c5ef;\">Verify</a>\n" +
                "\t\t</div> ";
        //message.setText(htmlMsg, true);
        helper.setText(htmlMsg, true);
        helper.setTo(user.getEmail());
        helper.setSubject("Please confirm your email address.");
        helper.setFrom("dislinkacc@outlook.com");
        javaMailSender.send(mimeMessage);
        //javaMailSender.send(message);
        System.out.println("mejl radi");
    }
}
