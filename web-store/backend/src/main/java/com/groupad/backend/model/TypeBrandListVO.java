/**
 * 
 */
package com.groupad.backend.model;

import java.util.List;

/**
 * @author Boho Kim
 *
 */

public class TypeBrandListVO {
	private List<String> types;
	private List<String> brands;

	/**
	 * @return the types
	 */

	public List<String> getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(List<String> types) {
		this.types = types;
	}

	/**
	 * @return the brands
	 */
	public List<String> getBrands() {
		return brands;
	}

	/**
	 * @param brands the brands to set
	 */
	public void setBrands(List<String> brands) {
		this.brands = brands;
	}

}
