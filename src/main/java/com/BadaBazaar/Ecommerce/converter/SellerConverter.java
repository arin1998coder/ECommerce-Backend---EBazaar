package com.BadaBazaar.Ecommerce.converter;

import com.BadaBazaar.Ecommerce.dtos.SellerAddRequestDto;
import com.BadaBazaar.Ecommerce.dtos.SellerDto;
import com.BadaBazaar.Ecommerce.model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConverter {

    //converts SellerRequestDto to Seller
    public static Seller sellerReqDtoToSeller(SellerAddRequestDto sellerAddRequestDto){

        return Seller.builder()
                .name(sellerAddRequestDto.getName())
                .email(sellerAddRequestDto.getEmail())
                .mobNo(sellerAddRequestDto.getMobNo())
                .panNo(sellerAddRequestDto.getPanNo())
                .build();
    }

    //converts Seller To SellerResponseDTO
    public static SellerDto sellerToSellerDTO (Seller seller){

        return SellerDto.builder()
                .id(seller.getId())
                .email(seller.getEmail())
                .mobNo(seller.getMobNo())
                .panNo(seller.getPanNo())
                .name(seller.getName())
                .build();
    }
}
