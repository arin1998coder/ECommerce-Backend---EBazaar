package com.BadaBazaar.Ecommerce.service;


import com.BadaBazaar.Ecommerce.dtos.ProductAddRequestDto;
import com.BadaBazaar.Ecommerce.dtos.ProductResponseDto;
import com.BadaBazaar.Ecommerce.exception.CategoryNotFoundException;
import com.BadaBazaar.Ecommerce.exception.ProductNotFoundException;
import com.BadaBazaar.Ecommerce.exception.SellerNotFoundException;
import com.BadaBazaar.Ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    ProductResponseDto addProduct(ProductAddRequestDto productAddRequestDto) throws SellerNotFoundException;

    List<ProductResponseDto> findProductsByCategory(String category) throws CategoryNotFoundException, ProductNotFoundException;

    List<ProductResponseDto> findProductsByPriceInAscendingOrder();

    List<ProductResponseDto> findProductByPriceInDescendingOrder();
}
