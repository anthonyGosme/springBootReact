package com.packt.cardatabase.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity // disable for constructor generator to work

@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carid;

    private LocalDateTime localDateTime ;
    // ONeToMany done in Owner class
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    //@ManyToMany(mappedBy = "cars")
    //private Set<Owner> owners;

    private String brand, model, color, registerNumber;
    private int year, price;
    LocalTime localTime ;
    public Car() {
    }

    public Car( LocalTime localTime,String brand, String model, String color, String registerNumber,
               int year, int price, Owner  owner) {
        super();
        this.localTime = localTime;
        this.brand = brand;

        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.owner = owner;
        // this.owners = owners;
    }
}
