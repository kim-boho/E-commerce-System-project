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
@Table(name = "review")
public class Review {
	@Column(nullable = false, name = "review_no")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long reviewId;
	@Column(nullable = false, name = "email")
	private String email;
	@Column(nullable = false, name = "product_no")
	private Long productId;
	@Column(name = "rate")
	private int rate;
	@Column(name = "description")
	private String description;

	public Review() {

	}

	public Review(String email, Long productId, int rate, String description) {
		this.email = email;
		this.productId = productId;
		this.rate = rate;
		this.description = description;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reviewId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(reviewId, other.reviewId);
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", email=" + email + ", productId=" + productId + ", rate=" + rate
				+ ", description=" + description + "]";
	}

}
