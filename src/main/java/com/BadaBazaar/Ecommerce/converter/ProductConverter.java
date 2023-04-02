package com.BadaBazaar.Ecommerce.converter;

import com.BadaBazaar.Ecommerce.dtos.ItemResponseDTO;
import com.BadaBazaar.Ecommerce.dtos.ProductAddRequestDto;
import com.BadaBazaar.Ecommerce.dtos.ProductResponseDto;
import com.BadaBazaar.Ecommerce.enums.ProductStatus;
import com.BadaBazaar.Ecommerce.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConverter {

    //converts a ProductAddRequestDto to Product
    public static Product productAddRequestDtoToProduct(ProductAddRequestDto productAddRequestDto){

        return Product.builder()
                .name(productAddRequestDto.getName())
                .price(productAddRequestDto.getPrice())
                .quantity(productAddRequestDto.getQuantity())
                .category(productAddRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    //coverts a Product to ProductResponseDTO
    public static ProductResponseDto productToProductResponseDTO(Product product){


        return ProductResponseDto.builder()
                .productName(product.getName())
                .pId(product.getId())
                .sellerId(product.getSeller().getId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

}
