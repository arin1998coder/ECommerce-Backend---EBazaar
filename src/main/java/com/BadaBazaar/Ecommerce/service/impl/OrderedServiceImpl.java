package com.BadaBazaar.Ecommerce.service.impl;

import com.BadaBazaar.Ecommerce.dtos.OrderRequestDto;
import com.BadaBazaar.Ecommerce.dtos.OrderResponseDto;
import com.BadaBazaar.Ecommerce.enums.ItemStatus;
import com.BadaBazaar.Ecommerce.enums.ProductStatus;
import com.BadaBazaar.Ecommerce.exception.*;
import com.BadaBazaar.Ecommerce.model.*;
import com.BadaBazaar.Ecommerce.repository.CardRepository;
import com.BadaBazaar.Ecommerce.repository.CustomerRepository;
import com.BadaBazaar.Ecommerce.repository.OrderedRepository;
import com.BadaBazaar.Ecommerce.repository.ProductRepository;
import com.BadaBazaar.Ecommerce.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class OrderedServiceImpl extends Thread implements OrderedService  {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderedRepository orderRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, CardDoesNotExistException, NotEnoughQuantityException, ProductOutOfStockException, InvalidRequiredQuantityException, InterruptedException {

        Customer customer=null;
        Card card = null;
        Product product = null;

        int requiredQuantity = orderRequestDto.getQuantity();
        int customerId = orderRequestDto.getCustomerId();
        int cardId = orderRequestDto.getCardId();
        int productId = orderRequestDto.getProductId();

        int cost;
        int totalCost;
        int deliveryCharge;
        String cardNo="";

        //validate customer id

        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException();
        }

        //validate product id
        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Product does'nt exist! Invalid Product Id");
        }

        //validate card id
        try{
            card = cardRepository.findById(cardId).get();
        }
        catch (Exception e){
            throw new CardDoesNotExistException();
        }

        //check if product status is "AVAILABLE" or "OUT_OF_STOCK"
        if(product.getProductStatus()== ProductStatus.OUT_OF_STOCK){
            throw new ProductOutOfStockException();
        }

        //validate the required Quantity of user
        if(requiredQuantity<=0){
            throw new InvalidRequiredQuantityException("Quantity "+requiredQuantity+" Requested by you is invalid");
        }
        //check requiredQuantity hsould be less than the current available quantity of product
        if(requiredQuantity>product.getQuantity()){
            throw new NotEnoughQuantityException();
        }

        //at this line product ,customer , card and requiredQuantity are all valid

        //calculate total cost
        cost =  product.getPrice() * requiredQuantity;

        //lets assign a delivery charge
        if(cost>=5000){
            deliveryCharge = 20;
        }
        else if (cost>=2000){
            deliveryCharge = 40;
        }
        else if(cost>=500){
            deliveryCharge = 60;
        }
        else
            deliveryCharge = 100;

        //total cost
        totalCost=cost + deliveryCharge;

        //make masked card
        cardNo+="XXXX-XXXX-XXXX-"+card.getCardNo().substring(12);

        //deduct the requested quantity from current quantity of the prodct
        product.setQuantity(product.getQuantity() - requiredQuantity);

        //make productStatus = OUT_OF_STOCK if productquantity = 0
        if(product.getQuantity()==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        //prepare the Ordered object and set its attributes
        Ordered newOrder = new Ordered();

        newOrder.setTotalCost(totalCost);
        newOrder.setDeliveryCharge(deliveryCharge);
        newOrder.setCardUsedForPayment(cardNo);
        newOrder.setCustomer(customer);

        customer.getOrderedList().add(newOrder);


        //list the product as an items
        //Create an Item and set its attributes
        Item item = new Item();

        item.setRequiredQuantity(requiredQuantity);
        item.setProduct(product);
        item.setOrdered(newOrder);
        item.setItemStatus(ItemStatus.ORDERED);

        newOrder.getItems().add(item);

        customerRepository.save(customer);

        //make the OrderResponseDTO and return it

        return OrderResponseDto.builder()
                .orderId(orderRepository.findLastOrderId())
                .orderDate(orderRepository.findLastOrderDate())
                .productName(product.getName())
                .price(product.getPrice())
                .quantity(requiredQuantity)
                .cardUsed(cardNo)
                .totalCost(totalCost)
                .deliveryCharge(deliveryCharge)
                .build();
    }
}
