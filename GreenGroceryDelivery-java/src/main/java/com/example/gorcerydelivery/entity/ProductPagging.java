package com.example.gorcerydelivery.entity;

import java.util.List;

public class ProductPagging {
	private List<Product> products;
	private long totalProducts;
	public ProductPagging() {
		super();
	}
	public ProductPagging(List<Product> products, long totalProducts) {
		super();
		this.products = products;
		this.totalProducts = totalProducts;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public long getTotalProducts() {
		return totalProducts;
	}
	public void setTotalProducts(long totalProducts) {
		this.totalProducts = totalProducts;
	}
	@Override
	public String toString() {
		return "ProductPagging [products=" + products + ", totalProducts=" + totalProducts + "]";
	}
	
	
}
