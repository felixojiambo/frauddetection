package com.frauddetect.FraudAlgo;

import com.frauddetect.FraudAlgo.dto.UserDTO;
import com.frauddetect.FraudAlgo.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FraudAlgoApplication {

	private final UserService userService;

	// Constructor Injection
	public FraudAlgoApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FraudAlgoApplication.class, args);
	}

	// Initialize admin user after application startup
	@PostConstruct
	public void initializeAdminUser() {
		if (userService.getAllUsers().isEmpty()) {
			UserDTO admin = new UserDTO();
			admin.setUsername("admin");
			admin.setEmail("admin@fraudsystem.com");
			admin.setRole("ADMIN");
			userService.registerUser(admin);
			System.out.println("Default admin user created");
		}
	}
}
