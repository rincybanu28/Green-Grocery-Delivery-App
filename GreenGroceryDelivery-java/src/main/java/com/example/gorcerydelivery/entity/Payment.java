package com.example.gorcerydelivery.entity;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;

@Entity
@SequenceGenerator(name="generator8",sequenceName = "seq8",initialValue = 800)
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generator8")
	private long paymentId;
	
	@Min(value=0 , message = "Price should not negative")
	private double totalPrice;
	
	
	private LocalDate paidDate;
	
	@Min(value=0 , message = "Price should not negative")
	private double paidAmount;
	
	private  long orderId;
	
	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="customer_id")
	@JsonIgnore
	private Customer customer;

	public Payment() {
		super();
	}

	public Payment(long paymentId, @Min(value = 0, message = "Price should not negative") double totalPrice,
			LocalDate paidDate, @Min(value = 0, message = "Price should not negative") double paidAmount, long orderId,
			Customer customer) {
		super();
		this.paymentId = paymentId;
		this.totalPrice = totalPrice;
		this.paidDate = paidDate;
		this.paidAmount = paidAmount;
		this.orderId = orderId;
		this.customer = customer;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", totalPrice=" + totalPrice + ", paidDate=" + paidDate
				+ ", paidAmount=" + paidAmount + ", orderId=" + orderId + ", customer=" + customer + "]";
	}
	
	
		
}
