package com.Works.Csmt;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.Works.Csmt", "F110"})
@EntityScan(basePackages = "F110.model")
@EnableJpaRepositories(basePackages = "F110.repository")
public class CsmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsmtApplication.class, args);
	}

}
