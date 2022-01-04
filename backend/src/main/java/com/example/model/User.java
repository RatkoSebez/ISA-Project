package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.servlet.Registration;
import java.util.*;

// treba dodati promenljivu koja ce drzati listu rola
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username;
    private String password;
    private String email; // novo
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private UserRole role;
    private Boolean locked = false;
    private Boolean enabled = false;
    private Boolean isNotLocked;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<WeekendCottage> cottages = new HashSet<WeekendCottage>();

    //Administrator
    public User(String username, String password, String email, String firstName, String lastName, String address, String city, String country, String phoneNumber, UserRole role, Boolean locked, Set<RegistrationRequest> listRegistrationRequests) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.locked = locked;
    }

    //FishingInstructor
    public User(String username, String password, String email, String firstName, String lastName, String address, String city, String country, String phoneNumber, UserRole role, Boolean locked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.locked = locked;
    }

    public User(String email, String password, UserRole role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        // grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.role));
        // return this.grantedAuthoritiesList;
        //return new ArrayList<>();
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(this.role.name()));

        return list;
    }

    public void addWeekendCottage(WeekendCottage cott){
        cottages.add(cott);
        cott.setUser(this);
    }

    public void removeWeekendCottage(WeekendCottage cott){
        cottages.remove(cott);
        cott.setUser(null);
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
