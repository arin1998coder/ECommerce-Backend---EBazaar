package com.BadaBazaar.Ecommerce.converter;


import com.BadaBazaar.Ecommerce.dtos.ItemResponseDTO;
import com.BadaBazaar.Ecommerce.dtos.ItemsRemovedResponseDTO;
import com.BadaBazaar.Ecommerce.model.Item;
import com.BadaBazaar.Ecommerce.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemConverter {

    //converts Product to ItemResponseDTO, by first getting the product associated with the items and then using that product to make a Item ResponseDTO
    public static ItemResponseDTO productToItemResponseDTO(Item item){

        Product product = item.getProduct();

        return ItemResponseDTO.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }

    public static ItemsRemovedResponseDTO ItemToItemRemovalResponseDTO(Item item){

        return ItemsRemovedResponseDTO.builder()
                .itemId(item.getId())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .price(item.getProduct().getPrice())
                .build();
    }
}
