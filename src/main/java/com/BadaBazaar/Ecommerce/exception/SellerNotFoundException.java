package com.BadaBazaar.Ecommerce.exception;

import lombok.Data;


public class SellerNotFoundException extends Exception{

    public SellerNotFoundException(){
        super("Seller id is invalid");
    }
}
