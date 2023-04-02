package com.BadaBazaar.Ecommerce.controller;

import com.BadaBazaar.Ecommerce.dtos.CardRequestDto;
import com.BadaBazaar.Ecommerce.dtos.CardResponseDto;
import com.BadaBazaar.Ecommerce.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardServiceImpl cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        CardResponseDto cardResponseDto;

        try{
            cardResponseDto = cardService.addCard(cardRequestDto);

        }
        catch (Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
      return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED) ;
    }
}
