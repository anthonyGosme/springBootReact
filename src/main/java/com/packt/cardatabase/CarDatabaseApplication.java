package com.packt.cardatabase;

import com.packt.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;

// @SpringBootApplication =  @EnableAutoConfiguration + @ComponentScan + @Configure
@SpringBootApplication
public class CarDatabaseApplication {

  private static final Logger logger = LoggerFactory.getLogger(CarDatabaseApplication.class);
  final UserRepository userRepository;
  private final CarRepository carRepository;
  private final OwnerRepository ownerRepository;

  public CarDatabaseApplication(
      UserRepository userRepository, CarRepository carRepository, OwnerRepository ownerRepository) {
    this.userRepository = userRepository;
    this.carRepository = carRepository;
    this.ownerRepository = ownerRepository;
  }

  public static void main(String[] args) {

    SpringApplication.run(CarDatabaseApplication.class, args);
    logger.info("hello Spring boot");
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {
      Owner owner1 = new Owner("John", "Johnson");
      ownerRepository.save(owner1);
      Owner owner2 = new Owner("Mary", "Robinson");
      ownerRepository.save(owner2);
      Car car1 =
          new Car()
              .setLocalTime(LocalTime.now())
              .setBrand("Ford")
              .setModel("Mustang")
              .setColor("red")
              .setRegisterNumber("ADF-1121")
              .setYear(2017)
              .setPrice(59000)
              .setOwner(owner1);
      new Car().setColor("blu");
      Car car2 =
          new Car()
              .setLocalTime(LocalTime.now())
              .setBrand("Nissan")
              .setModel("Leaf")
              .setColor("green")
              .setRegisterNumber("BAF-1122")
              .setYear(2018)
              .setPrice(69000)
              .setOwner(owner2);

      Car car3 =
          new Car()
              .setLocalTime(LocalTime.now())
              .setBrand("Toyota")
              .setModel("Prius")
              .setColor("green")
              .setRegisterNumber("ZZZ-1123")
              .setYear(2019)
              .setPrice(79000)
              .setOwner(owner2);

      carRepository.save(car1);
      carRepository.save(car2);
      carRepository.save(car3);
      userRepository.save(
          new User()
              .setUsername("user")
              .setPassword("$2a$04$/Ws7Jb1EmQdRYvWnBYsLhOoz03Iolr61WnBI6UnYG6DdchGqBV/pS")
              .setRole("USER"));
      userRepository.save(
          new User()
              .setUsername("admin")
              .setPassword("$2a$04$/Ws7Jb1EmQdRYvWnBYsLhOoz03Iolr61WnBI6UnYG6DdchGqBV/pS")
              .setRole("USER"));
    };
  }
}
