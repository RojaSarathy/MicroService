package com.order.OrderMicroService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.OrderMicroService.dto.OrderResponseDto;
import com.order.OrderMicroService.dto.ProductDto;
import com.order.OrderMicroService.entity.Order;
import com.order.OrderMicroService.repository.OrderReporsitory;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {
     
	@Autowired
	private OrderReporsitory orderReporsitory;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@PostMapping("/placeOrder")
	public Mono<ResponseEntity<OrderResponseDto>> placeOrder(@RequestBody Order order){
		
		return webClientBuilder.build().get().uri("http://localhost:8081/products/" +order.getProductId()).retrieve()
				.bodyToMono(ProductDto.class).map(productDto ->{
					OrderResponseDto response= new OrderResponseDto();
					response.setProductId(order.getProductId());
					response.setQuantity(order.getQuantity());
					
					//set product details
					
					response.setProductName(productDto.getName());
					response.setProductPrice(productDto.getPrice());
					response.setTotalPrice(order.getQuantity() * productDto.getPrice());
					
					//save order details to db
					
					orderReporsitory.save(order);
					response.setOrderId(order.getId());
					return ResponseEntity.ok(response);
					
				});
		
	}
	
	@GetMapping
	public List<Order> getAllOrderList(){
	return orderReporsitory.findAll();
	}
}
