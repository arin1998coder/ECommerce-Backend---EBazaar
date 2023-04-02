package com.BadaBazaar.Ecommerce.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerAddRequestDto {

    private String name;

    private String email;

    private String mobNo;

    private String panNo;

}
