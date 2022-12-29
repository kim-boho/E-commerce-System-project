/**
 * @author Michelangelo Granato
 */
package com.groupad.backend.bl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupad.backend.model.Cart;
import com.groupad.backend.model.CartPK;
import com.groupad.backend.model.responses.CartSetResponse;
import com.groupad.backend.repository.CartRepository;
import com.groupad.backend.repository.ProductRepository;

@Service
public class CartBL {
	@Autowired
	ProductRepository pRepo;
	@Autowired
	CartRepository cRepo;

	public CartBL() {
	}

	@Transactional
	public CartSetResponse setCart(Cart[] cart) {

		// clear old cart
		deleteByEmail(cart[0].getEmail());
		return addCart(cart);
	}

	@Transactional
	public CartSetResponse addCart(Cart[] cart) {
		CartSetResponse csr = new CartSetResponse();

		// iterate through cart and add to db
		for (Cart item : cart) {

			Boolean isProductAvailable = pRepo.findById(item.getProductId()).isPresent();
			if (Boolean.TRUE.equals(isProductAvailable)) {
				Cart i = new Cart();
				CartPK pk = new CartPK(item.getEmail(), item.getProductId());
				if (cRepo.findById(pk).isPresent()) {
					i = cRepo.getReferenceById(pk);
					i.setQuantity(i.getQuantity() + item.getQuantity());
				} else
					i = item;

				cRepo.save(i);

			} else {
				csr.addErrMsg("Error Adding To Cart. Product with ID: " + item.getProductId() + " not available.");
				csr.setResponse(false);
			}
		}
		return csr;
	}

	@Transactional
	private void deleteByEmail(String email) {
		cRepo.deleteByEmail(email);

	}

	public void delete(Cart cartItem) {
		cRepo.delete(cartItem);
	}

	@Transactional
	public List<Cart> findAll() {
		return cRepo.findAll();
	}

	@Transactional
	public List<Cart> findByEmail(String email) {
		return cRepo.findByEmail(email);
	}

}
