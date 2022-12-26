package com.example.inventoryservice.inventoryQuery.repositories;

import com.example.inventoryservice.inventoryQuery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
