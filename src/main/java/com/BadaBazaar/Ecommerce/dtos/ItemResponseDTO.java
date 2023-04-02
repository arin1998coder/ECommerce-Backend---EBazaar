package com.BadaBazaar.Ecommerce.dtos;

import com.BadaBazaar.Ecommerce.enums.Category;
import com.BadaBazaar.Ecommerce.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {

    private String name;

    private int price;

    private Category category;

    private ProductStatus productStatus;
}
