/**
 * 
 */
package com.groupad.backend.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.groupad.backend.enums.EventType;
import com.groupad.backend.model.Cart;
import com.groupad.backend.model.Order;
import com.groupad.backend.model.OrderedProduct;
import com.groupad.backend.model.OrderedProductPK;
import com.groupad.backend.model.VisitEvent;
import com.groupad.backend.model.requests.CheckoutRequest;
import com.groupad.backend.model.requests.PaymentMethod;
import com.groupad.backend.model.validation.CreditCardValidation;
import com.groupad.backend.repository.CartRepository;
import com.groupad.backend.repository.OrderRepository;
import com.groupad.backend.repository.OrderedProductRepository;
import com.groupad.backend.repository.VisitEventRepository;

/**
 * @author Michelangelo-Granato
 * @author Boho Kim
 *
 */

@RestController
public class CheckoutController {

	@Autowired
	OrderRepository oRepo;

	@Autowired
	OrderedProductRepository opRepo;

	@Autowired
	CartRepository cRepo;

	@Autowired
	VisitEventRepository vRepo;

	// (cart page) with products -> (checkout page) select address or put new
	// address
	// -> (payment page) put credit card info and billing address [checkout confirm]
	// => if card is valid, put products as order to DB

	@CrossOrigin
	@PostMapping("/checkout")
	public CheckoutResponse checkout(@RequestBody CheckoutRequest request) {
		CheckoutResponse cr = new CheckoutResponse();

		// validation
		if (!validatePaymentMethod(request.getPaymentMethod())) {
			cr.addErrMsg("Payment method invalid");
			cr.setResult(false);
		}

		// generate date
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateForMysql = sdf.format(date);

		// order
		Order order = new Order(dateForMysql, request.getEmail(), request.getShippingInfo().getAddressId());
		oRepo.save(order);

		// get all user's cart products
		List<Cart> cartListByUser = cRepo.findByEmail(request.getEmail());

		// get client ip for log
		String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest().getRemoteAddr();
		// get date for log
		LocalDate today = LocalDate.now();

		for (Cart c : cartListByUser) {
			// make ordered product,save ordered product
			OrderedProductPK opPK = new OrderedProductPK(order.getOrderId(), c.getProductId());
			OrderedProduct op = new OrderedProduct(opPK.getOrderId(), opPK.getProductId(), c.getQuantity());
			opRepo.save(op);
			// delete product from user's cart
			cRepo.delete(c);

			// add purchase event to visitEventRepo
			vRepo.save(new VisitEvent(remoteAddress, today, opPK.getProductId(), EventType.PURCHASE.toString()));
		}

		return cr;
	}

	private boolean validatePaymentMethod(PaymentMethod card) {
		LocalDate now = LocalDate.now();
		int year = now.getYear() % 100;
		int month = now.getMonthValue();
		int expYear = card.getExpiry() % 100;
		int expMonth = card.getExpiry() / 100;

		if (expYear < year)
			return false;
		else if (expYear == year && expMonth < month) {
			return false;
		}
		if (card.getNameOnCard().isEmpty()) {
			return false;
		}
		if (card.getCvc() > 999 || card.getCvc() < 1)
			return false;

		if (CreditCardValidation.isValid(card.getCardNumber()))
			return true;
		else
			return false;
	}
}
