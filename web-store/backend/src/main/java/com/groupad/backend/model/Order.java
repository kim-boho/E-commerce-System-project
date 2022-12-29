/**
 * 
 */
package com.groupad.backend.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Boho Kim
 *
 */
@Entity
@Table(name = "orders")
public class Order {
	@Column(name = "order_no")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long orderId;
	private String date;

	@Column(nullable = false, name = "email")
	private String email;

	@Column(nullable = false, name = "adrs_no")
	private Long addressId;

	public Order() {

	}

	public Order(String date, String email, Long addressId) {
		this.date = date;
		this.email = email;
		this.addressId = addressId;
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * @return the addressId
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(orderId, other.orderId);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", date=" + date + ", email=" + email + ", addressId=" + addressId + "]";
	}

}
