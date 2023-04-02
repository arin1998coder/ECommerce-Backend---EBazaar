package com.BadaBazaar.Ecommerce.exception;


public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(){
        super("Customer Not Found");
    }
}
