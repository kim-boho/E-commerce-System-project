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
 * @author Michelangelo Granato
 */
@Entity
@Table(name = "address")
public class Address {
	@Column(name = "adrs_no")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long addressId;
	@Column(name = "street")
	private String street;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "city")
	private String city;
	@Column(name = "province")
	private String region;
	@Column(name = "apartment")
	private String apartment;
	@Column(name = "country")
	private String country;
	@Column(name = "zip")
	private String postalCode;
	@Column(name = "phone")
	private String phone;

	@Column(nullable = false, name = "email")
	private String email;

	public Address() {

	}

	public Address(String firstName, String lastName, String street, String apartment, String city, String region,
			String country, String postalCode, String phone,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.apartment = apartment;
		this.region = region;
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * @return the adrsNo
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param adrsNo the adrsNo to set
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the zip
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setPostalCode(String zip) {
		this.postalCode = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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

	@Override
	public int hashCode() {
		return Objects.hash(addressId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(addressId, other.addressId);
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
