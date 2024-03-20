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

import com.example.gorcerydelivery.entity.DeliveryBoy;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.service.DeliveryBoyService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/deliveryBoy")
public class DeliveryBoyController {

	@Autowired
	private DeliveryBoyService deliveryBoyService;
	
	@PostMapping("/register")
	public ResponseEntity<DeliveryBoy> saveDeliveryBoy(@Valid @RequestBody DeliveryBoy deliveryBoy){
		deliveryBoy.setDeliveryBoyId(0);
		return new ResponseEntity<DeliveryBoy>(deliveryBoyService.saveDeliveryBoy(deliveryBoy),HttpStatus.CREATED);
		
	}
	
//	@PutMapping("/update")
//	public ResponseEntity<DeliveryBoy> update(@Valid @RequestBody DeliveryBoy deliveryBoy) {
//		DeliveryBoy updatedDeliveryBoy = deliveryBoyService.saveDeliveryBoy(deliveryBoy);
//		return ResponseEntity.status(HttpStatus.OK).body(updatedDeliveryBoy);
//	}
//	
	
	@PutMapping("/update/{deliveryBoyId}")
	private DeliveryBoy update(@Valid @RequestBody DeliveryBoy deliveryBoy) throws ResourceNotFoundException {

		DeliveryBoy d1 = deliveryBoyService.getDeliveryBoyBydeliveryBoyId(deliveryBoy.getDeliveryBoyId());

		System.out.println("OK");
		DeliveryBoy result = deliveryBoyService.updateDeliveryBoyById(d1.getDeliveryBoyId(), deliveryBoy);
		return result;
	}
	
	@GetMapping("/showAll")
	public List<DeliveryBoy> findAll(){
		return deliveryBoyService.findAll();	
	}
	
	@GetMapping("/findById/{deliveryBoyId}")
	public DeliveryBoy findById(@PathVariable long deliveryBoyId) throws ResourceNotFoundException {
		return deliveryBoyService.getDeliveryBoyBydeliveryBoyId(deliveryBoyId);
	}
	
	@DeleteMapping("/delete/{deliveryBoyId}")
	public String delete(@PathVariable long deliveryBoyId) throws ResourceNotFoundException {
		deliveryBoyService.deleteBydeliveryBoyId(deliveryBoyId);
		return "Deleted Customer Id: "+deliveryBoyId;
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<DeliveryBoy> login(@Valid @RequestBody DeliveryBoy deliveryBoy) throws ResourceNotFoundException {
		DeliveryBoy loginCustomer = deliveryBoyService.loginDeliveryBoy(deliveryBoy);
		return ResponseEntity.status(HttpStatus.OK).body(loginCustomer);
	}
	

	@GetMapping("/findByEmail/{deliveryBoyEmail}")
	public DeliveryBoy getByEmail(@PathVariable String deliveryBoyEmail) throws ResourceNotFoundException {
		return deliveryBoyService.getDeliveryBoyBydeliveryBoyEmail(deliveryBoyEmail);
	}
	
}
