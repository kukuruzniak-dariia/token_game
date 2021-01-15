package com.game.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.game.token"})
@EnableJpaRepositories("com.game.token.repositories")
public class TokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenApplication.class, args);
	}

}
