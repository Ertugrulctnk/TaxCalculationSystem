package com.example.TaxCalculationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxCalculationSystem.entity.Customer;
import com.example.TaxCalculationSystem.entity.Product;
import com.example.TaxCalculationSystem.service.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		try {
			Customer newCustomer = new Customer(customer.getUsername(), customer.getPassword());
			customerService.addCustomer(newCustomer);

			return ResponseEntity.ok("Customer added successfully");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding customer");
		}
	}

}
