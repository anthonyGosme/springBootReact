package com.packt.cardatabase.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity // disable for constructor generator to work

@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carid;

    // ONeToMany done in Owner class
   // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "owner")
    //private Owner owner;

    @ManyToMany(mappedBy = "cars")
    private Set<Owner> owners;

    private String brand, model, color, registerNumber;
    private int year, price;

    public Car() {
    }

    public Car(String brand, String model, String color, String registerNumber,
               int year, int price, Set<Owner>  owners) {
        super();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.owners = owners;
    }
}
