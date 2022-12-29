package com.groupad.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupad.backend.model.Review;

/**
 * @author Boho Kim
 *
 */

public interface ReviewRepository extends JpaRepository<Review, Long> {

	public List<Review> findByEmail(String email);

	public List<Review> findByProductId(Long productId);

	public Review findByEmailAndProductId(String email, Long productId);

}