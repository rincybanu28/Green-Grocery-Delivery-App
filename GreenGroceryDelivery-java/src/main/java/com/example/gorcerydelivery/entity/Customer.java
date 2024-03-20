package com.example.gorcerydelivery.entity;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

//customerId,customerFirstName,customerLastName,customerEmail,customerPhoneNumber,customerDOB,customerAddress,customerCity,customerState,customerPassword,role
@Entity
@SequenceGenerator(name = "generator2", sequenceName = "seq2", initialValue = 200)
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator2")
	private long customerId;

	@NotEmpty(message = "Customer first name cannot be empty")
	private String customerFirstName;

	@NotEmpty(message = "Customer last name cannot be empty")
	private String customerLastName;

	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Invalid Email Format!")
	private String customerEmail;

	@NotEmpty(message = "Phone number cannot be empty")
	@Pattern(regexp = "\\d{10}", message = "Enter valid phone number!")
	private String customerPhoneNumber;

	@NotEmpty(message = "Customer Pincode cannot be empty")
	private String pincode;

	@NotEmpty(message = "Address cannot be empty")
	private String customerAddress;

	

	@Pattern(regexp = "^(?!\\d+$)(?:[a-zA-Z0-9][a-zA-Z0-9 @&$]*)?$", message = "Password must contain atleast one uppercase, lowercase, number and a special character")
	@NotEmpty(message = "password cannot be empty")
	private String customerPassword;

	@NotEmpty(message = "Role cannot be empty")
	private String role;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Cart> cart = new ArrayList<>();

//	@OneToMany(mappedBy = "customer")
//	@JsonIgnore
//	private List<Payment> payments = new ArrayList<>();

	public Customer() {
		super();
	}

public Customer(long customerId, @NotEmpty(message = "Customer first name cannot be empty") String customerFirstName,
		@NotEmpty(message = "Customer last name cannot be empty") String customerLastName,
		@NotEmpty(message = "Email cannot be empty") @Email(message = "Invalid Email Format!") String customerEmail,
		@NotEmpty(message = "Phone number cannot be empty") @Pattern(regexp = "\\d{10}", message = "Enter valid phone number!") String customerPhoneNumber,
		@NotEmpty(message = "Customer Pincode cannot be empty") String pincode,
		@NotEmpty(message = "Address cannot be empty") String customerAddress,
		@Pattern(regexp = "^(?!\\d+$)(?:[a-zA-Z0-9][a-zA-Z0-9 @&$]*)?$", message = "Password must contain atleast one uppercase, lowercase, number and a special character") @NotEmpty(message = "password cannot be empty") String customerPassword,
		@NotEmpty(message = "Role cannot be empty") String role, List<Cart> cart) {
	super();
	this.customerId = customerId;
	this.customerFirstName = customerFirstName;
	this.customerLastName = customerLastName;
	this.customerEmail = customerEmail;
	this.customerPhoneNumber = customerPhoneNumber;
	this.pincode = pincode;
	this.customerAddress = customerAddress;
	this.customerPassword = customerPassword;
	this.role = role;
	this.cart = cart;
}

public long getCustomerId() {
	return customerId;
}

public void setCustomerId(long customerId) {
	this.customerId = customerId;
}

public String getCustomerFirstName() {
	return customerFirstName;
}

public void setCustomerFirstName(String customerFirstName) {
	this.customerFirstName = customerFirstName;
}

public String getCustomerLastName() {
	return customerLastName;
}

public void setCustomerLastName(String customerLastName) {
	this.customerLastName = customerLastName;
}

public String getCustomerEmail() {
	return customerEmail;
}

public void setCustomerEmail(String customerEmail) {
	this.customerEmail = customerEmail;
}

public String getCustomerPhoneNumber() {
	return customerPhoneNumber;
}

public void setCustomerPhoneNumber(String customerPhoneNumber) {
	this.customerPhoneNumber = customerPhoneNumber;
}

public String getPincode() {
	return pincode;
}

public void setPincode(String pincode) {
	this.pincode = pincode;
}

public String getCustomerAddress() {
	return customerAddress;
}

public void setCustomerAddress(String customerAddress) {
	this.customerAddress = customerAddress;
}

public String getCustomerPassword() {
	return customerPassword;
}

public void setCustomerPassword(String customerPassword) {
	this.customerPassword = customerPassword;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public List<Cart> getCart() {
	return cart;
}

public void setCart(List<Cart> cart) {
	this.cart = cart;
}

@Override
public String toString() {
	return "Customer [customerId=" + customerId + ", customerFirstName=" + customerFirstName + ", customerLastName="
			+ customerLastName + ", customerEmail=" + customerEmail + ", customerPhoneNumber=" + customerPhoneNumber
			+ ", pincode=" + pincode + ", customerAddress=" + customerAddress + ", customerPassword=" + customerPassword
			+ ", role=" + role + ", cart=" + cart + "]";
}

	
	
}
