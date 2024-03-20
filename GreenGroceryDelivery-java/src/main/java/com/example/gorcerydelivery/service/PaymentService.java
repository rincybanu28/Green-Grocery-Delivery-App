package com.example.gorcerydelivery.service;

import java.util.List;

import com.example.gorcerydelivery.entity.Payment;


public interface PaymentService {
	Payment addPayment(Payment payment,long orderId,long customerId);
	List<Payment> getAllPayments();
	Payment getPaymentById(long paymentId);
	void deletePayment(long paymentId);
	public List<Payment> getAllPaymentsByCustomerId(long customerId);
	public List<Payment> getAllPaymentByOrderId(long orderId);
}
