package com.BadaBazaar.Ecommerce.exception;


public class CategoryNotFoundException extends Exception{

    public CategoryNotFoundException(){
        super("Category not found");
    }
}
