package com.example.gorcerydelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gorcerydelivery.entity.Order;
import com.example.gorcerydelivery.entity.TransactionDetails;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.service.OrderService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

//adding order details
	@PostMapping("{customerId}/{cartId}")
	public ResponseEntity<Order> addOrder(@PathVariable long customerId, @PathVariable long cartId,
			@RequestBody Order order) throws ResourceNotFoundException {

		return new ResponseEntity<Order>(orderService.addOrder(order, customerId, cartId), HttpStatus.CREATED);
	}

//updating order details
	@PutMapping("{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable("orderId") long orderId, @RequestBody Order order) throws ResourceNotFoundException {
		return new ResponseEntity<Order>(orderService.updateOrder(order, orderId), HttpStatus.OK);
	}

	// get all order details
	@GetMapping("/findAll/")
	public List<Order> findAll(){
		return orderService.findAll();	
	}

	// get order details by customer id
	@GetMapping("{customerId}")
	public List<Order> getOrderByCustomerId(@PathVariable long customerId) throws ResourceNotFoundException {
		return orderService.getOrderByCustomerId(customerId);
	}
	@GetMapping("findByOrderId/{orderId}")
	public Order getOrderByOrderId(@PathVariable long orderId)throws ResourceNotFoundException {
		return orderService.getOrderById(orderId);
	}
	
	// to delete or cancel Order
	@DeleteMapping("{orderId}")
	public ResponseEntity<Boolean> deleteBooking(@PathVariable("orderId") long orderId) {
		orderService.deleteOrder(orderId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

	// adding order details
	@PostMapping("/addOrder/{customerId}")
	public ResponseEntity<Order> addOrderProduct(@PathVariable long customerId, @RequestBody Order order) throws ResourceNotFoundException {
		return new ResponseEntity<Order>(orderService.addOrderProduct(order, customerId), HttpStatus.CREATED);
	}
	
	@GetMapping({"/createTransaction/{amount}"})
    public TransactionDetails createTransaction(@PathVariable(name = "amount") Double amount) {
        return orderService.createTransaction(amount); //orderDetailService.createTransaction(amount);
    }


}
