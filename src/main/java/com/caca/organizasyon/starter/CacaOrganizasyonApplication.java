package com.caca.organizasyon.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.caca.organizasyon"})
@EntityScan(basePackages = {"com.caca.organizasyon"})
@EnableJpaRepositories(basePackages = {"com.caca.organizasyon"})
public class CacaOrganizasyonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacaOrganizasyonApplication.class, args);
	}

}
