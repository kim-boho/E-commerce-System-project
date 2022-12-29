/**
 * 
 */
package com.groupad.backend.model.requests;

/**
 * @author Boho Kim
 * @author Michelangelo Granato
 */
public class PaymentMethod {
	private Long cardNumber;
	private int expiry;
	private int cvc;
	private String nameOnCard;

	public Long getCardNumber() {
		return cardNumber;
	}

	public int getExpiry() {
		return expiry;
	}

	public void setExpiry(int expiry) {
		this.expiry = expiry;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
}