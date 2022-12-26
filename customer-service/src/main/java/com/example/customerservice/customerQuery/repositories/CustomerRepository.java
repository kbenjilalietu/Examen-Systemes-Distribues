package com.example.customerservice.customerQuery.repositories;

import com.example.customerservice.customerQuery.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {

}