package com.order.OrderMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.OrderMicroService.entity.Order;

@Repository
public interface OrderReporsitory extends JpaRepository<Order,Long> {
	

}
