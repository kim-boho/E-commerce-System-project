/**
 * 
 */
package com.groupad.backend.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Michelangelo Granato
 * @author Boho Kim
 *
 */
@Entity
@Table(name = "visitevent")
public class VisitEvent implements Serializable {
	@Column(nullable = false, name = "id")
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long eventId;
	@Column(nullable = false, name = "ip")
	private String ip;
	@Column(nullable = false, name = "product_no")
	private Long productId;
	@Column(nullable = false, name = "eventdate")
	private LocalDate eventDate;
	@Column(nullable = false, name = "eventtype")
	private String eventType;

	public VisitEvent() {
	}

	public VisitEvent(Long eventId, String ip, LocalDate localDate, Long productId, String eventType) {
		this.eventId = eventId;
		this.ip = ip;
		this.eventDate = localDate;
		this.productId = productId;
		this.eventType = eventType;
	}

	public VisitEvent(String ip, LocalDate localDate, Long productId, String eventType) {
		this.ip = ip;
		this.eventDate = localDate;
		this.productId = productId;
		this.eventType = eventType;
	}

	/**
	 * @return the ip
	 */
	public String getIP() {
		return ip;
	}

	/**
	 * @param iP the iP to set
	 */
	public void setIP(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the eventtype
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventtype the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VisitEvent other = (VisitEvent) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VisitEvent [ip=" + ip + ", productId=" + productId + ", eventDate=" + eventDate + ", eventType="
				+ eventType + "]";
	}

}