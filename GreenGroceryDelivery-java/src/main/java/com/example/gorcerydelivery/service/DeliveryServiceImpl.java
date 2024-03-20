package com.example.gorcerydelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gorcerydelivery.entity.Delivery;
import com.example.gorcerydelivery.entity.Order;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.DeliveryBoyRepository;
import com.example.gorcerydelivery.repository.DeliveryRepository;
import com.example.gorcerydelivery.repository.OrderRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class DeliveryServiceImpl implements DeliveryService {

    @PersistenceContext
    private EntityManager entityManager;
	@Autowired
	DeliveryRepository deliveryRepository;
	
	@Autowired
	DeliveryBoyRepository deliveryBoyRepository;
	
	@Autowired
	OrderService orderService;
	
	public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
		super();
		this.deliveryRepository = deliveryRepository;
	}

	@Override
	public Delivery addDeliveryOrder(Delivery delivery,long orderId) {
		Order order=orderService.getOrderById(orderId);
		// TODO Auto-generated method stub
		return deliveryRepository.save(delivery);
	}

//	@Override
//	public Delivery addDelivery(Delivery delivery) {
//		
//		return deliveryRepository.save(delivery);
//	}
	
	@Override
	public Delivery addDelivery(Delivery delivery) {
	    // Fetch the Order entity associated with the Delivery
	    // If the Order is detached, fetch it from the database
	    Order order = delivery.getOrder();
	    if (order != null && order.getOrderId() != 0) {
	        order = entityManager.find(Order.class, order.getOrderId());
	        delivery.setOrder(order); // Update the Delivery entity with the managed Order entity
	    }
	    
	    return deliveryRepository.save(delivery);
	}
	@Override
	public void deleteById(long deliveryId) throws ResourceNotFoundException {
		if(deliveryRepository.existsById(deliveryId)) {
			deliveryRepository.deleteById(deliveryId);
		}
		else {
			throw new ResourceNotFoundException("Delivery Id not Found: "+deliveryId);
		}

	}

	@Override
	public Delivery getDeliveryById(long deliveryId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return deliveryRepository.findById(deliveryId).orElseThrow(()->new ResourceNotFoundException("Delivery id not found"));
		
	}

	@Override
	public List<Delivery> findAll() {
		// TODO Auto-generated method stub
		return deliveryRepository.findAll();
	}

	@Override
	public List<Delivery> getDeliveryByDeliveryBoyId(long deliveryBoyId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		if(deliveryBoyRepository.existsById(deliveryBoyId)) {
			return deliveryRepository.findByDeliveryBoyDeliveryBoyId(deliveryBoyId);
		}
		else {
			throw new ResourceNotFoundException("Delivery id not found"+deliveryBoyId);
		}
	}
	
	

	@Override
	public List<Delivery> findAllorders() {
	
		    return deliveryRepository.findAllDeliveriesWithOrders();
	
	}

}
