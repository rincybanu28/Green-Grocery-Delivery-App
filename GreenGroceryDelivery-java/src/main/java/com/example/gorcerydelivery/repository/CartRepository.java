package com.example.gorcerydelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gorcerydelivery.entity.Cart;
import com.example.gorcerydelivery.entity.Customer;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	void deleteCardByCustomer(Customer customer);
}
