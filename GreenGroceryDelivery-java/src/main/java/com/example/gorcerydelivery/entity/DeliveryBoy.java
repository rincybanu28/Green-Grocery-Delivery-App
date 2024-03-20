package com.example.gorcerydelivery.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
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

@Entity
@SequenceGenerator(name = "generator12", sequenceName = "seq12", initialValue = 1700)
public class DeliveryBoy {
//	deliveryBoyId,deliveryBoyName,deliveryBoyAddress,deliveryBoyCity,deliveryBoyEmail,deliveyBoyContact. 

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator4")
	private long deliveryBoyId;
	
	@NotEmpty(message = " Name cannot be empty")
	private String deliveryBoyName;
	
	@NotEmpty(message = " Address cannot be empty")
	private String deliveryBoyAddress;
	
	@NotEmpty(message = "City cannot be empty")
	private String deliveryBoyCity;
	
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Invalid Email Format!")
	private String deliveryBoyEmail;
	
	@NotEmpty(message = "Phone number cannot be empty")
	@Pattern(regexp = "\\d{10}", message = "Enter valid phone number!")
	private String deliveyBoyContact;
	
	
	private String deliveryBoyPassword;
	
	@OneToMany(mappedBy = "deliveryBoy")
	@JsonIgnore
	private List<Delivery> delivery=new ArrayList<>();

	
	
	public DeliveryBoy() {
		super();
	}
	 @JsonCreator
public DeliveryBoy(long deliveryBoyId, @NotEmpty(message = " Name cannot be empty") String deliveryBoyName,
		@NotEmpty(message = " Address cannot be empty") String deliveryBoyAddress,
		@NotEmpty(message = "City cannot be empty") String deliveryBoyCity,
		@NotEmpty(message = "Email cannot be empty") @Email(message = "Invalid Email Format!") String deliveryBoyEmail,
		@NotEmpty(message = "Phone number cannot be empty") @Pattern(regexp = "\\d{10}", message = "Enter valid phone number!") String deliveyBoyContact,
		@Pattern(regexp = "^(?!\\d+$)(?:[a-zA-Z0-9][a-zA-Z0-9 @&$]*)?$", message = "Password must contain atleast one uppercase, lowercase, number and a special character") @NotEmpty(message = "password cannot be empty") String deliveryBoyPassword) {
	super();
	this.deliveryBoyId = deliveryBoyId;
	this.deliveryBoyName = deliveryBoyName;
	this.deliveryBoyAddress = deliveryBoyAddress;
	this.deliveryBoyCity = deliveryBoyCity;
	this.deliveryBoyEmail = deliveryBoyEmail;
	this.deliveyBoyContact = deliveyBoyContact;
	this.deliveryBoyPassword = deliveryBoyPassword;
}

public long getDeliveryBoyId() {
	return deliveryBoyId;
}

public void setDeliveryBoyId(long deliveryBoyId) {
	this.deliveryBoyId = deliveryBoyId;
}

public String getDeliveryBoyName() {
	return deliveryBoyName;
}

public void setDeliveryBoyName(String deliveryBoyName) {
	this.deliveryBoyName = deliveryBoyName;
}

public String getDeliveryBoyAddress() {
	return deliveryBoyAddress;
}

public void setDeliveryBoyAddress(String deliveryBoyAddress) {
	this.deliveryBoyAddress = deliveryBoyAddress;
}

public String getDeliveryBoyCity() {
	return deliveryBoyCity;
}

public void setDeliveryBoyCity(String deliveryBoyCity) {
	this.deliveryBoyCity = deliveryBoyCity;
}

public String getDeliveryBoyEmail() {
	return deliveryBoyEmail;
}

public void setDeliveryBoyEmail(String deliveryBoyEmail) {
	this.deliveryBoyEmail = deliveryBoyEmail;
}

public String getDeliveyBoyContact() {
	return deliveyBoyContact;
}

public void setDeliveyBoyContact(String deliveyBoyContact) {
	this.deliveyBoyContact = deliveyBoyContact;
}

public String getDeliveryBoyPassword() {
	return deliveryBoyPassword;
}

public void setDeliveryBoyPassword(String deliveryBoyPassword) {
	this.deliveryBoyPassword = deliveryBoyPassword;
}

@Override
public String toString() {
	return "DeliveryBoy [deliveryBoyId=" + deliveryBoyId + ", deliveryBoyName=" + deliveryBoyName
			+ ", deliveryBoyAddress=" + deliveryBoyAddress + ", deliveryBoyCity=" + deliveryBoyCity
			+ ", deliveryBoyEmail=" + deliveryBoyEmail + ", deliveyBoyContact=" + deliveyBoyContact
			+ ", deliveryBoyPassword=" + deliveryBoyPassword + "]";
}



}
