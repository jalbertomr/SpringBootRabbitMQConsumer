package com.bext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRabbitMQListenerApp {

	public static void main(String[] args) {
		SpringApplication.run(new Object[] {SpringRabbitMQListenerApp.class}, args);
	}
}
