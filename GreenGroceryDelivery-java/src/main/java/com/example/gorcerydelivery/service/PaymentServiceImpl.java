package com.example.gorcerydelivery.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gorcerydelivery.entity.Customer;
import com.example.gorcerydelivery.entity.Order;
import com.example.gorcerydelivery.entity.Payment;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.OrderRepository;
import com.example.gorcerydelivery.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	
	
	public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository,
			CustomerService customerService, OrderService orderService) {
		super();
		this.paymentRepository = paymentRepository;
//		this.orderRepository = orderRepository;
		this.customerService = customerService;
		this.orderService = orderService;
	}

	@Override
	public Payment addPayment(Payment payment, long orderId, long customerId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", orderId));
		System.out.println("****************"+order.getOrderId());
    	payment.setOrderId(orderId);
		payment.setTotalPrice(order.getTotalPrice());
		payment.setPaidDate(LocalDate.now());
		payment.setPaidAmount(order.getTotalPrice());
		if (payment.getTotalPrice() == payment.getPaidAmount()) {
			order.setPaymentStatus("PAID");
			order.setOrderStatus("Delivered");
		} else {

			order.setPaymentStatus("NOT-PAID");
			order.setOrderStatus("payment pending");
		}
			  Customer customer=customerService.getCustomerBycustomerId(customerId);
		    	
		    	payment.setCustomer(customer);
		    	
		    	
		    	     //return paymentRepository.save(payment);
		    	
		
		return paymentRepository.save(payment);
	}

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentById(long paymentId) {
		// TODO Auto-generated method stub
		return paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("Payement","Id",paymentId));
		
	}

	@Override
	public void deletePayment(long paymentId) {
		paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("Payement","Id",paymentId));
		paymentRepository.deleteById(paymentId);
		
		
		
	}

	@Override
	public List<Payment> getAllPaymentsByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		return paymentRepository.findByCustomerCustomerId(customerId);
	}

	@Override
	public List<Payment> getAllPaymentByOrderId(long orderId) {
		// TODO Auto-generated method stub
		return paymentRepository.findByOrderId(orderId);
	}

}
