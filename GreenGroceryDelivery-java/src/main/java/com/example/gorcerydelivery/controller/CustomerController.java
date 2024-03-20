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

import com.example.gorcerydelivery.entity.Customer;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.CustomerRepository;
import com.example.gorcerydelivery.service.CustomerService;


import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository custRepository;
	
	@PostMapping("/register")
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer){
		customer.setCustomerId(0);
		return new ResponseEntity<Customer>(customerService.saveCustomer(customer),HttpStatus.CREATED);
		
	}
	    
//	@PutMapping("/")
//	public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer) {
//		Customer updatedCustomer = customerService.saveCustomer(customer);
//		return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
//	}
//	
	
	@PutMapping("/")
	public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer) {
		Customer updatedCustomer = customerService.saveCustomer(customer);
		return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
	}
	@GetMapping("/showAll")
	public List<Customer> findAll(){
		return customerService.findAll();	
	}
	
	@GetMapping("/findById/{customerId}")
	public Customer findById(@PathVariable long customerId) throws ResourceNotFoundException {
		return customerService.getCustomerBycustomerId(customerId);
	}
	
	@DeleteMapping("/delete/{customerId}")
	public String delete(@PathVariable long customerId) throws ResourceNotFoundException {
		customerService.deleteBycustomerId(customerId);
		return "Deleted Customer Id: "+customerId;
		
	}
	
	/*
	 * //Login
		@PostMapping("/login")
		public ResponseEntity<User> loginUser(@RequestBody User user) {

			return new ResponseEntity<User>(userService.loginUser(user), HttpStatus.OK);

		}
	 */
	
	
	@PostMapping("/login/")
	public ResponseEntity<Customer> login(@RequestBody Customer customer) throws ResourceNotFoundException {
//		Customer loginCustomer = customerService.loginCustomer(customer);
//		return ResponseEntity.status(HttpStatus.OK).body(loginCustomer);
//	
		
		return new ResponseEntity<Customer>(customerService.loginCustomer(customer),HttpStatus.OK);
	//	return new ResponseEntity<User>(userService.loginUser(user), HttpStatus.OK);
	}
	

	@GetMapping("/findByEmail/{emailId}")
	public Customer getByEmail(@PathVariable String emailId) throws ResourceNotFoundException {
		return customerService.getCustomerBycustomerEmail(emailId);
	}
	
	// get customer by email
				@PostMapping("/forgotpassword")
				public Customer getUserByEmail(@RequestBody Customer customer) {
					return custRepository.findBycustomerEmail(customer.getCustomerEmail()).get();
				}
		
				//change password
				@PostMapping("/{custId}/{newpassword}")
				public Customer changeCustomerPassword(@PathVariable("custId") long custId,@PathVariable("newpassword") String newpassword) throws ResourceNotFoundException {
					
					Customer c=customerService.getCustomerBycustomerId(custId);
					
					c.setCustomerPassword(newpassword);
					customerService.updateCustomer(c, custId);
					return c;
				}
				
				
//				@PostMapping("/{uid}/{newpassword}")
//				public User changeUserPassword(@PathVariable("uid") long uid,@PathVariable("newpassword") String newpassword) {
//					//return customerService.getCustomerByEmail(customer);
//					User u=userService.getUserById(uid);
//					u.setPassword(newpassword);
//					userService.updateUser(u, uid);
//					return u;
//				}

			
				
				
				//Update Customer	
				@PutMapping("customer/{id}")
				public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long customerId, @RequestBody Customer customer) throws ResourceNotFoundException {
					return new ResponseEntity<Customer>(customerService.updateCustomer(customer, customerId), HttpStatus.OK);
				}
	
	
}
