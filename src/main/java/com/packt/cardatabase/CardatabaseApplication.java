package com.packt.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

// @EnableAutoConfiguration + @ComponentScan + @Configure
// @EnableAutoConfiguration + @ComponentScan + @Configure
// @EnableAutoConfiguration + @ComponentScan + @Configure
@SpringBootApplication
public class CardatabaseApplication {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class)  ;
	public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("hello Spring boot");
    }
}
