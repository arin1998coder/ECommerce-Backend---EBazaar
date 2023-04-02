package com.BadaBazaar.Ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private int id;

    private Date orderDate;

    List<Pair> productNames;

    private String cardUsed;

    private int totalCost;

    private int deliveryCharge;
}
