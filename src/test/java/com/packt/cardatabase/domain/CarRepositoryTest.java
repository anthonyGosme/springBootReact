package com.packt.cardatabase.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {
  @Autowired private TestEntityManager entityManager;
  @Autowired private CarRepository repository;

  @Test
  public void saveCar() {
    Car car =
        new Car()
            .setBrand("Tesla")
            .setModel("Model X")
            .setColor("White")
            .setRegisterNumber("ABC-1234")
            .setYear(2017)
            .setPrice(86000);
    assertThat(car.getId()).isNotNull();
  }

  @Test
  public void deleteCar() {

    entityManager.persistAndFlush(
        new Car()
            .setBrand("Tesla")
            .setModel("Model X")
            .setColor("White")
            .setRegisterNumber("ABC-1234")
            .setYear(2017)
            .setPrice(86000));

    entityManager.persistAndFlush(
        new Car()
            .setLocalTime(LocalTime.now())
            .setBrand("Toyota")
            .setModel("Prius")
            .setColor("green")
            .setRegisterNumber("ZZZ-1123")
            .setYear(2019)
            .setPrice(79000));
    repository.deleteAll();
    assertThat(repository.findAll()).isEmpty();
  }
}
