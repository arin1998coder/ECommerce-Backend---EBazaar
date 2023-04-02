package com.BadaBazaar.Ecommerce.controller;

import com.BadaBazaar.Ecommerce.dtos.ItemResponseDTO;
import com.BadaBazaar.Ecommerce.exception.ProductNotFoundException;
import com.BadaBazaar.Ecommerce.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemServiceImpl itemService;

    @PostMapping("/view_item/{prodId}")
    public ResponseEntity viewItem(@PathVariable int prodId){

        ItemResponseDTO itemResponseDTO;

        try{
            itemResponseDTO = itemService.viewItem(prodId);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(itemResponseDTO,HttpStatus.OK);
    }
}
