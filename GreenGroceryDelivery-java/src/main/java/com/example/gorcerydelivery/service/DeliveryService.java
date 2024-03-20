package com.example.gorcerydelivery.service;

import java.util.List;

import com.example.gorcerydelivery.entity.Customer;
import com.example.gorcerydelivery.entity.Delivery;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;



public interface DeliveryService {

	public Delivery addDelivery(Delivery delivery);
	public Delivery addDeliveryOrder(Delivery delivery,long orderId);
	void deleteById(long deliveryId) throws ResourceNotFoundException;
	Delivery getDeliveryById(long deliveryId)throws ResourceNotFoundException;
	List<Delivery> findAll();
	List<Delivery> getDeliveryByDeliveryBoyId(long deliveryBoyId)throws ResourceNotFoundException;
	public List<Delivery> findAllorders();
}
