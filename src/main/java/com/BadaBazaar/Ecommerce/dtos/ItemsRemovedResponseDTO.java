package com.BadaBazaar.Ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsRemovedResponseDTO {

    private int itemId;
    private String productName;
    private int price;
    private int quantity;
}
