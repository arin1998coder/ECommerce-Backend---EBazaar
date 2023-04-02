package com.BadaBazaar.Ecommerce.dtos;

import com.BadaBazaar.Ecommerce.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    private String cardNo;

    private CardType cardType;
}
