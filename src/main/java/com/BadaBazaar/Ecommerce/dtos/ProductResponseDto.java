package com.BadaBazaar.Ecommerce.dtos;


import com.BadaBazaar.Ecommerce.enums.Category;
import com.BadaBazaar.Ecommerce.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private int pId;

    private int sellerId;

    private ProductStatus productStatus;

    private String productName;

    private int price;

    private int quantity;

    private Category category;

}
