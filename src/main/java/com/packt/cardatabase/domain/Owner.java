package com.packt.cardatabase.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // disable for constructor generator to work
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ownerid;

    // cascade delete = all,   mappedBy=owner -> the car clas has a foeign field owner
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
   // private List<Car> cars;

    // cascade delelete = all,   mappedBy=owner -> the car clas has a foeign field owner
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "car_owner", joinColumns = {@JoinColumn(name = "ownerid")}, inverseJoinColumns = {@JoinColumn(name="id")})
    private Set<Car> cars = new HashSet<Car>() ;
    private String firstname, lastname;


    public Owner(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Owner() {
    }
}
