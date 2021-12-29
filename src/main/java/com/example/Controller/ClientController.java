package com.example.Controller;

import com.example.Model.User;
import com.example.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String testApi(){
        // System.out.println("ejjj");
        // return new Client("emial", "sifra", "ime", "prezime");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((User)principal).getUsername();
//        List<GrantedAuthority> authorityes = ((Client)principal).getGrantedAuthorities();
//        for(GrantedAuthority authority : authorityes){
//            System.out.println("e");
//            System.out.println(authority);
//        }
        //System.out.println("e");
        return username;
    }

    @GetMapping("test")
    @PreAuthorize("hasRole('CLIENT')")
    public String test(){
        return "klijent je ulogovan";
    }

    @PostMapping()
    public String postTest(@RequestBody User client) {
        clientService.registerClient(client);
        System.out.println(client.getUsername());
        return "radi";
        // return new ResponseEntity<>(new Client(), HttpStatus.CREATED);
    }
}
