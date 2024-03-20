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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gorcerydelivery.entity.Payment;
import com.example.gorcerydelivery.service.PaymentService;


@CrossOrigin(origins = "http://localhost:4200")

@RestController // is controller which provides different end points to access the services
@RequestMapping("/api/payements")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("{orderId}/{customerId}")
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment, @PathVariable long orderId,
			@PathVariable long customerId) {

		return new ResponseEntity<Payment>(paymentService.addPayment(payment, orderId,customerId),
				HttpStatus.CREATED);
	}

	// getting list of payments
			@GetMapping("/getAlPayments")
			public List<Payment> getAlPayments() {
				return paymentService.getAllPayments();
			}

			// to get payment by payment id(for receipt)

			@GetMapping("/getPaymentById/{paymentId}")
			public ResponseEntity<Payment> getPaymentById(@PathVariable long paymentId) {
				return new ResponseEntity<Payment>(paymentService.getPaymentById(paymentId), HttpStatus.OK);
			}

		// to delete payment
			@DeleteMapping("/delete/{paymentId}")
			public ResponseEntity<Boolean> deletePayment(@PathVariable long paymentId) {
				paymentService.deletePayment(paymentId);
				boolean flag = true;
				return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
			}
		
			//public List<Payment> findByOrderId(long orderId);
			@GetMapping("/getAllPaymentsByCustomerId/{customerId}")
			public List<Payment> getAllPaymentsByCustomerId(@PathVariable long customerId){
				return paymentService.getAllPaymentsByCustomerId(customerId);
			}
			@GetMapping("/getAllPaymentsByOrderId/{orderId}")
			public List<Payment> getAllPaymentsByOrderId(@PathVariable long orderId){
				return paymentService.getAllPaymentByOrderId(orderId);
			}
	
}
