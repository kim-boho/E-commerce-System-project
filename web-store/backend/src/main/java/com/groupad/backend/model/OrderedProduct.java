/**
 * 
 */
package com.groupad.backend.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author Boho Kim
 *
 */
@Entity
@Table(name = "orderedproducts")
@IdClass(OrderedProductPK.class)
public class OrderedProduct {

	@Column(name = "order_no")
	private @Id Long orderId;
	@Column(name = "product_no")
	private @Id Long productId;
	@Column(name = "quantity")
	private int quantity;

	public OrderedProduct() {

	}

	public OrderedProduct(Long orderId, Long productId, int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
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
	 * @return the productNo
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productNo the productNo to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		OrderedProduct other = (OrderedProduct) obj;
		return Objects.equals(orderId, other.orderId) && Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "OrderedProduct [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}

}