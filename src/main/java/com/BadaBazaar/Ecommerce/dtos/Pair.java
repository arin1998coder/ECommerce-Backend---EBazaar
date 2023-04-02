package com.BadaBazaar.Ecommerce.dtos;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pair {

    private String productName;

    private int price;

    private int quantity;
}
