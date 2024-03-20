package com.example.gorcerydelivery.service;

import java.util.List;

import com.example.gorcerydelivery.entity.DeliveryBoy;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;



public interface DeliveryBoyService {

	DeliveryBoy saveDeliveryBoy(DeliveryBoy deliveryBoy);
	DeliveryBoy loginDeliveryBoy(DeliveryBoy deliveryBoy) throws ResourceNotFoundException;
	DeliveryBoy getDeliveryBoyBydeliveryBoyId(long deliveryBoyId) throws ResourceNotFoundException;
	List<DeliveryBoy> findAll();
	public DeliveryBoy updateDeliveryBoyById(long deliveryBoyId, DeliveryBoy deliveryBoy)throws ResourceNotFoundException;

	void deleteBydeliveryBoyId(long deliveryBoyId) throws ResourceNotFoundException;
	DeliveryBoy getDeliveryBoyBydeliveryBoyEmail(String deliveryBoyEmail)throws ResourceNotFoundException;
}
