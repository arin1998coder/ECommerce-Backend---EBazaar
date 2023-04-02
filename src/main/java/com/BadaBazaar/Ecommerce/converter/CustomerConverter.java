package com.BadaBazaar.Ecommerce.converter;

import com.BadaBazaar.Ecommerce.dtos.CustomerRequestDto;
import com.BadaBazaar.Ecommerce.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConverter {

    //converts a Customer Response dto obj to Customer obj
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .address(customerRequestDto.getAddress())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

}
