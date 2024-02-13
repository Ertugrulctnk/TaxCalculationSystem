package com.example.TaxCalculationSystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.TaxCalculationSystem.entity.Product;
import com.example.TaxCalculationSystem.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public List<Product> getProductsByOwnerId(Long ownerId) {
		return productRepository.findByOwnerId(ownerId);
	}

	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	public Boolean isProductOwner(Long productId, Long ownerId) throws Exception {

		// Ürünün sahibini kontrol et
		Product existingProduct = getProductById(productId);
		if (existingProduct == null) {
			throw new Exception("The product you were looking for was not found");
		} else {
			if (existingProduct.getOwner() == null) {
				throw new Exception("The owner of this product is not set");
			}
		}

		// Ürün bu kişiye mi ait?
		if (!existingProduct.getOwner().getId().equals(ownerId)) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}

	}
}
