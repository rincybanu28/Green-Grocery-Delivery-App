package com.example.gorcerydelivery.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
@Entity
@SequenceGenerator(name = "generator7", sequenceName = "seq7", initialValue = 700)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator7")
    @Column(name="supplierId")
    private long supplierId;

    @NotBlank(message = "First name is required")
    @Column(name = "supplierName", nullable = false, length =30)
    private String supplierName;


    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid Email Format!")
    @Column(name = "supplierEmail", nullable = false, length =50)
    private String supplierEmail;

    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
    private String supplierPhoneNumber;

    @NotBlank(message = "Address is required")
    @Column(name = "supplierAddress", nullable = false, length =150)
    private String supplierAddress;

    @NotBlank(message = "City is required")
    @Column(name = "supplierCity", nullable = false, length =50)
    private String supplierCity;

    @NotBlank(message = "State is required")
    @Column(name = "supplierState", nullable = false, length =50)
    private String supplierState;

    @OneToMany(mappedBy = "supplier") 
    private List<Product> products;
    
	public Supplier() {
		super();
	}

	public Supplier(long supplierId, @NotBlank(message = "First name is required") String supplierName,
			@NotEmpty(message = "Email cannot be empty") @Email(message = "Invalid Email Format!") String supplierEmail,
			@Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits") String supplierPhoneNumber,
			@NotBlank(message = "Address is required") String supplierAddress,
			@NotBlank(message = "City is required") String supplierCity,
			@NotBlank(message = "State is required") String supplierState) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierEmail = supplierEmail;
		this.supplierPhoneNumber = supplierPhoneNumber;
		this.supplierAddress = supplierAddress;
		this.supplierCity = supplierCity;
		this.supplierState = supplierState;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierCity() {
		return supplierCity;
	}

	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}

	public String getSupplierState() {
		return supplierState;
	}

	public void setSupplierState(String supplierState) {
		this.supplierState = supplierState;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierEmail="
				+ supplierEmail + ", supplierPhoneNumber=" + supplierPhoneNumber + ", supplierAddress="
				+ supplierAddress + ", supplierCity=" + supplierCity + ", supplierState=" + supplierState + "]";
	}

	
    
}
