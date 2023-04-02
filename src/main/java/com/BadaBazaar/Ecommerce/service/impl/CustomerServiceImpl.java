package com.BadaBazaar.Ecommerce.service.impl;

import com.BadaBazaar.Ecommerce.converter.CustomerConverter;
import com.BadaBazaar.Ecommerce.converter.OrderConverter;
import com.BadaBazaar.Ecommerce.dtos.CustomerRequestDto;
import com.BadaBazaar.Ecommerce.dtos.OrderDetailsDto;
import com.BadaBazaar.Ecommerce.exception.CustomerNotFoundException;
import com.BadaBazaar.Ecommerce.model.Cart;
import com.BadaBazaar.Ecommerce.model.Customer;
import com.BadaBazaar.Ecommerce.model.Ordered;
import com.BadaBazaar.Ecommerce.repository.CustomerRepository;
import com.BadaBazaar.Ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String addCustomer(CustomerRequestDto customerRequestDto) {

        //todo
        //1.validate age should not be -ve
        //2.valid email should be in proper email format
        //3.validate mob no. is 10 digit and +ve num

        //create Customer object
        Customer customer = CustomerConverter.CustomerRequestDtoToCustomer(customerRequestDto);

        //create the cart and assign it a customer
        Cart cart = new Cart();

        //set atteibutes of the cart
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        customerRepository.save(customer);

        return "Congrats!! You have been registered and Your Id : "+customer.getId();
    }

    @Override
    public List<OrderDetailsDto> getAllOrdersByCustomerId(int customerId) throws CustomerNotFoundException {

        Customer customer = null;

        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException();
        }

        List<OrderDetailsDto> orderList = new ArrayList<>();

        for(Ordered order:customer.getOrderedList()){

            OrderDetailsDto orderDetailsDto= OrderConverter.orderToOrderDetailsDto(order);

            orderList.add(orderDetailsDto);
        }
        return orderList;
    }
}
