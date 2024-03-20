package com.example.gorcerydelivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gorcerydelivery.entity.Supplier;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.SupplierRepository;



@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierRepository supplierRepository;
	
	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@Override
	public void deleteById(long supplierId) throws ResourceNotFoundException {
		if(supplierRepository.existsById(supplierId)) 
		{
			supplierRepository.deleteById(supplierId);
		}
		else
		{
			throw new ResourceNotFoundException("Supplier Id not found : "+supplierId);
		}
	}

	@Override
	public Supplier findById(long supplierId) throws ResourceNotFoundException {
		Optional<Supplier> result = supplierRepository.findById(supplierId);
		Supplier supplier;
		if(result.isPresent()) 
		{
			supplier = result.get();
		}
		else 
		{
			throw new ResourceNotFoundException("Supplier id not found : "+supplierId);
		}
		return supplier;
	}

	@Override
	public Supplier getSupplierBysupplierEmail(String supplierEmail) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return supplierRepository.findBysupplierEmail(supplierEmail).orElseThrow(()-> new ResourceNotFoundException("Email id not found"+supplierEmail));

	}

	
}
