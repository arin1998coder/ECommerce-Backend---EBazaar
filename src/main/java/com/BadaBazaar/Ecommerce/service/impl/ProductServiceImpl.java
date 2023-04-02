package com.BadaBazaar.Ecommerce.service.impl;

import com.BadaBazaar.Ecommerce.converter.ProductConverter;
import com.BadaBazaar.Ecommerce.dtos.ProductAddRequestDto;
import com.BadaBazaar.Ecommerce.dtos.ProductResponseDto;
import com.BadaBazaar.Ecommerce.enums.Category;
import com.BadaBazaar.Ecommerce.exception.CategoryNotFoundException;
import com.BadaBazaar.Ecommerce.exception.ProductNotFoundException;
import com.BadaBazaar.Ecommerce.exception.SellerNotFoundException;
import com.BadaBazaar.Ecommerce.model.Product;
import com.BadaBazaar.Ecommerce.model.Seller;
import com.BadaBazaar.Ecommerce.repository.ProductRepository;
import com.BadaBazaar.Ecommerce.repository.SellerRepository;
import com.BadaBazaar.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductAddRequestDto productAddRequestDto) throws SellerNotFoundException {

        //check if the seller id provided is correct
        Seller seller;

        try{
            seller = sellerRepository.findById(productAddRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new SellerNotFoundException();
        }

        //seller is valid

        //create the product

        Product product = ProductConverter.productAddRequestDtoToProduct(productAddRequestDto);

        product.setSeller(seller);

        //add product to seller's productList
        seller.getProductList().add(product);

        productRepository.save(product);

        //set the response attributes in response dto
        ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDTO(product);

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> findProductsByCategory(String category) throws CategoryNotFoundException, ProductNotFoundException {

        HashSet<String> categoriesSet = new HashSet<>();

        //add all the categories to the categories set in lowercase string
        for(Category category1:Category.values()){
            categoriesSet.add(category1.name().toLowerCase());
        }

        //if the user inputted category is invalid or not available show exception categorhy not found
        if(!categoriesSet.contains(category.toLowerCase())){
            throw new CategoryNotFoundException();
        }

        //at this point i know user provided category is a valid one
        //convert the user provided category string to ENum Category

        Category enumCategory = Category.valueOf(category.toUpperCase());

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        //iterate over all the products of the required category and convert to a ProdResponseDTo and add to ProdResponseDTOList
        for(Product product:productRepository.findByCategory(enumCategory)){

            ProductResponseDto productDto = ProductConverter.productToProductResponseDTO(product);

            productResponseDtoList.add(productDto);
        }
        //if no products found of the required category
        if(productResponseDtoList.isEmpty()){
            throw new ProductNotFoundException("Currently no product is available of category: "+enumCategory);
        }
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> findProductsByPriceInAscendingOrder() {

        List<Product> productList = productRepository.findProductsByPriceInAscendingOrder();

        //create a list of productResponse dto
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(Product product : productList){

            //convert the product to Product Response dto and add to the responseDtoList
            ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDTO(product);

            productResponseDtoList.add(productResponseDto);
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> findProductByPriceInDescendingOrder() {
        List<Product> productList = productRepository.findProductByPriceInDescendingOrder();

        //create a list of productResponse dto
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(Product product : productList){

            //convert the product to Product Response dto and add to the responseDtoList
            ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDTO(product);

            productResponseDtoList.add(productResponseDto);
        }

        return productResponseDtoList;
    }


}
