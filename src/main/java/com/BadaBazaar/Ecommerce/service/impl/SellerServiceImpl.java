package com.BadaBazaar.Ecommerce.service.impl;


import com.BadaBazaar.Ecommerce.converter.SellerConverter;
import com.BadaBazaar.Ecommerce.dtos.SellerAddRequestDto;
import com.BadaBazaar.Ecommerce.dtos.SellerDto;
import com.BadaBazaar.Ecommerce.exception.SellerNotFoundException;
import com.BadaBazaar.Ecommerce.model.Seller;
import com.BadaBazaar.Ecommerce.repository.SellerRepository;
import com.BadaBazaar.Ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public String addSeller(SellerAddRequestDto sellerAddRequestDto) {

        //create seller object using sellerReqDto

        Seller seller = SellerConverter.
                        sellerReqDtoToSeller(sellerAddRequestDto);

        sellerRepository.save(seller);

        return "Added Successfully and ID : "+seller.getId();
    }

    @Override
    public List<SellerDto> viewAllSellers() {

        List<SellerDto> sellerDtoList = new ArrayList<>();

        for(Seller seller: sellerRepository.findAll()){

            SellerDto sellerDto = SellerConverter.sellerToSellerDTO(seller);

            sellerDtoList.add(sellerDto);

        }

        return sellerDtoList;
    }

    @Override
    public SellerDto getSellerByPanCard(String pancard) throws SellerNotFoundException {


         Seller seller = sellerRepository.findByPanNo(pancard);

         //checking if seller found or not
        if(seller==null){
            throw new SellerNotFoundException();
        }

         SellerDto sellerDto = SellerConverter.sellerToSellerDTO(seller);

         return sellerDto;
    }


}
