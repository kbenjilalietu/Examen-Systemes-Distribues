package com.example.customerservice.customerQuery.controller;

import com.example.commonapi.query.GetAllCustomersQuery;
import com.example.commonapi.query.GetCustomerById;
import com.example.customerservice.customerQuery.entities.Customer;
import com.example.customerservice.customerQuery.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/queries")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerQueryController {
    private QueryGateway queryGateway;
    private CustomerRepository customerRepository;

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllRadars(){
        return queryGateway.query(new GetAllCustomersQuery(),
                ResponseTypes.multipleInstancesOf(Customer.class)).join();
    }

    @QueryHandler
    public List<Customer> on(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }

    @GetMapping("/getCustomer/{id}")
    public Customer getRadar(@PathVariable String id){
        return queryGateway.query(new GetCustomerById(id),
                ResponseTypes.instanceOf(Customer.class)).join();
    }

    @QueryHandler
    public Customer on(GetCustomerById query){
        return customerRepository.findById(query
                .getId()).get();
    }
}
