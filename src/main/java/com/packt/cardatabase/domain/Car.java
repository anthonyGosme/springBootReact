package com.packt.cardatabase.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity // disable for constructor generator to work
@Accessors(chain = true)
@Getter
@Setter
public class Car {
  private LocalTime localTime;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private LocalDateTime localDateTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "owner")
  private Owner owner;

  private String brand, model, color, registerNumber;
  private int year, price;
}
