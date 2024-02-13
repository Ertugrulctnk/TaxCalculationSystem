package com.example.TaxCalculationSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaxCalculationSystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByOwnerId(Long ownerId);
}
