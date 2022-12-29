/**
 * 
 */
package com.groupad.backend.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

/**
 * @author Boho Kim
 *
 */
public class OrderedProductPK implements Serializable {
	@Column(nullable = false, name = "order_no")
	private Long orderId;
	@Column(nullable = false, name = "product_no")
	private Long productId;

	public OrderedProductPK() {

	}

	public OrderedProductPK(Long orderId, Long productId) {
		this.orderId = orderId;
		this.productId = productId;
	}

	/**
	 * @return the orderNo
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return productId the productNo
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productNo to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedProductPK other = (OrderedProductPK) obj;
		return Objects.equals(orderId, other.orderId) && Objects.equals(productId, other.productId);
	}
}
