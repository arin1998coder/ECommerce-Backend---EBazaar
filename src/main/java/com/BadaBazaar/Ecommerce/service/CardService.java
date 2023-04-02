package com.BadaBazaar.Ecommerce.service;

import com.BadaBazaar.Ecommerce.dtos.CardRequestDto;
import com.BadaBazaar.Ecommerce.dtos.CardResponseDto;
import com.BadaBazaar.Ecommerce.exception.CustomerNotFoundException;
import com.BadaBazaar.Ecommerce.exception.InvalidCardNoException;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException, InvalidCardNoException;
}
