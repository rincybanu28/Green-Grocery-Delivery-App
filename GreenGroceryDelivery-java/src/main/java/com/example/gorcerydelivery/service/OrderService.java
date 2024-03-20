package com.example.gorcerydelivery.service;

import java.util.List;

import com.example.gorcerydelivery.entity.Order;
import com.example.gorcerydelivery.entity.TransactionDetails;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;




public interface OrderService {

	Order addOrder(Order order, long customerId, long cartId)throws ResourceNotFoundException;

	Order getOrderById(long orderId) throws ResourceNotFoundException;
	
	Order updateOrder(Order order, long orderId) throws ResourceNotFoundException;

	List<Order> getOrderByCustomerId(long customerId) throws ResourceNotFoundException;

	//List<Order> getAllOrders();

	// List<Order> getAllOrdersByCartId(long cartId);
	
	Order addOrderProduct(Order order,long customerId) throws ResourceNotFoundException;
	
	void deleteOrder(long orderId);
	
	TransactionDetails createTransaction(Double amount);

	List<Order> findAll();
}
