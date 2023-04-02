package com.BadaBazaar.Ecommerce.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {

    private int id;

    private String name;

    private String email;

    private String mobNo;

    private String panNo;
}
