package com.BadaBazaar.Ecommerce.repository;

import com.BadaBazaar.Ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {


}
