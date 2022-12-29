package com.groupad.backend.model.requests;

import com.groupad.backend.model.Cart;

public class CartSetRequest {
    private Cart[] cart;

    public Cart[] getCart() {
        return cart;
    }

    public void setCart(Cart[] cart) {
        this.cart = cart;
    }
}
