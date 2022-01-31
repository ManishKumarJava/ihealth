package com.life.pharmacy.ihealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class IhealthApplication {

	public static void main(String[] args) {
		SpringApplication.run(IhealthApplication.class, args);
	}

}
