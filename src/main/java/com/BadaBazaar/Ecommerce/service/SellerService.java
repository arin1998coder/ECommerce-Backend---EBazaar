package com.BadaBazaar.Ecommerce.service;

import com.BadaBazaar.Ecommerce.dtos.SellerAddRequestDto;
import com.BadaBazaar.Ecommerce.dtos.SellerDto;
import com.BadaBazaar.Ecommerce.exception.SellerNotFoundException;
import com.BadaBazaar.Ecommerce.model.Seller;

import java.util.List;

public interface SellerService {

    String addSeller(SellerAddRequestDto sellerAddRequestDto);

    List<SellerDto> viewAllSellers();

    SellerDto getSellerByPanCard(String pancard) throws SellerNotFoundException;
}
