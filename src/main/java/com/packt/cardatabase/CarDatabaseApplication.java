package com.packt.cardatabase;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;


//@SpringBootApplication =  @EnableAutoConfiguration + @ComponentScan + @Configure
@SpringBootApplication
public class CarDatabaseApplication {

    private static final Logger logger = LoggerFactory.getLogger(CarDatabaseApplication.class);
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public static void main(String[] args) {

        SpringApplication.run(CarDatabaseApplication.class, args);
        logger.info("hello Spring boot");
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Set<Owner>  ownerSet1 = new HashSet<>() ;
            Owner owner1 = new Owner("John", "Johnson") ;
            ownerSet1.add(owner1) ;
            Set<Owner>  ownerSet2 = new HashSet<>() ;
            Owner owner2 = new Owner("Mary", "Robinson") ;
            ownerSet2.add(owner2) ;
            ownerRepository.save(owner1);
            ownerRepository.save(owner2);

            Car car1 = new Car("Ford", "Mustang", "red", "ADF-1121", 2017, 59000, ownerSet1);
            Car car2 = new Car("Nissan", "Leaf", "green", "BAF-1122", 2018, 69000, ownerSet2);
            Car car3 = new Car("Toyota", "Prius", "silver", "ZZZ-1123", 2019, 79000, ownerSet2);
            carRepository.save(car1);
            carRepository.save(car2);
            carRepository.save(car3);
        };
    }


}
