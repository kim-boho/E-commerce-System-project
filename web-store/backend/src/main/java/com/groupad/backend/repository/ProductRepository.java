package com.groupad.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupad.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByType(String type);

	public List<Product> findByBrand(String brand);
}