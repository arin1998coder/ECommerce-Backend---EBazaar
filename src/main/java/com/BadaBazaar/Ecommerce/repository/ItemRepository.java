package com.BadaBazaar.Ecommerce.repository;

import com.BadaBazaar.Ecommerce.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
