package com.example.gorcerydelivery.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@SequenceGenerator(name = "generator6", sequenceName = "seq6", initialValue = 600)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator6")
    @Column(name="product_id")
    private long productId;

    @NotBlank(message = "Product name is required")
    @Column(name = "productName", nullable = false, length =30)
    private String productName;

    @Min(value = 0, message = "Product quantity must be positive")
    private int productQuantity;

    @NotBlank(message = "Product image URL is required")
    @Column(name="productImage")
    private String productImage;

    @NotNull(message = "Buy date is required")
    private Date buyDate;

    // Expiry date can be nullable
   // private Date expiryDate;

    @Positive(message = "Price must be positive")
    private double price;
    
  //  private String description;

   
    
    private Category category;
    
    @ManyToOne
   	@JoinColumn(name = "supplierId")
       private Supplier supplier;
    
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Cart> cart = new ArrayList<>();

	public Product() {
		super();
	}

	public Product(long productId, @NotBlank(message = "Product name is required") String productName,
			@Min(value = 0, message = "Product quantity must be positive") int productQuantity,
			@NotBlank(message = "Product image URL is required") String productImage,
			@NotNull(message = "Buy date is required") Date buyDate,
			@Positive(message = "Price must be positive") double price, Category category, Supplier supplier,
			List<Cart> cart) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productImage = productImage;
		this.buyDate = buyDate;
		this.price = price;
		this.category = category;
		this.supplier = supplier;
		this.cart = cart;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productQuantity="
				+ productQuantity + ", productImage=" + productImage + ", buyDate=" + buyDate + ", price=" + price
				+ ", category=" + category + ", supplier=" + supplier + ", cart=" + cart + "]";
	}
    
	
	//
//	@ManyToMany(mappedBy = "products", cascade = CascadeType.MERGE)
//	private List<Order> orders;
	
	
	
}