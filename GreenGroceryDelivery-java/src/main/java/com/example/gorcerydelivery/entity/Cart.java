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
@Table(name = "cart_table")
@SequenceGenerator(name = "generator11", sequenceName = "seq11", initialValue = 1500)
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator11")
	@Column(name = "cart_id")
	private long cartId;
	private int quantity;
	private double mrpPrice;
	
	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="productId")
	private Product product;
	
	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="customerId")
	private Customer customer;
	

	public Cart() {
  
	}

	public Cart(long cartId, int quantity, double mrpPrice, Product product, Customer customer) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		this.mrpPrice = mrpPrice;
		this.product = product;
		this.customer = customer;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(double mrpPrice) {
		this.mrpPrice = mrpPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", quantity=" + quantity + ", mrpPrice=" + mrpPrice + ", product=" + product
				+ ", customer=" + customer + "]";
	}

	
	
}
