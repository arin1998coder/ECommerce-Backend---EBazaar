package com.BadaBazaar.Ecommerce.repository;

import com.BadaBazaar.Ecommerce.enums.Category;
import com.BadaBazaar.Ecommerce.model.Card;
import com.BadaBazaar.Ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {


}
