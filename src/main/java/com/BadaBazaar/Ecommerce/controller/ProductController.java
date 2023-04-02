package com.BadaBazaar.Ecommerce.controller;

import com.BadaBazaar.Ecommerce.dtos.ProductAddRequestDto;
import com.BadaBazaar.Ecommerce.dtos.ProductResponseDto;
import com.BadaBazaar.Ecommerce.exception.CategoryNotFoundException;
import com.BadaBazaar.Ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/add_product")
    public ResponseEntity addProduct(@RequestBody ProductAddRequestDto productAddRequestDto){

        ProductResponseDto productResponseDto;

        try{
            productResponseDto = productService.addProduct(productAddRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(productResponseDto, HttpStatus.ACCEPTED);

    }

    @GetMapping("/get_products_by_category/{category}")
    public ResponseEntity findProductsByCategory(@PathVariable String category) throws CategoryNotFoundException {
        List<ProductResponseDto> productDtoList;

        try{
            productDtoList=productService.findProductsByCategory(category);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productDtoList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/top_5/least_expensive_products")
    public List<ProductResponseDto> getProductsByPriceInAscendingOrder(){
        return productService.findProductsByPriceInAscendingOrder();
    }
    @GetMapping("/top_5/most_expensive_products")
    public List<ProductResponseDto> getProductsByPriceInDescendingOrder(){
        return productService.findProductByPriceInDescendingOrder();
    }
}
