package com.example.gorcerydelivery.service;

import java.util.List;

import com.example.gorcerydelivery.entity.Cart;
import com.example.gorcerydelivery.entity.Customer;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;



public interface CartService {
	
	Cart addCart(Cart cart,long cartId,long customerId);
	List<Cart> getAllCarts();
	Cart getCartById(long cartId) throws ResourceNotFoundException;
	Cart updateCart(Cart cart, long cartId) throws ResourceNotFoundException ;
	void deleteCart(long cartId) throws ResourceNotFoundException;
	void deleteCartByCustomer(Customer customer);
	}
