package com.example.gorcerydelivery.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.gorcerydelivery.entity.Category;
import com.example.gorcerydelivery.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findByProductId(long productId);

    List<Product> findByCategory(Category category);

    Page<Product> findByCategory(Category category, Pageable pageable);

    @Query("select p from Product p where p.price = :price")
    List<Product> findByPrice(double price);

    Page<Product> findByProductNameContains(String keyword, Pageable pageable);
}
