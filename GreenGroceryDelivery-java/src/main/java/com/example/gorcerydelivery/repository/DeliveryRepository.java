package com.example.gorcerydelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gorcerydelivery.entity.Delivery;



public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	
	@Query("SELECT d FROM Delivery d LEFT JOIN FETCH d.order")
	List<Delivery> findAllDeliveriesWithOrders();
	 
	
	List<Delivery> findByDeliveryBoyDeliveryBoyId(long deliveryBoyId);
}
