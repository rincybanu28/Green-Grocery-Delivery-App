package com.example.gorcerydelivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gorcerydelivery.entity.DeliveryBoy;




public interface DeliveryBoyRepository extends JpaRepository<DeliveryBoy, Long> {

	Optional<DeliveryBoy> findByDeliveryBoyEmailAndDeliveryBoyPassword(String DeliveryBoyEmail, String deliveryBoyPassword);
	Optional<DeliveryBoy> findByDeliveryBoyEmail(String DeliveryBoyEmail);
}
