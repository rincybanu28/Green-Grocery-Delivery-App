package com.example.gorcerydelivery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.gorcerydelivery.entity.Category;
import com.example.gorcerydelivery.entity.Product;
import com.example.gorcerydelivery.entity.ProductPagging;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.ProductRepository;







@Service
public class ProductServiceImpl implements ProductService {

	@Autowired  //It will inject  repository object into service layer
	ProductRepository productRepository;
	
	
	
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		System.out.println("Book added Succesfully "+product);
		product.setProductName(product.getProductName());
		product.setProductQuantity(product.getProductQuantity());
		product.setProductImage(product.getProductImage());
		product.setBuyDate(product.getBuyDate());
		//product.setExpiryDate(product.getExpiryDate());
		
		product.setPrice(product.getPrice());
		//product.setDescription(null);
		return productRepository.save(product);
	}


	@Override
	public void deleteById(long productId) throws ResourceNotFoundException {
		if(productRepository.existsById(productId)) 
		{
			productRepository.deleteById(productId);
		}
		else
		{
			throw new ResourceNotFoundException("Product Id not found : "+productId);
		}	
	}

	@Override
	public Product findById(long productId) throws ResourceNotFoundException {
		Optional<Product> result = productRepository.findById(productId);
		Product product;
		if(result.isPresent()) 
		{
			product = result.get();
		}
		else 
		{
			throw new ResourceNotFoundException("Product id not found : "+productId);
		}
		return product;
	}

	@Override
	public List<Product> findByCategory(Category category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategory(category);
	}

	@Override
	public ProductPagging findByCategory(Category category, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		
		Pageable paging=PageRequest.of(pageNo, pageSize);
		Page<Product> pageResult = productRepository.findByCategory(category, paging);
		ProductPagging productPagging =new ProductPagging();
		productPagging.setTotalProducts(pageResult.getTotalElements());
		if(pageResult.hasContent()) {
			productPagging.setProducts(pageResult.getContent());
		}else {
			productPagging.setProducts(new ArrayList<Product>());
		}
		return productPagging;
	}

	@Override
	public ProductPagging getAllProduct(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable paging=PageRequest.of(pageNo, pageSize);
		Page<Product> pageResult = productRepository.findAll(paging);
		ProductPagging productPagging =new ProductPagging();
		productPagging.setTotalProducts(pageResult.getTotalElements());
		System.out.println(">>>>>"+ pageResult.getTotalPages());
		if(pageResult.hasContent()) {
			productPagging.setProducts(pageResult.getContent());
        } else {
        	productPagging.setProducts(new ArrayList<Product>());
        }
		return productPagging;
	}

	@Override
	public List<Product> findByPriceEquals(double price) {
		// TODO Auto-generated method stub
		return productRepository.findByPrice(price);
	}

//	@Override
//	public ProductPagging findByProductname(String keyword, Integer pageNo, Integer pageSize) {
//		// TODO Auto-generated method stub
//		Pageable paging = PageRequest.of(pageNo, pageSize);
//		Page<Product> pageResult = productRepository.findByProductNameContains(keyword, paging);
//		ProductPagging productPagging =new ProductPagging();
//		productPagging.setTotalProducts(pageResult.getTotalElements());
//		if(pageResult.hasContent()) {
//			productPagging.setProducts(pageResult.getContent());
//        } else {
//        	productPagging.setProducts(new ArrayList<Product>());
//        }
//		return productPagging;
//	}

	@Override
	public ProductPagging findByProductname(String keyword, Integer pageNo, Integer pageSize) {
	    Pageable paging = PageRequest.of(pageNo, pageSize);
	    Page<Product> pageResult = productRepository.findByProductNameContains(keyword, paging);

	    ProductPagging productPagging = new ProductPagging();
	    productPagging.setTotalProducts(pageResult.getTotalElements());

	    if (pageResult.hasContent()) {
	        productPagging.setProducts(pageResult.getContent());
	    } else {
	        productPagging.setProducts(new ArrayList<Product>());
	    }

	    return productPagging;
	}

	@Override
	public Product updateProduct(Product product, long productId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Product existingBook = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("book","bookId",productId));
		existingBook.setProductName(product.getProductName());
		existingBook.setPrice(product.getPrice());
		existingBook.setProductImage(product.getProductImage());
		//existingBook.setDescription(product.getDescription());		
		existingBook.setProductQuantity(product.getProductQuantity());

		productRepository.save(existingBook);	
		return existingBook;		
	}

	

}
