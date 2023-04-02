package com.BadaBazaar.Ecommerce.service;

import com.BadaBazaar.Ecommerce.dtos.ItemResponseDTO;
import com.BadaBazaar.Ecommerce.exception.ProductNotFoundException;

public interface ItemService {

    public ItemResponseDTO viewItem(int prodId) throws ProductNotFoundException;
}
