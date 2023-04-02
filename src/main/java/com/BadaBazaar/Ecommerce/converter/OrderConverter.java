package com.BadaBazaar.Ecommerce.converter;

import com.BadaBazaar.Ecommerce.dtos.OrderDetailsDto;
import com.BadaBazaar.Ecommerce.dtos.Pair;
import com.BadaBazaar.Ecommerce.model.Item;
import com.BadaBazaar.Ecommerce.model.Ordered;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OrderConverter {

    public static OrderDetailsDto orderToOrderDetailsDto(Ordered order){

        List<Pair> productNames = new ArrayList<>();

        for(Item item : order.getItems()){
            Pair pair = new Pair();
            pair.setProductName(item.getProduct().getName());
            pair.setPrice(item.getProduct().getPrice());
            pair.setQuantity(item.getRequiredQuantity());

            productNames.add(pair);
        }

        return OrderDetailsDto.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .productNames(productNames)
                .cardUsed(order.getCardUsedForPayment())
                .totalCost(order.getTotalCost())
                .deliveryCharge(order.getDeliveryCharge())
                .build();
    }
}
