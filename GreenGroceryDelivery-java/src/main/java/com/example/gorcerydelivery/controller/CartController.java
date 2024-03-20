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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.example.gorcerydelivery.entity.Cart;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.service.CartService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/carts")
public class CartController {
	@Autowired
	private CartService cartService;

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}
	
	@PostMapping("/{customerId}/{productId}")
	public ResponseEntity<Cart> addCart(@Valid @RequestBody Cart cart, 
	                                    @PathVariable long productId, 
	                                    @PathVariable long customerId) throws ResourceNotFoundException {
	    System.out.println("********");    
	    return new ResponseEntity<>(cartService.addCart(cart, productId, customerId), HttpStatus.CREATED);
	}

	
	/*
	 * @PostMapping("{userId}/{bookId}")
		public ResponseEntity<Cart> addCart(@Valid @RequestBody Cart cart, @PathVariable long bookId,@PathVariable long userId) {
			System.out.println("********");
			return new ResponseEntity<Cart>(cartService.addCart(cart, bookId,userId), HttpStatus.CREATED);
		}
	 */
	
	@GetMapping("/showAll")
	public List<Cart> getAllCarts(){
		return cartService.getAllCarts();
	}
	
	@GetMapping("CartById/{id}")
	public ResponseEntity<Cart> getcartById(@PathVariable("id") long cartId) throws ResourceNotFoundException{
		return new ResponseEntity<Cart>(cartService.getCartById(cartId), HttpStatus.OK);
		
	}
	
	@PutMapping("{cartId}")
	public ResponseEntity<Cart> updateCart(@Valid @PathVariable("cartId") long cartId, @RequestBody Cart cart) throws ResourceNotFoundException {
		return new ResponseEntity<Cart>(cartService.updateCart(cart, cartId), HttpStatus.OK);
	}
	
	@DeleteMapping("{cartId}")
	public ResponseEntity<Boolean> deleteCart(@PathVariable long cartId) throws ResourceNotFoundException {
		cartService.deleteCart(cartId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
	

}
