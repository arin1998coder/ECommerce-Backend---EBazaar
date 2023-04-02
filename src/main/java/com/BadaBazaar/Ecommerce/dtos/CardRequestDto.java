package com.BadaBazaar.Ecommerce.dtos;

import com.BadaBazaar.Ecommerce.enums.CardType;
import com.BadaBazaar.Ecommerce.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    private String cardNo;

    private int cvv;

    private CardType cardType;

    private int customerId;
}
