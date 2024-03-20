package com.example.gorcerydelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gorcerydelivery.entity.Customer;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.CustomerRepository;






@Service
public class CustomerServiceImpl  implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}
 
	@Override
	public Customer loginCustomer(Customer customer) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return customerRepository.findByCustomerEmailAndCustomerPassword(customer.getCustomerEmail(), customer.getCustomerPassword()).orElseThrow(()-> new ResourceNotFoundException("Email id or password not found"));
	}
//findByCustomerEmailAndCustomerPassword
	@Override
	public Customer getCustomerBycustomerId(long customerId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer id not found"+customerId));
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public void deleteBycustomerId(long customerId) throws ResourceNotFoundException {
		if(customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
		}
		else {
			throw new ResourceNotFoundException("Customer Id not Found: "+customerId);
		}
	}

	@Override
	public Customer getCustomerBycustomerEmail(String customerEmail) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return customerRepository.findBycustomerEmail(customerEmail).orElseThrow(()-> new ResourceNotFoundException("Email id not found"+customerEmail));
	}
	
	@Override
	public Customer updateCustomer(Customer customer, long customerId) throws ResourceNotFoundException {
		
		Customer existingCustomer=customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer id not found"+customerId));	
		
		existingCustomer.setCustomerFirstName(customer.getCustomerFirstName());
		existingCustomer.setCustomerLastName(customer.getCustomerLastName());
		existingCustomer.setCustomerEmail(customer.getCustomerEmail());
		existingCustomer.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
		existingCustomer.setPincode(customer.getPincode());
		existingCustomer.setCustomerAddress(customer.getCustomerAddress());
		existingCustomer.setCustomerPassword(customer.getCustomerPassword());
		
		customerRepository.save(existingCustomer);
		System.out.println(existingCustomer);
		return existingCustomer;
	}
	
}
