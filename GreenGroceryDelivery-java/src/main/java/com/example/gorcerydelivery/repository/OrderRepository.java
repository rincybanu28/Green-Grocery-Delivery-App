package com.example.gorcerydelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gorcerydelivery.entity.Order;




public interface OrderRepository extends JpaRepository<Order, Long>{

	public List<Order> findByCustomerCustomerId(long customerId);
	public void deleteByOrderId(long orderId);
	public List<Order> findByOrderId(long orderId);
}
