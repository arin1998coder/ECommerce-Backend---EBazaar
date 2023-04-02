package com.BadaBazaar.Ecommerce.repository;

import com.BadaBazaar.Ecommerce.model.Card;
import com.BadaBazaar.Ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {


}
