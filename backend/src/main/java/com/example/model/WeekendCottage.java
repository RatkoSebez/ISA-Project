package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class WeekendCottage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @Column(columnDefinition="TEXT")
    private String description;
    private Double rating;
    private String image;
    @OneToMany(targetEntity = ReservationCottage.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "foreign_key", referencedColumnName = "id")
    private List<ReservationCottage> reservations;
    @Column(columnDefinition="TEXT")
    private String rulesOfConduct;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }
//    private String priceList;

    public WeekendCottage(String name, String address, String description, Double rating, String rulesOfConduct, String image){
        this.name = name;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.rulesOfConduct = rulesOfConduct;
        this.image = image;
    }

    public WeekendCottage(String name, String address, String description, Double rating, String rulesOfConduct, String image, List<ReservationCottage> reservations){
        this.name = name;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.rulesOfConduct = rulesOfConduct;
        this.image = image;
        this.reservations = reservations;
    }
}
