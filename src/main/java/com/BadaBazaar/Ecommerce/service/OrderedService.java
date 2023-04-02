package com.BadaBazaar.Ecommerce.service;

import com.BadaBazaar.Ecommerce.dtos.OrderRequestDto;
import com.BadaBazaar.Ecommerce.dtos.OrderResponseDto;
import com.BadaBazaar.Ecommerce.exception.*;
import org.springframework.stereotype.Service;

public interface OrderedService{

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, CardDoesNotExistException, NotEnoughQuantityException, ProductOutOfStockException, InvalidRequiredQuantityException, InterruptedException;
}
