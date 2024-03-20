package com.example.gorcerydelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gorcerydelivery.entity.Payment;



@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
public List<Payment> findByCustomerCustomerId(long customerId);
public List<Payment> findByOrderId(long orderId);
}