package com.Springboot_web_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringbootWebRestApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootWebRestApplication.class, args);
	}

}
