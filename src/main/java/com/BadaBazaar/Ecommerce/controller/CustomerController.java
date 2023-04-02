package com.BadaBazaar.Ecommerce.controller;


import com.BadaBazaar.Ecommerce.dtos.CustomerRequestDto;
import com.BadaBazaar.Ecommerce.dtos.OrderDetailsDto;
import com.BadaBazaar.Ecommerce.model.Ordered;
import com.BadaBazaar.Ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/allOrders_by_id")
    public ResponseEntity getAllOrdersByCustomerId(@RequestParam int cusId){

        List<OrderDetailsDto> orderedList ;

        try{
            orderedList = customerService.getAllOrdersByCustomerId(cusId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(orderedList,HttpStatus.ACCEPTED);

    }
}
