package com.example.service;


import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministratorService {

    @Autowired
    private final UserRepository administratorRepository;


    public AdministratorService(UserRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }



    public List<User> getAdministrators() {
        List<User> listAllUsers = administratorRepository.findAll();
        List<User> administrators = new ArrayList<User>();

        for (int i = 0; i < listAllUsers.size(); i++) {
            if(listAllUsers.get(i).getRole().equals(UserRole.ROLE_ADMIN)){
                administrators.add(listAllUsers.get(i));
            }
        }
        return administrators;

    }
}
