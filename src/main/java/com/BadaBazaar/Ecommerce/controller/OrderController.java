package com.BadaBazaar.Ecommerce.controller;

import com.BadaBazaar.Ecommerce.dtos.OrderRequestDto;
import com.BadaBazaar.Ecommerce.dtos.OrderResponseDto;
import com.BadaBazaar.Ecommerce.service.OrderedService;
import com.BadaBazaar.Ecommerce.service.impl.OrderedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderedServiceImpl orderedService;

    @PostMapping("/place_order")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){

        OrderResponseDto orderResponseDto;

        try{
            orderResponseDto = orderedService.placeOrder(orderRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orderResponseDto,HttpStatus.OK);
    }
}
