package com.BadaBazaar.Ecommerce.dtos;

import com.BadaBazaar.Ecommerce.enums.Category;
import com.BadaBazaar.Ecommerce.model.Seller;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddRequestDto {

    private String name;

    private int price;

    private int quantity;

    private Category category;

    private int sellerId;
}
