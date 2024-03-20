package com.example.gorcerydelivery.entity;

import java.sql.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;


import jakarta.persistence.*;

//orderId,orderDate,price,quantity,totalPrice,productId.
@Entity
@SequenceGenerator(name = "generator5", sequenceName = "seq5", initialValue = 500)
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator5")
	private long orderId;

	private Date orderedDate;

	private String orderStatus;

	private double mrpPrice;
	
	private double totalPrice;

	private float quantity;

	private float totalQuantity;

	private String paymentStatus;
	
	private String productName;
	
	private String productImage;

	@ManyToMany(cascade=CascadeType.MERGE)
  private List<Product> product;
	
	@ManyToOne
  @JoinColumn(name = "customerId", nullable = false)
  private Customer customer;

	@OneToOne
	@JoinColumn(name = "cartId")
	private Cart cart;
	
	
	
	/*
	 * @OneToMany(mappedBy = "customer")
	 * 
	 * @JsonIgnore private List<Order> orders = new ArrayList<>();
	 * 
	 */

	public Order() {
		super();
	}
	public Order(long orderId, Date orderedDate, String orderStatus, double mrpPrice, double totalPrice, float quantity,
			float totalQuantity, String paymentStatus, String productName, String productImage, List<Product> product,
			Customer customer, Cart cart) {
		super();
		this.orderId = orderId;
		this.orderedDate = orderedDate;
		this.orderStatus = orderStatus;
		this.mrpPrice = mrpPrice;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
		this.totalQuantity = totalQuantity;
		this.paymentStatus = paymentStatus;
		this.productName = productName;
		this.productImage = productImage;
		this.product = product;
		this.customer = customer;
		this.cart = cart;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public Date getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getMrpPrice() {
		return mrpPrice;
	}
	public void setMrpPrice(double mrpPrice) {
		this.mrpPrice = mrpPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public float getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(float totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderedDate=" + orderedDate + ", orderStatus=" + orderStatus
				+ ", mrpPrice=" + mrpPrice + ", totalPrice=" + totalPrice + ", quantity=" + quantity
				+ ", totalQuantity=" + totalQuantity + ", paymentStatus=" + paymentStatus + ", productName="
				+ productName + ", productImage=" + productImage + ", product=" + product + ", customer=" + customer
				+ ", cart=" + cart + "]";
	}
	
	
	
	
}