/**
 * 
 */
package com.groupad.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupad.backend.model.Order;

/**
 * @author Boho Kim
 *
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
	public List<Order> findByEmail(String email);
}