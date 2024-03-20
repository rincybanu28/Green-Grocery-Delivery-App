package com.example.gorcerydelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gorcerydelivery.entity.Category;
import com.example.gorcerydelivery.entity.Product;
import com.example.gorcerydelivery.entity.ProductPagging;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.service.ProductService;


import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
		product.setProductId(0);
		return new ResponseEntity<Product>(productService.addProduct(product),HttpStatus.CREATED);	
	}
	
	@GetMapping("/findAll")
	public List<Product> findAll(){
		return productService.findAll();	
	}
	
	@GetMapping("/findById/{productId}")
	public Product findById(@PathVariable long productId) throws ResourceNotFoundException {
		return productService.findById(productId);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product)
	{
		Product udatedProduct= productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(udatedProduct);
	}
	
	@DeleteMapping("/delete/{productId}")
	public String deleteProduct(@PathVariable long productId) throws ResourceNotFoundException
	{
		productService.deleteById(productId);
		return "Deleted Product Id : "+productId;
	}
	
	
	@GetMapping("category/{categoryId}")
	public List<Product> getAllProductsByCategory(@PathVariable("categoryId") int categoryId) {
		Category c = Category.valueOf(categoryId);
		return productService.findByCategory(c);
	}
	
	@GetMapping("/{categoryId}/{pageNo}/{pageSize}")
	public ProductPagging getAllProductsByCategory(@PathVariable("categoryId") int categoryId, @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		Category c = Category.valueOf(categoryId);
		return productService.findByCategory(c, pageNo, pageSize);
	}
	
	@GetMapping("/{pageNo}/{pageSize}")
	public ProductPagging getAllProducts(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		return productService.getAllProduct(pageNo, pageSize);
	}
	
	@GetMapping("/mrp/{price}")
	public List<Product> findByPrice(@PathVariable("price") double price) {
		return productService.findByPriceEquals(price);
	}
	
	@GetMapping("/Search/{keyword}/{pageNo}/{pageSize}")
	public ProductPagging getProductByName(@PathVariable("keyword") String keyword,
			@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		return productService.findByProductname(keyword, pageNo, pageSize);
	}

	@PutMapping("/update/{productId}")
	private Product update(@Valid @RequestBody Product product) throws ResourceNotFoundException {

		Product p1 = productService.findById(product.getProductId());

		System.out.println("OK");
		Product result = productService.updateProduct(product, p1.getProductId());
		return result;
	}
}
