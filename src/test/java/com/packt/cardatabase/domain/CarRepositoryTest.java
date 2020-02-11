package com.packt.cardatabase.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {
  private TestEntityManager testEntityManager;
  @Autowired private CarRepository carRepository;

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
      assertThat(car.getId()).isNotNull() ;
  }


}
