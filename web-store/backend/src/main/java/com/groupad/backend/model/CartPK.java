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
public class CartPK implements Serializable {
	@Column(nullable = false, name = "email")
	private String email;

	@Column(nullable = false, name = "product_no")
	private Long productId;

	public CartPK() {

	}

	public CartPK(String email, Long productId) {
		this.email = email;
		this.productId = productId;
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
	 * @param productId the productNo to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
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
		CartPK other = (CartPK) obj;
		return Objects.equals(email, other.email) && productId == other.productId;
	}
}
