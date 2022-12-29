/**
 * 
 */
package com.groupad.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groupad.backend.model.Cart;
import com.groupad.backend.model.requests.CartSetRequest;
import com.groupad.backend.model.responses.CartSetResponse;

import com.groupad.backend.bl.CartBL;

/**
 * @author Boho Kim
 * @author Michelangelo Granato
 */

@RestController
public class CartController {

	@Autowired
	CartBL cartBL;

	public CartController() {
	}

	@GetMapping("/cart")
	public List<Cart> getAllByEmail(@RequestParam("email") String email) {
		return cartBL.findByEmail(email);
	}

	@GetMapping("/cart/all")
	public List<Cart> getAllCart() {
		return cartBL.findAll();
	}

	@CrossOrigin
	@PostMapping("/cart")
	public CartSetResponse setCart(@RequestBody CartSetRequest cart) {
		return cartBL.setCart(cart.getCart());
	}

	@CrossOrigin
	@PostMapping("/cart/add")
	public CartSetResponse addCart(@RequestBody Cart cart) {
		Cart[] c = { cart };
		return cartBL.addCart(c);
	}

	@DeleteMapping("/cart")
	public void delete(@RequestParam Cart cartItem) {
		cartBL.delete(cartItem);
	}

}
