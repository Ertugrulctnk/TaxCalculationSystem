package com.example.TaxCalculationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaxCalculationSystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByUsername(String username);
}
