package com.BadaBazaar.Ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private int orderId;

    private Date orderDate;

    private String productName;

    private int price;

    private int quantity;

    private String cardUsed;

    private int totalCost;

    private int deliveryCharge;
}
