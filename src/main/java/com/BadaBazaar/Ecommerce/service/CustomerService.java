package com.BadaBazaar.Ecommerce.service;

import com.BadaBazaar.Ecommerce.dtos.CustomerRequestDto;
import com.BadaBazaar.Ecommerce.dtos.OrderDetailsDto;
import com.BadaBazaar.Ecommerce.exception.CustomerNotFoundException;
import com.BadaBazaar.Ecommerce.model.Ordered;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    String addCustomer(CustomerRequestDto customerRequestDto);

    List<OrderDetailsDto> getAllOrdersByCustomerId(int customerId) throws CustomerNotFoundException;
}
