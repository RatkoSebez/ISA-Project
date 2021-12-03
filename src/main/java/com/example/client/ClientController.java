package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class ClientController {

    @Autowired
    private ClientService clientService;

    // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "api/client")
    public String testApi(){
        // System.out.println("ejjj");
        // return new Client("emial", "sifra", "ime", "prezime");
        return "proba";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping(path = "api/client")
    public String postTest(@RequestBody Client client) {
        clientService.registerClient(client);
        System.out.println(client.getEmail());
        return "radi";
        // return new ResponseEntity<>(new Client(), HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public void login(){
//
//    }

}
