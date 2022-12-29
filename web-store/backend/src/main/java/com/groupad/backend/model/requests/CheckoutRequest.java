/**
 * 
 */
package com.groupad.backend.model.requests;

import com.groupad.backend.model.Address;
import com.groupad.backend.model.Cart;

/**
 * @author Michelangelo Granato
 *
 */
public class CheckoutRequest {
	private PaymentMethod paymentMethod;
	private Address shippingInfo;
	private Cart[] cart;

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Address getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(Address shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public Cart[] getCart() {
		return cart;
	}

	public void setCart(Cart[] cart) {
		this.cart = cart;
	}

	public String getEmail() {
		return cart[0].getEmail();
	}

}
