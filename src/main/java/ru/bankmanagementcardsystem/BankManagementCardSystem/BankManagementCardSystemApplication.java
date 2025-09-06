package ru.bankmanagementcardsystem.BankManagementCardSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class BankManagementCardSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagementCardSystemApplication.class, args);
	}

}
