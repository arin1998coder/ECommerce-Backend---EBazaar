package com.BadaBazaar.Ecommerce.repository;

import com.BadaBazaar.Ecommerce.enums.Category;
import com.BadaBazaar.Ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory(Category category);

    @Query(value = "select * from product p order by p.price asc limit 5",nativeQuery = true)
    List<Product> findProductsByPriceInAscendingOrder();

    @Query(value = "select * from product p order by p.price desc limit 5",nativeQuery = true)
    List<Product> findProductByPriceInDescendingOrder();
}
