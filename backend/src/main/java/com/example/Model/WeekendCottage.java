package com.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
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
    private String description;
    private Double rating;
//    @OneToMany(targetEntity = String.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "cottage_fk", referencedColumnName = "id")
//    @ElementCollection
//    private List<String> interiorImages;
//    @ElementCollection
//    private List<String> exteriorImages;
//    private Integer numberOfRooms;
//    @ElementCollection
//    private List<Integer> numberOfBedsInRoom;
    private String rulesOfConduct;
//    private String priceList;

    public WeekendCottage(String name, String address, String description, Double rating, String rulesOfConduct){
        this.name = name;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.rulesOfConduct = rulesOfConduct;
    }
}
