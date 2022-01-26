package com.arsenbaktiyarov.productservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@PostMapping
	public String createProduct() {
		return "HTTP POST handled";
	}
	
	@GetMapping
	public String getProduct() {
		return "HTTP GET handled";
	}
	
	@PutMapping
	public String updateProduct() {
		return "HTTP PUT handled";
	}
	
	@DeleteMapping
	public String deleteProduct() {
		return "HTTP DELETE handled";
	}
	
	
}
