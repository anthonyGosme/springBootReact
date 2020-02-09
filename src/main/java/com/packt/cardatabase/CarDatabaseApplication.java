package com.packt.cardatabase;

import com.packt.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;


//@SpringBootApplication =  @EnableAutoConfiguration + @ComponentScan + @Configure
@SpringBootApplication
public class CarDatabaseApplication {

    private static final Logger logger = LoggerFactory.getLogger(CarDatabaseApplication.class);
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired  UserRepository userRepository ;
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
            Car car1 = new Car(LocalTime.now(), "Ford", "Mustang", "red", "ADF-1121", 2017, 59000, owner1);
            Car car2 = new Car(LocalTime.now(), "Nissan", "Leaf", "green", "BAF-1122", 2018, 69000, owner2);
            Car car3 = new Car(LocalTime.now(), "Toyota", "Prius", "silver", "ZZZ-1123", 2019, 79000, owner2);
            carRepository.save(car1);
            carRepository.save(car2);
            carRepository.save(car3);
            userRepository.save(new User("user" ,"{bcrypt}$2a$04$/Ws7Jb1EmQdRYvWnBYsLhOoz03Iolr61WnBI6UnYG6DdchGqBV/pS","USER"))  ;
            userRepository.save(new User("admin","{bcrypt}$2a$04$/Ws7Jb1EmQdRYvWnBYsLhOoz03Iolr61WnBI6UnYG6DdchGqBV/pS","ADMIN"))  ;
        };
    }


}
