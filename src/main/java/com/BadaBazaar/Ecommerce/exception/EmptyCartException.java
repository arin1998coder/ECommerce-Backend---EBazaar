package com.BadaBazaar.Ecommerce.exception;

public class EmptyCartException extends Exception {

    public EmptyCartException(){
        super("Cart is empty ! Please add products to checkout");
    }
}
