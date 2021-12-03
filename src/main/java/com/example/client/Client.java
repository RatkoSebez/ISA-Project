package com.example.client;

import com.example.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements UserDetails {
    @Id
//    @SequenceGenerator(
//            name = "client_sequence",
//            sequenceName = "client_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "client_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String[] roles; // ROLE_USER{ read, edit }, ROLE_ADMIN
    private String[] authorities;
    private Boolean isNotLocked;
    //private List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public Client(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
