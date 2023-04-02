package com.BadaBazaar.Ecommerce.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

    private String name;

    private int age;

    private String address;

    private String email;

    private String mobNo;

}
