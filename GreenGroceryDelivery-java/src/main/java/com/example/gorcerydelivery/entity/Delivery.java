package com.example.gorcerydelivery.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@SequenceGenerator(name = "generator3", sequenceName = "seq3", initialValue = 300)
@Table(name = "delivery_table")
public class Delivery {
//deliveryId,currentDate,status,orderId,deliveryBoyId
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator3")
	private long deliveryId;
	
	
	 @Column(name = "delivery_date") 
	    private String deliveryDate;
	

	private String status;
	
	@ManyToOne
	@JoinColumn(name = "deliveryBoyId")
	private DeliveryBoy deliveryBoy;
	
	 @ManyToOne(cascade = CascadeType.PERSIST)
	    @JoinColumn(name = "orderId")
	    private Order order;

//	@OneToMany(mappedBy = "delivery")
//	@JsonIgnore
//	private List<Order> orders = new ArrayList<>();
	
	
	
	public Delivery() {
		super();
	}

public Delivery(long deliveryId, String deliveryDate, String status, DeliveryBoy deliveryBoy, Order order) {
	super();
	this.deliveryId = deliveryId;
	this.deliveryDate = deliveryDate;
	this.status = status;
	this.deliveryBoy = deliveryBoy;
	this.order = order;
}

public long getDeliveryId() {
	return deliveryId;
}

public void setDeliveryId(long deliveryId) {
	this.deliveryId = deliveryId;
}

public String getDeliveryDate() {
	return deliveryDate;
}

public void setDeliveryDate(String deliveryDate) {
	this.deliveryDate = deliveryDate;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public DeliveryBoy getDeliveryBoy() {
	return deliveryBoy;
}

public void setDeliveryBoy(DeliveryBoy deliveryBoy) {
	this.deliveryBoy = deliveryBoy;
}

public Order getOrder() {
	return order;
}

public void setOrder(Order order) {
	this.order = order;
}

@Override
public String toString() {
	return "Delivery [deliveryId=" + deliveryId + ", deliveryDate=" + deliveryDate + ", status=" + status
			+ ", deliveryBoy=" + deliveryBoy + ", order=" + order + "]";
}


}
