package com.example.gorcerydelivery.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gorcerydelivery.entity.DeliveryBoy;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.DeliveryBoyRepository;



@Service
public class DeliveryBoyServiceImpl implements DeliveryBoyService {

	@Autowired
	DeliveryBoyRepository deliveryBoyRepository;
	
	

	@Override
	public DeliveryBoy saveDeliveryBoy(DeliveryBoy deliveryBoy) {
		// TODO Auto-generated method stub
		return deliveryBoyRepository.save(deliveryBoy);
	}

	@Override
	public DeliveryBoy loginDeliveryBoy(DeliveryBoy deliveryBoy) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return deliveryBoyRepository.findByDeliveryBoyEmailAndDeliveryBoyPassword(deliveryBoy.getDeliveryBoyEmail(), deliveryBoy.getDeliveryBoyPassword()).orElseThrow(()-> new ResourceNotFoundException("Email id or password not found"));
		}

	@Override
	public DeliveryBoy getDeliveryBoyBydeliveryBoyId(long deliveryBoyId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return deliveryBoyRepository.findById(deliveryBoyId).orElseThrow(()-> new ResourceNotFoundException("Delivery Boy id not found"+deliveryBoyId));
		}

	@Override
	public List<DeliveryBoy> findAll() {
		// TODO Auto-generated method stub
		return deliveryBoyRepository.findAll();
	}

	@Override
	public void deleteBydeliveryBoyId(long deliveryBoyId) throws ResourceNotFoundException {
		if(deliveryBoyRepository.existsById(deliveryBoyId)) {
			deliveryBoyRepository.deleteById(deliveryBoyId);
		}
		else {
			throw new ResourceNotFoundException("Delivery Boy Id not Found: "+deliveryBoyId);
		}
	}

	@Override
	public DeliveryBoy getDeliveryBoyBydeliveryBoyEmail(String deliveryBoyEmail) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return deliveryBoyRepository.findByDeliveryBoyEmail(deliveryBoyEmail).orElseThrow(()-> new ResourceNotFoundException("Email id not found"+deliveryBoyEmail));
		
	}
	
	@Override
	public DeliveryBoy updateDeliveryBoyById(long deliveryBoyId, DeliveryBoy deliveryBoy)
			throws ResourceNotFoundException {
		DeliveryBoy result = getDeliveryBoyBydeliveryBoyId(deliveryBoyId);
		if (result != null) {
			deliveryBoyRepository.save(deliveryBoy);
			return deliveryBoy;
		} else
			throw new ResourceNotFoundException("Delivery Boy  is not available !!!");
	}

}
