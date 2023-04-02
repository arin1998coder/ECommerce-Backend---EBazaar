package com.BadaBazaar.Ecommerce.exception;


public class ProductOutOfStockException extends Exception{

    public ProductOutOfStockException(){
        super("Product out of stock!");
    }
}
