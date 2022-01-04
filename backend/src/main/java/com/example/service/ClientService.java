package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User editUser(User user){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User oldUser = (User)principal;
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        if(user.getPassword().equals("")) encodedPassword = oldUser.getPassword();
        User newUser = new User(encodedPassword, user.getEmail(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getCity(), user.getCountry(), user.getPhoneNumber(), oldUser.getId(), UserRole.ROLE_CLIENT);
        userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, newUser.getPassword(), newUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);      
        return newUser;
    }
}
