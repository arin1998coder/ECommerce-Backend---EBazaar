package com.BadaBazaar.Ecommerce.service.impl;

import com.BadaBazaar.Ecommerce.converter.ItemConverter;
import com.BadaBazaar.Ecommerce.converter.ProductConverter;
import com.BadaBazaar.Ecommerce.dtos.*;
import com.BadaBazaar.Ecommerce.enums.ItemStatus;
import com.BadaBazaar.Ecommerce.enums.ProductStatus;
import com.BadaBazaar.Ecommerce.exception.*;
import com.BadaBazaar.Ecommerce.model.*;
import com.BadaBazaar.Ecommerce.repository.*;
import com.BadaBazaar.Ecommerce.service.CartService;
import com.BadaBazaar.Ecommerce.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    EmailService emailService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderedRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;


    @Override
    public ItemAddedToCartResponseDto addToCart(AddToCartRequestDto addToCartRequestDto) throws CustomerNotFoundException, ProductNotFoundException, ProductOutOfStockException, NotEnoughQuantityException, InvalidRequiredQuantityException {

        int customerId = addToCartRequestDto.getCustomerId();
        int productId = addToCartRequestDto.getProductId();
        int requiredQuantity = addToCartRequestDto.getQuantity();

        Customer customer;
        //valid customer id
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException();
        }

        Product product;
        //valid product id
        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        //check product is Available or not
        if(product.getProductStatus()== ProductStatus.OUT_OF_STOCK){
            throw new ProductOutOfStockException();
        }

        //validate the quantity requested by user
        if(requiredQuantity>product.getQuantity()){
            throw new NotEnoughQuantityException();
        }
        if(requiredQuantity<=0){
            throw new InvalidRequiredQuantityException("Invalid requested quantity");
        }

        //at this point product , quantity , customer are all valid and we can initiate the process of adding to cart

        Cart cart = customer.getCart();

        int updatedCartTotal = cart.getCartTotal() + requiredQuantity * product.getPrice();
        cart.setCartTotal(updatedCartTotal);

        //register the product as an item
        Item item = new Item();

        item.setRequiredQuantity(requiredQuantity);
        item.setProduct(product);
        item.setCart(cart);
        item.setItemStatus(ItemStatus.ADDED_TO_CART);

        //add the item to the cart
        cart.getItemList().add(item);

        customerRepository.save(customer);

        ItemAddedToCartResponseDto itemAddedToCartResponseDto = ProductConverter.productToItemAddedToCartResponseDTO(product,requiredQuantity);

        return itemAddedToCartResponseDto;

    }

    @Override
    public List<OrderResponseDto> cartCheckout(CheckOutRequestDto checkOutRequestDto) throws CustomerNotFoundException, CardDoesNotExistException, EmptyCartException, NotEnoughQuantityException, ProductOutOfStockException, ProductNotFoundException, InvalidRequiredQuantityException, InterruptedException {

        int customerId = checkOutRequestDto.getCustomerId();
        int cardId = checkOutRequestDto.getCardId();

        Customer customer;
        //valid customer id
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException();
        }

        Card card;
        //validate card id
        try{
            card = cardRepository.findById(cardId).get();
        }
        catch (Exception e){
            throw new CardDoesNotExistException();
        }

        //fetch the cart of the customer
        Cart cart = customer.getCart();

        //check if cart has items or not
        if(cart.getItemList().size()==0){
            throw new EmptyCartException();
        }


        //if reqd quantity > than available quantity of any product during checkout--> block the checkout process giving a message,
        //"Checkout can't be completed" since the quantity of item i is more than whatever is actually availabel

        //if any of the product is OUTOFSTOCK, then also block the checkout process
        for(Item item:cart.getItemList()){
            //check item.reqd quantity >=product.quantity
            if(item.getProduct().getQuantity()<item.getRequiredQuantity()){
                throw new NotEnoughQuantityException();
            }
            //if product is outofstock
            else if(item.getProduct().getProductStatus()==ProductStatus.OUT_OF_STOCK){
                throw new ProductOutOfStockException();
            }
        }

        //make masked card
        String cardNo="XXXX-XXXX-XXXX-"+card.getCardNo().substring(12);

        //at this point if we arrive , it means all the items in the cart are okay and can be proceeded for checkout

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        //place order for every item one by one
        int size = cart.getItemList().size();

        int count = 0;

        int cartTotal = cart.getCartTotal();

        int deliveryCharge;
            //lets assign a delivery charge
            if(cartTotal>=5000){
                deliveryCharge = 20;
            }
            else if (cartTotal>=2000){
                deliveryCharge = 40;
            }
            else if(cartTotal>=500){
                deliveryCharge = 60;
            }
            else
                deliveryCharge = 100;

        for(Item item : cart.getItemList()){

            //place order
            //calculate total cost
           int cost =  item.getProduct().getPrice() * item.getRequiredQuantity();

            //total cost
            int totalCost=cost + deliveryCharge;

            //deduct the requested quantity from current quantity of the prodct
            item.getProduct().setQuantity(item.getProduct().getQuantity() - item.getRequiredQuantity());

            //make productStatus = OUT_OF_STOCK if productquantity = 0
            if(item.getProduct().getQuantity()==0){
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            //prepare the Ordered object and set its attributes
            Ordered newOrder = new Ordered();

            newOrder.setTotalCost(totalCost);
            newOrder.setDeliveryCharge(deliveryCharge);
            newOrder.setCardUsedForPayment(cardNo);
            newOrder.setCustomer(customer);

            customer.getOrderedList().add(newOrder);

            item.setOrdered(newOrder);
            item.setCart(null);
            item.setItemStatus(ItemStatus.ORDERED);

            newOrder.getItems().add(item);

//            customerRepository.save(customer);
            count++;

            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .orderId(orderRepository.findLastOrderId()+count)
                    .orderDate(new Date())
                    .productName(item.getProduct().getName())
                    .price(item.getProduct().getPrice())
                    .quantity(item.getRequiredQuantity())
                    .cardUsed(cardNo)
                    .totalCost(totalCost)
                    .deliveryCharge(deliveryCharge)
                    .build();

            orderResponseDtoList.add(orderResponseDto);

            //send order placed mail to customer
            String subject = "EBazaar Order Confirmation Mail";
            String msgBody = "Hurray!! Your order has been placed successfully!\n\nOrder Id: "+orderRepository.findLastOrderId()+count
                    +"\nProduct name: "+item.getProduct().getName()+"\nQuantity: "+item.getRequiredQuantity()+"\nPrice: "+item.getProduct().getPrice()+"\nDelivery Charge: "+deliveryCharge+"\nTotal Cost: "+totalCost+"\n\nYour Order will be delivered Asap.\n\nThank You for Shopping with us!";

            emailService.sendMail(msgBody,subject);
        }

        //empty the cart
        cart.setCartTotal(0);
        cart.getItemList().clear();
        System.out.println(cart.getItemList().size());
//        System.out.println(cart.hashCode());
        customerRepository.save(customer);

        return orderResponseDtoList;
    }

    @Override
    public List<ItemAddedToCartResponseDto> viewCart(int customerId) throws CustomerNotFoundException, EmptyCartException {

        Customer customer;
        //valid customer id
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException();
        }

        //get cart of customer
        Cart cart = customer.getCart();

        //cart is empty
        if(cart.getItemList().isEmpty()){
            throw new EmptyCartException();
        }
        System.out.println(cart.getItemList().size());
        System.out.println(cart.getCartTotal());
        //display the items of the cart
        List<ItemAddedToCartResponseDto> listOfItemsInCart = new ArrayList<>();

        for(Item item:cart.getItemList()){

            Product product = item.getProduct();
            ItemAddedToCartResponseDto itemResponseDto = new ItemAddedToCartResponseDto();

            itemResponseDto = ProductConverter.productToItemAddedToCartResponseDTO(product,item.getRequiredQuantity());

            listOfItemsInCart.add(itemResponseDto);
        }

        return listOfItemsInCart;

    }

    @Override
    public List<ItemsRemovedResponseDTO> removeItemsFromCart(ItemsRemoveRequestDTO itemsRemoveRequestDTO) throws Exception {

        int customerId = itemsRemoveRequestDTO.getCustomerId();
        List<Integer> itemids = itemsRemoveRequestDTO.getItemIds();

        List<ItemsRemovedResponseDTO> listOfItemsRemovedResponseDTO = new ArrayList<>();


        Customer customer;
        //valid customer id
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException();
        }

        int countOfItemsRemoved = 0;

        //get cart of customer
        Cart cart = customer.getCart();

        for(int itemId:itemids){

            Item item = null;
            try{
                item = itemRepository.findById(itemId).get();
            }
            catch (Exception e){
                System.out.println("Item id : "+itemId+" doesn't exist");
                continue;
            }
            //item id is valid, but i need to check whether it belongs to the given customers cart or not
            if(cart.getItemList().contains(item)){
                item.setItemStatus(ItemStatus.REMOVED_FROM_CART);
                item.setCart(null);
                cart.getItemList().remove(item);
                cart.setCartTotal(cart.getCartTotal() - item.getRequiredQuantity() * item.getProduct().getPrice());

                countOfItemsRemoved++;
            }

            ItemsRemovedResponseDTO itemsRemovedResponseDTO = ItemConverter.ItemToItemRemovalResponseDTO(item);

            listOfItemsRemovedResponseDTO.add(itemsRemovedResponseDTO);
        }
        if(countOfItemsRemoved==0){
            throw new Exception("No Items were Removed!");
        }
        cartRepository.save(cart);

        return listOfItemsRemovedResponseDTO;

    }
}
