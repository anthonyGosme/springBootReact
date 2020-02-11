package com.packt.cardatabase.web;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class CarController {

  private final CarRepository carRepository;

  public CarController(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @RequestMapping(
      value = "/cars",
      method = {GET, POST, DELETE, PATCH})
  public Iterable<Car> getCars() {
    return carRepository.findAll();
  }
}
