package com.example.gorcerydelivery.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gorcerydelivery.entity.Cart;
import com.example.gorcerydelivery.entity.Customer;
import com.example.gorcerydelivery.entity.Product;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.CartRepository;
import com.example.gorcerydelivery.repository.CustomerRepository;
import com.example.gorcerydelivery.repository.ProductRepository;

import jakarta.transaction.Transactional;




@Service
public class CartServiceImpl implements CartService {

	@Autowired
	public CartRepository cartRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	
	
	
	


	 public CartServiceImpl(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}


	
//	    @Override
//	    public Cart addToCart(Cart cart, long productId, long customerId) throws ResourceNotFoundException {
//	        // Fetch the product and customer from the services
//	        Product product = productService.findById(productId);
//	        Customer customer = customerService.getCustomerBycustomerId(customerId);
//
//	        // Get existing cart for the same customer and product
//	        Optional<Cart> existingCartOptional = getAllCarts()
//	                .stream()
//	                .filter(c -> c.getCustomer().getCustomerId() == customerId && c.getProduct().getProductId() == productId)
//	                .findFirst();
//
//	        if (existingCartOptional.isPresent()) {
//	            // Update existing cart
//	            Cart existingCart = existingCartOptional.get();
//	            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
//	            existingCart.setMrpPrice(product.getPrice());
//	            existingCart.setCustomer(customer);
//	            System.out.println("Updating existing cart");
//	            return updateCart(existingCart, existingCart.getCartId());
//	        } else {
//	            // Create a new cart
//	            cart.setProduct(product);
//	            cart.setMrpPrice(product.getPrice());
//	            cart.setCustomer(customer);
//	            System.out.println("Creating new cart");
//
//	            // Handle negative product quantity
//	            int newQuantity = product.getProductQuantity() - cart.getQuantity();
//	            if (newQuantity < 0) {
//	                throw new IllegalArgumentException("Insufficient product quantity");
//	            } else {
//	                product.setProductQuantity(newQuantity);
//	            }
//
//	            // Save the new cart
//	            return cartRepository.save(cart);
//	        }
//	    }
//	
	
	 @Override
	 public Cart addCart(Cart cart, long productId, long customerId)  {
		 Product product=productService.findById(productId);
		 Customer customer=customerService.getCustomerBycustomerId(customerId);
		 List<Cart> crl=this.getAllCarts();
		 int flag=0;
		 Cart existingCart=null;
		 if(crl.size()>0) {
			 for(int i=0;i<crl.size();i++) {
				 Cart c=this.getCartById(crl.get(i).getCartId());
				 if(c.getCustomer().getCustomerId()==customerId && c.getProduct().getProductId()==productId) {
					 flag=1;
					 existingCart=c;
				 }
			 }
		 }
		 product.setProductQuantity(product.getProductQuantity()-cart.getQuantity());
		 if(flag==1 && existingCart!=null) {
			 existingCart.setQuantity(existingCart.getQuantity()+cart.getQuantity());
			 existingCart.setMrpPrice(product.getPrice());
			 existingCart.setCustomer(customer);
			 System.out.println("111111111111");
			 return this.updateCart(existingCart, existingCart.getCartId());
			 
		 }else {
			 cart.setProduct(product);
			 cart.setMrpPrice(product.getPrice());
			 cart.setCustomer(customer);
			 System.out.println("2222222222");
			 return cartRepository.save(cart);
		 }
	 
	 
	 }
	       
	 
	 
	@Override
	public List<Cart> getAllCarts() {
		// TODO Auto-generated method stub
		return cartRepository.findAll();
	}

	@Override
	public Cart getCartById(long cartId) throws ResourceNotFoundException {
		return cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart","Id",cartId));
	}

	@Override
	public Cart updateCart(Cart cart, long cartId) throws ResourceNotFoundException {
	    Cart existingCart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", cartId));

	    // Update fields other than the identifier
	    existingCart.setQuantity(cart.getQuantity());
	    existingCart.setMrpPrice(cart.getMrpPrice());
	    existingCart.setProduct(cart.getProduct());
	    existingCart.setCustomer(cart.getCustomer());

	    // Save the updated cart
	    return cartRepository.save(existingCart);
	}


	@Override
	public void deleteCart(long cartId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Cart existingCart=cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart","Id",cartId));
		Product product =productService.findById(existingCart.getProduct().getProductId());
		product.setProductQuantity(product.getProductQuantity());
		productService.updateProduct(product, product.getProductId());
		// cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart","Id",cartId));
		cartRepository.deleteById(cartId);
	}

	@Override
	public void deleteCartByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		cartRepository.deleteCardByCustomer(customer);
	}
	

	
}
