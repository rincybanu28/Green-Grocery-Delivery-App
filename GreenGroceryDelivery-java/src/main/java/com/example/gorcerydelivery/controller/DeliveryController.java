package com.example.gorcerydelivery.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.gorcerydelivery.entity.Delivery;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.service.DeliveryService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("/register")
	public ResponseEntity<Delivery> addDeliveryorder(@Valid @RequestBody Delivery delivery ){
		delivery.setDeliveryId(0);
		return new ResponseEntity<>(deliveryService.addDelivery(delivery),HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/register/{orderId}")
	public ResponseEntity<Delivery> saveOrderByOrder(@Valid @RequestBody Delivery delivery,
			 @PathVariable long orderId ){
		delivery.setDeliveryId(0);
		return new ResponseEntity<>(deliveryService.addDeliveryOrder(delivery,orderId),HttpStatus.CREATED);
		
	}
	
//	@PutMapping("/update/")
//	public ResponseEntity<Delivery> update(@Valid @RequestBody Delivery delivery) {
//		Delivery updatedDelivery = deliveryService.addDelivery(delivery);
//		return ResponseEntity.status(HttpStatus.OK).body(updatedDelivery);
//	}
	
	@GetMapping("/findAll")
	public List<Delivery> findAll(){
		return deliveryService.findAll();	
	}
	
	@GetMapping("/findAllWithOrders")
	public List<Delivery> findAllDeliveriesWithOrders() {
	    return deliveryService.findAllorders();
	}
	
	
	
	
	
	@GetMapping("/findBydeliveryId/{deliveryId}")
	public Delivery findById(@PathVariable long deliveryId) throws ResourceNotFoundException {
		return deliveryService.getDeliveryById(deliveryId);
	}
	
	@DeleteMapping("/delete/{deliveryId}")
	public String delete(@PathVariable long deliveryId) throws ResourceNotFoundException {
		deliveryService.deleteById(deliveryId);   
		return "Deleted Order Id: "+deliveryId;
		
	}
	
	@GetMapping("/getDeliveryByDeliveryBoyId/{deliveryBoyId}")
	public List<Delivery> getDeliveryByDeliveryBoyId(@PathVariable long deliveryBoyId) throws ResourceNotFoundException{
		return deliveryService.getDeliveryByDeliveryBoyId(deliveryBoyId);
	}
}
