package com.example.gorcerydelivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.gorcerydelivery.entity.Supplier;



public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Optional<Supplier> findBysupplierEmail(String supplierEmail);
}
