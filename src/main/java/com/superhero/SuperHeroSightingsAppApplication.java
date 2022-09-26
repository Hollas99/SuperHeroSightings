package com.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com"})
@EntityScan({"com"})
@EnableJpaRepositories({"com"})
@SpringBootApplication
public class SuperHeroSightingsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperHeroSightingsAppApplication.class, args);
	}

}
