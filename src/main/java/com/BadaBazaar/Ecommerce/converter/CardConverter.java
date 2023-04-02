package com.BadaBazaar.Ecommerce.converter;

import com.BadaBazaar.Ecommerce.dtos.CardRequestDto;
import com.BadaBazaar.Ecommerce.dtos.CardResponseDto;
import com.BadaBazaar.Ecommerce.model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConverter {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
    }

    public static CardResponseDto cardToCardResponseDtp(Card card){

        return CardResponseDto.builder().name(card.getCustomer().getName()).build();
    }
}
