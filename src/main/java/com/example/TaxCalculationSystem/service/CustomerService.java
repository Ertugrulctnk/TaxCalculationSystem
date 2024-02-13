package com.example.TaxCalculationSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaxCalculationSystem.entity.Customer;
import com.example.TaxCalculationSystem.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository cr;

	@Autowired
	public CustomerService(CustomerRepository cr) {
		this.cr = cr;
	}

	public Customer findByUsername(String username) {
		return cr.findByUsername(username);
	}

	public void saveCustomer(Customer customer) {
		cr.save(customer);
	}

	public Optional<Customer> findById(Long id) {
		return cr.findById(id);
	}

	public void addCustomer(Customer newCustomer) throws Exception {
		try {
			cr.save(newCustomer);
		} catch (Exception e) {
			throw new Exception("Error occurred while adding customer");
		}

	}
}
