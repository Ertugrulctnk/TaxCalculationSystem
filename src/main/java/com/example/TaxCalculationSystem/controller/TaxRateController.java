package com.example.TaxCalculationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxCalculationSystem.entity.Product;
import com.example.TaxCalculationSystem.service.ProductService;

@RestController
@RequestMapping("/api")
public class TaxRateController {

	@Autowired
	private ProductService ps;

	@GetMapping("/calculate-tax/{productId}")
	public ResponseEntity<Double> calculateTax(@PathVariable Long productId) {
		try {

			Product product = ps.getProductById(productId);

			if (product == null) {
				return ResponseEntity.notFound().build();
			}

			double taxRate = getTaxRateForProduct(product);

			double taxAmount = product.getPrice() * (taxRate / 100);

			return ResponseEntity.ok(taxAmount);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	private double getTaxRateForProduct(Product product) {

		return 18.0;
	}
}
