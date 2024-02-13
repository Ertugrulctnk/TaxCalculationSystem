package com.example.TaxCalculationSystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxCalculationSystem.entity.Product;
import com.example.TaxCalculationSystem.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@RequestBody Product productRequest) {
		try {
			Product newProduct = new Product();
			newProduct.setName(productRequest.getName());
			newProduct.setPrice(productRequest.getPrice());
			productService.addProduct(newProduct);

			return ResponseEntity.ok("Product added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product");
		}
	}

	@GetMapping("/getById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {

		Product Product = productService.getProductById(productId);

		return new ResponseEntity<>(Product, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {

		Boolean isProductOwner;
		try {
			isProductOwner = productService.isProductOwner(product.getId(), product.getOwner().getId());

			if (isProductOwner) {
				productService.updateProduct(product);
				return ResponseEntity.status(HttpStatus.OK).body("Product Update Successfull");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("You can only update your own products.");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId, @RequestBody Long ownerId) {

		Boolean isProductOwner;
		try {
			isProductOwner = productService.isProductOwner(productId, ownerId);
			if (isProductOwner) {
				productService.deleteProduct(productId);
				return ResponseEntity.status(HttpStatus.OK).body("Product Delete Successfull");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("You can only delete your own products.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
}
