package com.BadaBazaar.Ecommerce.repository;

import com.BadaBazaar.Ecommerce.model.Ordered;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Integer> {

    @Query(value = "SELECT id FROM orders ORDER BY id DESC LIMIT 1", nativeQuery = true)
    int findLastOrderId();


    @Query(value = "SELECT o.order_date FROM orders o ORDER BY o.order_date DESC limit 1",nativeQuery = true)
    Date findLastOrderDate();
}
