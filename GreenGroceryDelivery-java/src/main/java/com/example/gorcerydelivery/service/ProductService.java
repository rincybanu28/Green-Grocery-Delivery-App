package com.example.gorcerydelivery.service;

import java.util.List;

import com.example.gorcerydelivery.entity.Category;
import com.example.gorcerydelivery.entity.Product;
import com.example.gorcerydelivery.entity.ProductPagging;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;





public interface ProductService {
	public Product addProduct(Product product);
	public List<Product> findAll();
	//public Product save(Product product);
	public void deleteById(long productId) throws ResourceNotFoundException;
	Product findById(long productId) throws ResourceNotFoundException;
	List<Product> findByCategory(Category category);
	
	ProductPagging findByCategory(Category category, Integer pageNo, Integer pageSize);
	ProductPagging getAllProduct(Integer pageNo, Integer pageSize);
	List<Product> findByPriceEquals(double price);
	ProductPagging findByProductname(String keyword, Integer pageNo, Integer pageSize);
	public Product updateProduct(Product product, long productId)throws ResourceNotFoundException ;
	//Book updateBook(Book book, long bookId);
	
	
}
