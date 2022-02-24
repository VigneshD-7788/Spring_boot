package com.Springboot_web_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootWebRestApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootWebRestApplication.class, args);
	}

}
