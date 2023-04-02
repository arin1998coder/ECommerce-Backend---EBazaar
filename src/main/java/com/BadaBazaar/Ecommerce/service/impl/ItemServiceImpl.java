package com.BadaBazaar.Ecommerce.service.impl;

import com.BadaBazaar.Ecommerce.converter.ItemConverter;
import com.BadaBazaar.Ecommerce.dtos.ItemResponseDTO;
import com.BadaBazaar.Ecommerce.exception.ProductNotFoundException;
import com.BadaBazaar.Ecommerce.model.Item;
import com.BadaBazaar.Ecommerce.model.Product;
import com.BadaBazaar.Ecommerce.repository.ItemRepository;
import com.BadaBazaar.Ecommerce.repository.ProductRepository;
import com.BadaBazaar.Ecommerce.service.ItemService;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public ItemResponseDTO viewItem(int prodId) throws ProductNotFoundException {

       //validate prodId
        Product product = null;
        try{
            product = productRepository.findById(prodId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product ID");
        }

        //product should be listed as items
        Item item =new Item();

        item.setProduct(product);
        product.setItem(item); //map the product to the items, since now this product has become an items


        ItemResponseDTO itemResponseDTO = ItemConverter.productToItemResponseDTO(item);

        productRepository.save(product);

        return itemResponseDTO;

    }
}
