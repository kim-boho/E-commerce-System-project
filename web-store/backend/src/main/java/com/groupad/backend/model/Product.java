package com.groupad.backend.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Column(nullable = false, name = "product_no")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "type")
	private String type;
	@Column(name = "brand")
	private String brand;
	@Column(name = "instock")
	private int instock;
	@Column(name = "price")
	private double price;
	@Column(name = "imgsrc")
	private String imgSrc;
	@Column(name = "imgalt")
	private String imgAlt;

	public Product(String name, String description, String type, String brand, int instock, double price,
			String imgSrc, String imgAlt) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.brand = brand;
		this.instock = instock;
		this.price = price;
		this.imgSrc = imgSrc;
		this.imgAlt = imgAlt;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return id;
	}

	/**
	 * @param productNo the productId to set
	 */
	public void setProductId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the instock
	 */
	public int getInstock() {
		return instock;
	}

	/**
	 * @param instock the instock to set
	 */
	public void setInstock(int instock) {
		this.instock = instock;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the imgsrc
	 */
	public String getImgSrc() {
		return imgSrc;
	}

	/**
	 * @param imgSrc the imgSrc to set
	 */
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	/**
	 * @param imgSrc the img alt text to get
	 */
	public String getImgAlt() {
		return imgAlt;
	}

	/**
	 * @param imgSrc the img alt text to set
	 */
	public void setImgAlt(String imgAlt) {
		this.imgAlt = imgAlt;
	}

	Product() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Product [productId=" + id + ", name=" + name + ", description=" + description + ", type="
				+ type
				+ ", brand=" + brand + ", instock=" + instock + ", price=" + price + ", imgsrc=" + imgSrc + "imgAlt="
				+ imgAlt + "]";
	}
}