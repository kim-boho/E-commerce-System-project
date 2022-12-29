package com.groupad.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupad.backend.model.Cart;
import com.groupad.backend.model.CartPK;

/**
 * 
 * @author Boho Kim
 * @author Michelangelo Granato
 */

public interface CartRepository extends JpaRepository<Cart, CartPK> {
	public List<Cart> findByEmail(String email);

	public void deleteById(CartPK cartPk);

	public long deleteByEmail(String email);
}