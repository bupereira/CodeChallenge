package com.wit.springCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class SpringCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCalculatorApplication.class, args);
	}

}
