package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
//    @OneToMany(targetEntity = String.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "cottage_fk", referencedColumnName = "id")
//    @ElementCollection
//    private List<String> interiorImages;
//    @ElementCollection
//    private List<String> exteriorImages;
//    private Integer numberOfRooms;
//    @ElementCollection
//    private List<Integer> numberOfBedsInRoom;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "owner_id")
//    private User user;

    @Column(columnDefinition="TEXT")
    private String rulesOfConduct;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//    private String priceList;

    public WeekendCottage(String name, String address, String description, Double rating, String rulesOfConduct, String image){
        this.name = name;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.rulesOfConduct = rulesOfConduct;
        this.image = image;
    }
}
