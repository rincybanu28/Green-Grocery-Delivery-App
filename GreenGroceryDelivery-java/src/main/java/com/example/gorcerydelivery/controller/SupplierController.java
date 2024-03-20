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

import com.example.gorcerydelivery.entity.Supplier;
import com.example.gorcerydelivery.service.SupplierService;

import jakarta.validation.Valid;

import com.example.gorcerydelivery.exception.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;

	public SupplierController(SupplierService supplierService) {
		super();
		this.supplierService = supplierService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Supplier> saveSupplier(@Valid @RequestBody Supplier supplier)
	{
		// Just in case we can pass id in JSON request body, setId as 0  to force a save of new item instead of update
		supplier.setSupplierId(0);
		Supplier createdSupplier= supplierService.saveSupplier(supplier);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
	}
	
	@GetMapping("/showAll")
	public List<Supplier> findAll(){
		return supplierService.findAll();	
	}
	
	@GetMapping("/findById/{supplierId}")
	public Supplier findById(@PathVariable long supplierId) throws ResourceNotFoundException {
		return supplierService.findById(supplierId);
	}
	
	@GetMapping("/findByEmail/{supplierEmail}")
	public Supplier findByEmailId(@PathVariable String supplierEmail)throws ResourceNotFoundException{
		return supplierService.getSupplierBysupplierEmail(supplierEmail);
	}
	@PutMapping("/update")
	public ResponseEntity<Supplier> updateSupplier(@Valid @RequestBody Supplier supplier)
	{
		Supplier udatedSupplier= supplierService.saveSupplier(supplier);
		return ResponseEntity.status(HttpStatus.CREATED).body(udatedSupplier);
	}
	
//	@DeleteMapping("/delete/{supplierId}")
//	public String deleteSupplier(@PathVariable long supplierId) throws ResourceNotFoundException {
//	    supplierService.deleteById(supplierId);
//	    return "Deleted Supplier Id : " + supplierId;
//	}

	@DeleteMapping("/delete/{supplierId}")
	public String deleteSupplier(@PathVariable long supplierId) throws ResourceNotFoundException {
	    try {
	        // Delete associated records in the order_table_product table
	    	supplierService.deleteById(supplierId);
	        
	        // Delete the supplier
	        supplierService.deleteById(supplierId);

	        return "Deleted Supplier Id: " + supplierId;
	    } catch (Exception e) {
	        return "Error deleting supplier: " + e.getMessage();
	    }
	}

	
}
