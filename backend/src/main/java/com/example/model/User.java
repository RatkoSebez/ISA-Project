package com.example.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.sql.Delete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Entity
@Getter
@Setter
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
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String verificationCode = "";
    private UserRole role;
    private Boolean locked = false;
    private Boolean enabled = true;
    private LoyaltyCategory loyaltyCategory;
    private Long loyaltyPoints;
    private String loyaltyBenefits;
    @OneToMany(targetEntity = FishingService.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FishingService> fishingServiceList;
    @OneToMany(targetEntity = UserSubscription.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserSubscription> subscriptions;
    @OneToMany(targetEntity = AvailabilityFishingInstructor.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fishing_instr_id", referencedColumnName = "id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AvailabilityFishingInstructor> availabilityFishingInstructorList;
    @OneToMany(targetEntity = Adventure.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fishing_instr_id", referencedColumnName = "id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Adventure> adventures;
    @OneToMany(targetEntity = PotentialComplaint.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PotentialComplaint> potentialComplaints;

    //administrator
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

    //FishingInstructor
    public User(String username, String password, String email, String firstName, String lastName, String address, String city, String country, String phoneNumber, UserRole role, Boolean locked, List<FishingService> fishingServiceList) {
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
        this.fishingServiceList = fishingServiceList;
        this.availabilityFishingInstructorList = new ArrayList<AvailabilityFishingInstructor>();
    }

    //FishingInstructor2
    public User(String password, String email, String firstName, String lastName, String address, String city, String country, String phoneNumber, UserRole role, List<Adventure> adventures) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.adventures = adventures;
    }

    //Client
    public User(String password, String email, String firstName, String lastName, String address, String city, String country, String phoneNumber, Long id, UserRole role, LoyaltyCategory loyaltyCategory, Long loyaltyPoints, String loyaltyBenefits) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.role = role;
        this.loyaltyCategory = loyaltyCategory;
        this.loyaltyBenefits = loyaltyBenefits;
        this.loyaltyPoints = loyaltyPoints;
    }

    //Client2
    public User(String password, String email, String firstName, String lastName, String address, String city, String country, String phoneNumber, UserRole role, LoyaltyCategory loyaltyCategory, Long loyaltyPoints, String loyaltyBenefits) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.loyaltyCategory = loyaltyCategory;
        this.loyaltyPoints = loyaltyPoints;
        this.loyaltyBenefits = loyaltyBenefits;
    }

    public User(String email, String password, UserRole role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(this.role.name()));
        return list;
    }

//    public void addWeekendCottage(WeekendCottage cott){
//        cottages.add(cott);
//        cott.setUser(this);
//    }
//
//    public void removeWeekendCottage(WeekendCottage cott){
//        cottages.remove(cott);
//        cott.setUser(null);
//    }

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
        return this.enabled;
    }

    public void setFirstName(String name){
        this.firstName = name;
    }
}
