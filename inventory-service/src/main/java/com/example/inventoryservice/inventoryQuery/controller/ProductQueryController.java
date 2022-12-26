package com.example.inventoryservice.inventoryQuery.controller;


import com.example.commonapi.query.GetAllProductsQuery;
import com.example.commonapi.query.GetProductById;
import com.example.inventoryservice.inventoryQuery.entities.Product;
import com.example.inventoryservice.inventoryQuery.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products/queries")
@CrossOrigin("*")
public class ProductQueryController {
    private QueryGateway queryGateway;
    private ProductRepository proprietaireRepository;

    @GetMapping("/allProducts")
    public List<Product> getAllProprietaires() {
        return queryGateway.query(new GetAllProductsQuery(), ResponseTypes.multipleInstancesOf(Product.class)).join();
    }

    @QueryHandler
    public List<Product> on(GetAllProductsQuery query) {
        return proprietaireRepository.findAll();
    }

    @GetMapping("/getProprietaire/{id}")
    public Product getProprietaire(@PathVariable String id) {
        return queryGateway.query(new GetProductById(id),ResponseTypes.instanceOf(Product.class)).join();
    }

    @QueryHandler
    public Product on(GetProductById query) {
        return proprietaireRepository.findById(query.getId()).orElse(null);
    }
}
