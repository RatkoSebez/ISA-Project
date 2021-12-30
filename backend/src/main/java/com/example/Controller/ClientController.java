package com.example.Controller;

import com.example.Model.User;
import com.example.Service.ClientService;
import com.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping()
    public User testApi(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((User)principal).getUsername();
        return (User)principal;
    }

    @GetMapping("test")
    @PreAuthorize("hasRole('CLIENT')")
    public String test(){
        return "klijent je ulogovan";
    }

    @GetMapping("test2")
    public String test2(){
        return "klijent je ulogovan";
    }

    @PostMapping()
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<User> registerClient(@RequestBody User user) {
        clientService.registerClient(user);
        // System.out.println(user.getUsername());
        // System.out.println("test");
        // return "radi";
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return userResponseEntity;
    }
}
