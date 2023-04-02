package com.BadaBazaar.Ecommerce.service.impl;

import com.BadaBazaar.Ecommerce.converter.CardConverter;
import com.BadaBazaar.Ecommerce.dtos.CardDto;
import com.BadaBazaar.Ecommerce.dtos.CardRequestDto;
import com.BadaBazaar.Ecommerce.dtos.CardResponseDto;
import com.BadaBazaar.Ecommerce.enums.CardType;
import com.BadaBazaar.Ecommerce.exception.CustomerNotFoundException;
import com.BadaBazaar.Ecommerce.exception.InvalidCardNoException;
import com.BadaBazaar.Ecommerce.model.Card;
import com.BadaBazaar.Ecommerce.model.Customer;
import com.BadaBazaar.Ecommerce.repository.CardRepository;
import com.BadaBazaar.Ecommerce.repository.CustomerRepository;
import com.BadaBazaar.Ecommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;


    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException, InvalidCardNoException {

        //check if the customer Id is valid or not
        Customer customer = null;

        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException();
        }

        //check if user has provided 16 digit card number
        String cardNo = cardRequestDto.getCardNo();

        if(cardNo.length()!=16)
            throw new InvalidCardNoException("Provided card no exceeds the allowed 16 digit length");

        if(isNumeric(cardNo)==false){
            throw new InvalidCardNoException("Invalid card number format");
        }

        //create Card object using builder

        Card card = CardConverter.cardRequestDtoToCard(cardRequestDto);

        card.setCustomer(customer);

        //add card to customers cardList
        customer.getCardList().add(card);

        cardRepository.save(card);

        String customerName = customer.getName();
        String mask = "XXXX-XXXX-XXXX-";

        List<CardDto> cardDtoList = new ArrayList<>();

        //create the cardDto for every card of the customer
        for(Card card1: customer.getCardList()){

            CardDto cardDto = new CardDto();
            cardDto.setCardNo(mask+card1.getCardNo().substring(12));
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }

        CardResponseDto cardResponseDto = CardConverter.cardToCardResponseDtp(card);

        cardResponseDto.setCardDtoList(cardDtoList);

        return cardResponseDto;
    }


    //checks if any of the num of the card is anything other than a digit then return false
    public boolean isNumeric(String s) throws InvalidCardNoException {
        for(char c:s.toCharArray()){
            if(!(c-'0'>=0 && c-'0'<=9)){
                return false;
            }
        }
        return true;
    }
}
