package com.BadaBazaar.Ecommerce.service;

import com.BadaBazaar.Ecommerce.dtos.*;
import com.BadaBazaar.Ecommerce.exception.*;

import java.util.List;

public interface CartService {

    ItemAddedToCartResponseDto addToCart(AddToCartRequestDto addToCartRequestDto) throws CustomerNotFoundException, ProductNotFoundException, ProductOutOfStockException, NotEnoughQuantityException, InvalidRequiredQuantityException;

   List< OrderResponseDto> cartCheckout(CheckOutRequestDto checkOutRequestDto) throws CustomerNotFoundException, CardDoesNotExistException, EmptyCartException, NotEnoughQuantityException, ProductOutOfStockException, ProductNotFoundException, InvalidRequiredQuantityException, InterruptedException;

   List<ItemAddedToCartResponseDto> viewCart(int customerId) throws CustomerNotFoundException, EmptyCartException;

   List<ItemsRemovedResponseDTO> removeItemsFromCart(ItemsRemoveRequestDTO itemsRemoveRequestDTO) throws Exception;
}
