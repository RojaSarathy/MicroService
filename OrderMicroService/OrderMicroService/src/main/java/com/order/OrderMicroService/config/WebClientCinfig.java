 package com.order.OrderMicroService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientCinfig {

	@Bean
	public WebClient.Builder webClientBuilder(){
		return WebClient.builder();
	}
}
