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
@Table(name = "cart")
@IdClass(CartPK.class)
public class Cart {
	@Column(name = "email")
	private @Id String email;
	@Column(name = "product_no")
	private @Id Long productId;
	@Column(name = "qnty")
	private int quantity;

	public Cart() {

	}

	public Cart(String email, Long productId, int quantity) {
		this.email = email;
		this.productId = productId;
		this.quantity = quantity;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the qnty
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param qnty the qnty to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(email, other.email) && productId == other.productId;
	}

	@Override
	public String toString() {
		return "Cart [email=" + email + ", productId=" + productId + ", quantity=" + quantity + "]";
	}

}
