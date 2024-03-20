package com.example.gorcerydelivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gorcerydelivery.entity.Customer;





public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerEmailAndCustomerPassword(String customerEmail, String customerPassword);
	Optional<Customer> findBycustomerEmail(String customerEmail);
	
	
}
