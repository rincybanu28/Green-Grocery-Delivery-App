package com.example.gorcerydelivery.service;

import java.util.List;

import com.example.gorcerydelivery.entity.Supplier;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;



public interface SupplierService {
	public List<Supplier> findAll();
	public Supplier saveSupplier(Supplier supplier);
	public void deleteById(long supplierId) throws ResourceNotFoundException;
	Supplier findById(long supplierId) throws ResourceNotFoundException;
	Supplier getSupplierBysupplierEmail(String supplierEmail)throws ResourceNotFoundException;
}
