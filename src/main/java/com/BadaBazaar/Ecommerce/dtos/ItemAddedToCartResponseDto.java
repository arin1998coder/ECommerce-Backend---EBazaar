package com.BadaBazaar.Ecommerce.dtos;

import com.BadaBazaar.Ecommerce.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemAddedToCartResponseDto {

    private String productName;
    private Category category;
    private int price;

    private int quantity;

}
