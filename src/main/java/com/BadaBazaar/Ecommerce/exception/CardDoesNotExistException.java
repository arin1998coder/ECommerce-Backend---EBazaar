package com.BadaBazaar.Ecommerce.exception;


public class CardDoesNotExistException extends Exception{

    public CardDoesNotExistException(){
        super("Card Id does not exist");
    }
}
