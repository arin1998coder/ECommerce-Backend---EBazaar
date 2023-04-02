package com.BadaBazaar.Ecommerce.exception;


public class NotEnoughQuantityException extends Exception{

    public NotEnoughQuantityException(){
        super("Required Quantity of the product not available");
    }
}
