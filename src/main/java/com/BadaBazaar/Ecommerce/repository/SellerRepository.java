package com.BadaBazaar.Ecommerce.repository;

import com.BadaBazaar.Ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Seller findByPanNo(String pancard);

}
