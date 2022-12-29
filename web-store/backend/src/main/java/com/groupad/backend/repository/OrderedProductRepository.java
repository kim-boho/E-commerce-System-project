package com.groupad.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupad.backend.model.OrderedProduct;
import com.groupad.backend.model.OrderedProductPK;

/**
 * @author Boho Kim
 *
 */

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, OrderedProductPK> {

}
