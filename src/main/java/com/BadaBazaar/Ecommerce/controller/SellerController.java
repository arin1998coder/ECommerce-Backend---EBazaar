package com.BadaBazaar.Ecommerce.controller;

import com.BadaBazaar.Ecommerce.dtos.SellerAddRequestDto;
import com.BadaBazaar.Ecommerce.dtos.SellerDto;
import com.BadaBazaar.Ecommerce.model.Seller;
import com.BadaBazaar.Ecommerce.service.SellerService;
import com.BadaBazaar.Ecommerce.service.impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerServiceImpl sellerService;

    @PostMapping("/add_seller")
    public String addSeller(@RequestBody SellerAddRequestDto sellerAddRequestDto){

        return sellerService.addSeller(sellerAddRequestDto);
    }

    @GetMapping("/get_all_sellers")
    public List<SellerDto> getAllSellers(){
        return sellerService.viewAllSellers();
    }

    @GetMapping("/find_by_pancard")
    public ResponseEntity getSellerByPan(@RequestParam String pan){

        SellerDto seller;

        try{
            seller = sellerService.getSellerByPanCard(pan);
        }
        catch (Exception e){
            return new ResponseEntity("Seller Not Found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(seller,HttpStatus.ACCEPTED);
    }
}
