package com.example.gorcerydelivery.service;


import java.util.List;

import com.example.gorcerydelivery.entity.Customer;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;



public interface CustomerService {
	Customer saveCustomer(Customer customer);
	Customer loginCustomer(Customer customer) throws ResourceNotFoundException;
	Customer getCustomerBycustomerId(long customerId) throws ResourceNotFoundException;
	List<Customer> findAll();
	void deleteBycustomerId(long customerId) throws ResourceNotFoundException;
	Customer getCustomerBycustomerEmail(String customerEmail)throws ResourceNotFoundException;
	Customer updateCustomer(Customer customer, long customerId) throws ResourceNotFoundException;


}
