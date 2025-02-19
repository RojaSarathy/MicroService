package com.microService.api_gatway_microService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatwayMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatwayMicroServiceApplication.class, args);
	}

}
