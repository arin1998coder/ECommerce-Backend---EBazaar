package com.BadaBazaar.Ecommerce.controller;

import com.BadaBazaar.Ecommerce.dtos.*;
import com.BadaBazaar.Ecommerce.repository.CartRepository;
import com.BadaBazaar.Ecommerce.service.CartService;
import com.BadaBazaar.Ecommerce.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody AddToCartRequestDto addToCartRequestDto){
        ItemAddedToCartResponseDto itemAddedToCartResponseDto;

        try{
            itemAddedToCartResponseDto = cartService.addToCart(addToCartRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(itemAddedToCartResponseDto,HttpStatus.OK);

    }

    @GetMapping("/view/{customerId}")
    public ResponseEntity viewCart(@PathVariable int customerId){

        List<ItemAddedToCartResponseDto> listOfItemsInCart;

        try{
            listOfItemsInCart = cartService.viewCart(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listOfItemsInCart,HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity checkOut(@RequestBody CheckOutRequestDto checkOutRequestDto){

        List<OrderResponseDto> listOfOrdersPlaced;

        try{
            listOfOrdersPlaced = cartService.cartCheckout(checkOutRequestDto);
        }
        catch (Exception e){
            String errorMessage = "Cannot Proceed To Checkout Because "+e.getMessage();
            e.printStackTrace();
            return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listOfOrdersPlaced,HttpStatus.OK);
    }

    @PutMapping("/remove")
    public ResponseEntity removeItemsFromCart(@RequestBody ItemsRemoveRequestDTO itemsRemoveRequestDTO){

        List<ItemsRemovedResponseDTO> responseDTOList;

        try{
            responseDTOList = cartService.removeItemsFromCart(itemsRemoveRequestDTO);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDTOList,HttpStatus.ACCEPTED);
    }
}
