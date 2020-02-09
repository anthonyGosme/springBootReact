package com.packt.cardatabase.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity // disable for constructor generator to work
@Getter
@Setter
public class Car {
  LocalTime localTime;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long carid;

  private LocalDateTime localDateTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "owner")
  private Owner owner;

  private String brand, model, color, registerNumber;
  private int year, price;

  public Car() {}

  public Car(
      LocalTime localTime,
      String brand,
      String model,
      String color,
      String registerNumber,
      int year,
      int price,
      Owner owner) {
    super();
    this.localTime = localTime;
    this.brand = brand;

    this.model = model;
    this.color = color;
    this.registerNumber = registerNumber;
    this.year = year;
    this.price = price;
    this.owner = owner;
  }
}
